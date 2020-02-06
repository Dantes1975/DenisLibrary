package by.it_academy.controller;


import by.it_academy.bean.Authenticate;
import by.it_academy.bean.User;
import by.it_academy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

import static by.it_academy.utill.ApplicationConstants.*;
import static by.it_academy.utill.ErrorConstant.*;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private BorrowService borrowService;

    @Autowired
    private BookService bookService;

    @Autowired
    AuthenticateService authenticateService;

    @Autowired
    private MessageService messageService;

    @GetMapping("/create")
    public ModelAndView loadCreatePage() {
        ModelAndView modelAndView = new ModelAndView(CREATE_BY_ADMIN_JSP);
        User user = new User();
        Authenticate authenticate = new Authenticate();
        authenticate.setProfile_enable(ON_KEY);
        modelAndView.addObject(USER, user);
        modelAndView.addObject(AUTHENTICATE_KEY, authenticate);
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView processCreateByAdmin(@ModelAttribute User user, @ModelAttribute Authenticate authenticate) {

        try {

            if (authenticate.getLogin() == null || authenticate.getLogin().isEmpty()) {
                throw new RuntimeException(INVALID_USER_LOGIN);
            }
            if (authenticate.getPassword() == null || authenticate.getPassword().isEmpty()) {
                throw new RuntimeException(INVALID_USER_PASSWORD);
            }

            user.setAuthenticate(authenticate);
            authenticate.setUser(user);
            authenticateService.save(authenticate);

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName(START_JSP);
            return modelAndView;

        } catch (RuntimeException e) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject(ERROR_KEY, e.getMessage());
            modelAndView.setViewName(CREATE_BY_ADMIN_JSP);
            return modelAndView;
        }
    }

    @GetMapping("/update")
    public ModelAndView loadUpdatePage(@RequestParam long id) {
        ModelAndView modelAndView = new ModelAndView(UPDATE_JSP);
        User user = new User();
        user.setId(id);
        Authenticate authenticate = new Authenticate();
        authenticate.setId(id);
        modelAndView.addObject(USER, user);
        modelAndView.addObject(AUTHENTICATE_KEY, authenticate);
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView updateUser(@ModelAttribute User user, @ModelAttribute Authenticate authenticate) {

        try {

            if (authenticate.getLogin() == null || authenticate.getLogin().isEmpty()) {
                throw new RuntimeException(INVALID_USER_LOGIN);
            }
            if (authenticate.getPassword() == null || authenticate.getPassword().isEmpty()) {
                throw new RuntimeException(INVALID_USER_PASSWORD);
            }

            authenticate.setProfile_enable(ON_KEY);
            user.setAuthenticate(authenticate);
            authenticate.setUser(user);
            authenticate = authenticateService.save(authenticate);


            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject(USER, user);
            modelAndView.addObject(AUTHENTICATE_KEY, authenticate);
            modelAndView.addObject(AUTHENTICATES_KEY, authenticateService.findAll());
            modelAndView.setViewName(START_JSP);
            return modelAndView;

        } catch (RuntimeException e) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject(ERROR_KEY, e.getMessage());
            modelAndView.setViewName(UPDATE_JSP);
            return modelAndView;
        }
    }

    @PostMapping("/off")
    public ModelAndView userOff(@RequestParam long id, @RequestParam String type, @RequestParam long adminid) {
        ModelAndView modelAndView = new ModelAndView();
        if (type.equals(OFF_KEY)) {
            authenticateService.authenticateOff(id);
            List<Long> booksID = borrowService.getBooksIdByUserId(id);
            for (Long bookId : booksID) {
                bookService.returnBook(bookId);
            }
            borrowService.deleteByUser(id);
        } else {
            authenticateService.authenticateBlock(id);
        }
        modelAndView.addObject(AUTHENTICATES_KEY, authenticateService.findAll());
        modelAndView.addObject(AUTHENTICATE_KEY, authenticateService.getById(adminid));
        modelAndView.addObject(MYMESSAGES_KEY, messageService.getMessagesByRecipient(adminid));
        modelAndView.setViewName(ADMIN_JSP);
        return modelAndView;
    }


    @PostMapping("/on")
    public ModelAndView userOn(@RequestParam long id, @RequestParam long adminid) {
        ModelAndView modelAndView = new ModelAndView();
        authenticateService.authenticateOn(id);
        modelAndView.addObject(AUTHENTICATES_KEY, authenticateService.findAll());
        modelAndView.addObject(AUTHENTICATE_KEY, authenticateService.getById(adminid));
        modelAndView.addObject(MYMESSAGES_KEY, messageService.getMessagesByRecipient(adminid));
        modelAndView.setViewName(ADMIN_JSP);
        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView userDelete(@RequestParam long id, @RequestParam long adminid) {
        ModelAndView modelAndView = new ModelAndView();
        if (id != adminid) {
            authenticateService.deleteById(id);
            userService.deleteById(id);
            modelAndView.addObject(AUTHENTICATES_KEY, authenticateService.findAll());
            modelAndView.addObject(AUTHENTICATE_KEY, authenticateService.getById(adminid));
            modelAndView.addObject(MYMESSAGES_KEY, messageService.getMessagesByRecipient(adminid));
        } else {
            modelAndView.addObject(AUTHENTICATES_KEY, authenticateService.findAll());
            modelAndView.addObject(AUTHENTICATE_KEY, authenticateService.getById(adminid));
            modelAndView.addObject(MYMESSAGES_KEY, messageService.getMessagesByRecipient(adminid));
        }
        modelAndView.setViewName(ADMIN_JSP);
        return modelAndView;
    }
}
