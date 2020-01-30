package by.it_academy.controller;


import by.it_academy.bean.Authenticate;
import by.it_academy.bean.Role;
import by.it_academy.bean.User;
import by.it_academy.repository.*;
import by.it_academy.service.AuthenticateService;
import by.it_academy.service.BookService;
import by.it_academy.service.BorrowService;
import by.it_academy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpSession;

import static by.it_academy.utill.ApplicationConstants.*;
import static by.it_academy.utill.ErrorConstant.*;


@Controller
public class AuthenticateController {

    @Autowired
    private AuthenticateService authenticateService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BorrowService borrowService;

    @Autowired
    UserService userService;

    @Autowired
    MessageRepository messageRepository;


    @GetMapping("/")
    protected ModelAndView loadLoginPage() {
        return new ModelAndView(START_JSP);
    }

    @PostMapping("/login")
    public ModelAndView processLogin(@RequestParam String login, @RequestParam String password) {
        try {

            if (login == null || login.isEmpty()) {
                throw new RuntimeException(INVALID_USER_LOGIN);
            } else if (password == null || password.isEmpty()) {
                throw new RuntimeException(INVALID_USER_PASSWORD);
            }

            Authenticate authenticate = authenticateService.getByLoginAndPassword(login, password);
            ModelAndView modelAndView = new ModelAndView();

            if (authenticate != null) {
                modelAndView.addObject(AUTHENTICATE_KEY, authenticate);

                if (authenticate.getUser().getRole() == Role.USER) {
                    modelAndView.addObject(LISTBOOKS_KEY, bookService.getAllBooks());
                    modelAndView.addObject(BORROWS_KEY, borrowService.findAll());
                    modelAndView.addObject(MYMESSAGES_KEY, messageRepository.getMessagesByRecipient(authenticate.getId()));
                    modelAndView.setViewName(BOOKS_JSP);
                } else {
                    modelAndView.addObject(AUTHENTICATES_KEY, authenticateService.findAll());
                    modelAndView.addObject(MYMESSAGES_KEY, messageRepository.getMessagesByRecipient(authenticate.getId()));
                    modelAndView.setViewName(ADMIN_JSP);
                }

            } else {
                modelAndView.setViewName(START_JSP);
            }
            return modelAndView;
        } catch (RuntimeException e) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject(ERROR_KEY, e.getMessage());
            modelAndView.setViewName(START_JSP);
            return modelAndView;
        }

    }

    @PostMapping("/guest")
    public ModelAndView loadGuestPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(LISTBOOKS_KEY, bookService.getAllBooks());
        modelAndView.setViewName(LIST_JSP);
        return modelAndView;
    }

    @GetMapping("/registration")
    public ModelAndView loadRegistrationPage() {
        ModelAndView modelAndView = new ModelAndView(REGISTRATION_JSP);

        User user = new User();
        user.setRole(Role.USER);
        Authenticate authenticate = new Authenticate();
        authenticate.setProfile_enable("ON");
        modelAndView.addObject(USER, user);
        modelAndView.addObject(AUTHENTICATE_KEY, authenticate);

        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView processRegistration(@ModelAttribute User user, @ModelAttribute Authenticate authenticate) {

        try {

            if (authenticate.getLogin() == null || authenticate.getLogin().isEmpty()) {
                throw new RuntimeException(INVALID_USER_LOGIN);
            }
            if (authenticate.getPassword() == null || authenticate.getPassword().isEmpty()) {
                throw new RuntimeException(INVALID_USER_PASSWORD);
            }

            user.setAuthenticate(authenticate);
            authenticate.setUser(user);
            authenticate = authenticateService.save(authenticate);

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject(USER, authenticate.getUser());
            modelAndView.addObject(AUTHENTICATE_KEY, authenticate);
            modelAndView.setViewName(START_JSP);

            return modelAndView;

        } catch (RuntimeException e) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject(ERROR_KEY, e.getMessage());
            modelAndView.setViewName(REGISTRATION_JSP);
            return modelAndView;
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return START_JSP;
    }
}
