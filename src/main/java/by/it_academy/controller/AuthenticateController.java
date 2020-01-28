package by.it_academy.controller;


import by.it_academy.bean.Authenticate;
import by.it_academy.bean.Role;
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


import javax.servlet.http.HttpSession;

import static by.it_academy.utill.ApplicationConstants.*;
import static by.it_academy.utill.ErrorConstant.*;


@Controller
public class AuthenticateController {

    @Autowired
    private AuthenticateRepository authenticateRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/")
    protected ModelAndView loadLoginPage() {
        return new ModelAndView("start");
    }

    @PostMapping("/login")
    public ModelAndView processLogin(@RequestParam String login, @RequestParam String password) {
        try {

            if (login == null || login.isEmpty()) {
                throw new RuntimeException(INVALID_USER_LOGIN);
            } else if (password == null || password.isEmpty()) {
                throw new RuntimeException(INVALID_USER_PASSWORD);
            }

            Authenticate authenticate = authenticateRepository.getByLoginAndPassword(login, password);
            ModelAndView modelAndView = new ModelAndView();

            if (authenticate != null) {
                modelAndView.addObject("authenticate", authenticate);

                if (authenticate.getUser().getRole() == Role.USER) {
                    modelAndView.addObject(LISTBOOKS_KEY, bookRepository.findAll());
                    modelAndView.addObject(BORROWS_KEY, borrowRepository.findAll());
                    modelAndView.setViewName("books");
                } else {
                    modelAndView.addObject("authenticates", authenticateRepository.findAll());
                    modelAndView.setViewName("admin");
                }

            } else {
                modelAndView.setViewName("start");
            }
            return modelAndView;
        } catch (RuntimeException e) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("start");
            return modelAndView;
        }

    }

    @PostMapping("/guest")
    public ModelAndView loadGuestPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(LISTBOOKS_KEY, bookRepository.findAll());
        modelAndView.setViewName("list");
        return modelAndView;
    }

    @GetMapping("/registration")
    public ModelAndView loadRegistrationPage() {
        ModelAndView modelAndView = new ModelAndView("registration");

        User user = new User();
        user.setRole(Role.USER);
        Authenticate authenticate = new Authenticate();
        authenticate.setProfile_enable("ON");
        modelAndView.addObject("user", user);
        modelAndView.addObject("authenticate", authenticate);

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
            authenticate = authenticateRepository.save(authenticate);

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("user", authenticate.getUser());
            modelAndView.addObject("authenticate", authenticate);
            modelAndView.setViewName("start");

            return modelAndView;

        } catch (RuntimeException e) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("registration");
            return modelAndView;
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "start";
    }
}
