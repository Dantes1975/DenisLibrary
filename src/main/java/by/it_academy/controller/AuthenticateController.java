package by.it_academy.controller;


import by.it_academy.bean.Authenticate;
import by.it_academy.bean.Role;
import by.it_academy.bean.User;
import by.it_academy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static by.it_academy.utill.ApplicationConstants.*;
import static by.it_academy.utill.ErrorConstant.*;


@Controller
public class AuthenticateController {

    @Autowired
    private AuthenticateService authenticateService;

    @Autowired
    private BorrowService borrowService;

    @Autowired
    UserService userService;


    @GetMapping("/login")
    protected ModelAndView loadLoginPage() {
        ModelAndView modelAndView = new ModelAndView(START_JSP);
        modelAndView.addObject(AUTHENTICATE_KEY, new Authenticate());
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView processLogin(@Valid @ModelAttribute Authenticate authenticate,
                                     BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                     HttpSession httpSession) {
        try {

            if (bindingResult.hasErrors()) {
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.authenticate", bindingResult);
                redirectAttributes.addFlashAttribute(AUTHENTICATE_KEY, authenticate);
                return new ModelAndView(REDIRECT_LOGIN);
            }

            authenticate = authenticateService.getByLoginAndPassword(authenticate.getLogin(), authenticate.getPassword());
            ModelAndView modelAndView = new ModelAndView();

            if (authenticate != null) {
                httpSession.setAttribute(AUTHENTICATE_KEY, authenticate);
                modelAndView.setViewName(REDIRECT_MAIN);
            } else throw new RuntimeException(USER_NOT_FOUND);

            return modelAndView;
        } catch (RuntimeException e) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject(ERROR_KEY, e.getMessage());
            modelAndView.setViewName(START_JSP);
            return modelAndView;
        }

    }

    @GetMapping("/registration")
    public ModelAndView loadRegistrationPage() {
        ModelAndView modelAndView = new ModelAndView(REGISTRATION_JSP);
        User user = new User();
        user.setRole(Role.USER);
        Authenticate authenticate = new Authenticate();
        authenticate.setProfile_enable(ON_KEY);
        authenticate.setUser(user);
        user.setAuthenticate(authenticate);
        modelAndView.addObject(AUTHENTICATE_KEY, authenticate);
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView processRegistration(@Valid @ModelAttribute User user, @Valid @ModelAttribute Authenticate authenticate,
                                            BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                            HttpSession httpSession) {

        try {
            if (bindingResult.hasErrors()) {
                ModelAndView modelAndView = new ModelAndView(REDIRECT_REGISTRATION);
                redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
                redirectAttributes.addFlashAttribute(USER, user);
                redirectAttributes.addFlashAttribute(AUTHENTICATE_KEY, authenticate);
                return modelAndView;

            } else {

                if (!authenticateService.existByLogin(authenticate.getLogin())) {
                    user.setAuthenticate(authenticate);
                    authenticate.setUser(user);
                    authenticate = authenticateService.save(authenticate);
                    httpSession.setAttribute(AUTHENTICATE_KEY, authenticate);
                    return new ModelAndView(REDIRECT_MAIN);
                } else throw new RuntimeException(ALREADY_EXIST);

            }
        } catch (RuntimeException e) {
            ModelAndView modelAndView = new ModelAndView(REGISTRATION_JSP);
            modelAndView.addObject(ERROR_KEY, e.getMessage());
            return modelAndView;
        }

    }

    @GetMapping("/userpage")
    public ModelAndView loadUserPage(HttpSession httpSession) {
        Authenticate authenticate = (Authenticate) httpSession.getAttribute(AUTHENTICATE_KEY);
        ModelAndView modelAndView = new ModelAndView(USERPAGE_JSP);
        modelAndView.addObject(AUTHENTICATES_KEY, authenticateService.findAll());
        modelAndView.addObject(BORROWS_KEY, borrowService.getBorowsByUser(authenticate.getId()));
        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        return new ModelAndView(REDIRECT_GUEST);
    }

    @GetMapping("/home")
    public ModelAndView returnHomePage(HttpSession httpSession) {
        Authenticate authenticate = (Authenticate) httpSession.getAttribute(AUTHENTICATE_KEY);
        ModelAndView modelAndView = new ModelAndView();
        if (authenticate != null) {
            modelAndView.setViewName(REDIRECT_MAIN);
        } else {
            modelAndView.setViewName(REDIRECT_GUEST);
        }
        return modelAndView;
    }


}
