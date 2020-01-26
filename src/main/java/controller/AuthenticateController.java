package controller;


import bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import repository.AuthenticateDao;
import repository.BookDao;

import java.util.List;

import static utill.ApplicationConstants.*;
import static utill.ApplicationConstants.LOGIN_JSP;
import static utill.ErrorConstant.INVALID_USER_LOGIN;
import static utill.ErrorConstant.INVALID_USER_PASSWORD;

@Controller
public class AuthenticateController {

    @Autowired
    private AuthenticateDao authenticateDao;

    @Autowired
    private BookDao bookDao;

    @GetMapping("/login")
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
            } else {
                Authenticate authenticate = authenticateDao.getByLoginaAndPassword(login, password);
                ModelAndView modelAndView = new ModelAndView();
                if (authenticate != null) {
                    modelAndView.addObject("authenticate", authenticate);

                    if (authenticate.getUser().getRole() == Role.USER) {
                        modelAndView.setViewName("book");
                    } else {
                        modelAndView.addObject("authenticates", authenticateDao.findAll());
                        modelAndView.setViewName("admin");
                    }
                    return modelAndView;
                } else {
                    modelAndView.setViewName("start");
                }
            }
        } catch (RuntimeException e) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("authenticate");

            return modelAndView;
        }

    }

    @PostMapping("/guest")
    public ModelAndView loadGuestPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(LISTBOOKS_KEY, bookDao.findAll());
        modelAndView.setViewName("list");
        return modelAndView;
    }
}
