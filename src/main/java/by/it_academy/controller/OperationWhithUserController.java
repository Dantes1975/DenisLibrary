package by.it_academy.controller;


import by.it_academy.bean.Authenticate;
import by.it_academy.bean.User;
import by.it_academy.repository.AuthenticateRepository;
import by.it_academy.repository.BookRepository;
import by.it_academy.repository.BorrowRepository;
import by.it_academy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



import java.util.List;

import static by.it_academy.utill.ErrorConstant.*;


@Controller
public class OperationWhithUserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticateRepository authenticateRepository;

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private BookRepository bookRepository;


    @GetMapping("/create")
    public ModelAndView loadCreatePage() {
        ModelAndView modelAndView = new ModelAndView("createUserByAdmin");
        User user = new User();
        Authenticate authenticate = new Authenticate();
        modelAndView.addObject("user", user);
        modelAndView.addObject("authenticate", authenticate);
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

            user = userRepository.save(user);
            authenticate = authenticateRepository.save(authenticate);

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("user", user);
            modelAndView.addObject("authenticate", authenticate);
            modelAndView.addObject("authenticates", authenticateRepository.findAll());
            modelAndView.setViewName("admin");
            return modelAndView;

        } catch (RuntimeException e) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("createUserByAdmin");
            return modelAndView;
        }
    }

    @GetMapping("/update")
    public ModelAndView loadUpdatePage() {
        ModelAndView modelAndView = new ModelAndView("update");
        User user = new User();
        Authenticate authenticate = new Authenticate();
        modelAndView.addObject("user", user);
        modelAndView.addObject("authenticate", authenticate);
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

            user = userRepository.save(user);
            authenticate = authenticateRepository.save(authenticate);
            authenticate.setProfile_enable("ON");

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("user", user);
            modelAndView.addObject("authenticate", authenticate);
            modelAndView.addObject("authenticates", authenticateRepository.findAll());
            modelAndView.setViewName("start");
            return modelAndView;

        } catch (RuntimeException e) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("update");
            return modelAndView;
        }
    }

    @PostMapping("/off")
    public ModelAndView userOff(@RequestParam long id, @RequestParam String type) {
        ModelAndView modelAndView = new ModelAndView();
        if (type.equals("off")) {
            authenticateRepository.authOff(id);
            List<Long> booksID = borrowRepository.getBooksIdByUserId(id);
            for (Long bookId : booksID) {
                bookRepository.returnBook(bookId);
            }
            borrowRepository.deleteByUser(id);
        } else {
            authenticateRepository.authBlock(id);
        }
        modelAndView.addObject("authenticates", authenticateRepository.findAll());
        modelAndView.setViewName("admin");
        return modelAndView;
    }


    @PostMapping("/on")
    public ModelAndView userOn(@RequestParam long id) {
        ModelAndView modelAndView = new ModelAndView();
        authenticateRepository.authOn(id);
        modelAndView.addObject("authenticates", authenticateRepository.findAll());
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView userDelete(@RequestParam long id) {
        ModelAndView modelAndView = new ModelAndView();
        authenticateRepository.deleteById(id);
        userRepository.deleteById(id);
        modelAndView.addObject("authenticates", authenticateRepository.findAll());
        modelAndView.setViewName("admin");
        return modelAndView;
    }
}
