package by.it_academy.controller;


import by.it_academy.bean.Authenticate;
import by.it_academy.bean.User;
import by.it_academy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

import static by.it_academy.utill.ApplicationConstants.*;

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
    public ModelAndView processCreateByAdmin(@Valid @ModelAttribute User user, @Valid @ModelAttribute Authenticate authenticate,
                                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("redirect:/registration");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
            redirectAttributes.addFlashAttribute(USER, user);
            redirectAttributes.addFlashAttribute(AUTHENTICATE_KEY, authenticate);
            return modelAndView;

        } else {

            user.setAuthenticate(authenticate);
            authenticate.setUser(user);
            authenticateService.save(authenticate);

            ModelAndView modelAndView = new ModelAndView(USERPAGE_JSP);
            modelAndView.addObject(AUTHENTICATES_KEY, authenticateService.findAll());
            return modelAndView;

        }
    }

    @GetMapping("/update")
    public ModelAndView loadUpdatePage(HttpSession httpSession) {
        Authenticate authenticate = (Authenticate) httpSession.getAttribute(AUTHENTICATE_KEY);
        ModelAndView modelAndView = new ModelAndView(UPDATE_JSP);
        User user = authenticate.getUser();
        modelAndView.addObject(USER, user);
        modelAndView.addObject(AUTHENTICATE_KEY, authenticate);
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView updateUser(@Valid @ModelAttribute User user, @Valid @ModelAttribute Authenticate authenticate,
                                   BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("redirect:/registration");
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
            redirectAttributes.addFlashAttribute(USER, user);
            redirectAttributes.addFlashAttribute(AUTHENTICATE_KEY, authenticate);
            return modelAndView;

        } else {

            authenticate.setProfile_enable(ON_KEY);
            user.setAuthenticate(authenticate);
            authenticate.setUser(user);
            authenticateService.save(authenticate);
            ModelAndView modelAndView = new ModelAndView(GUEST_JSP);
            modelAndView.addObject(LISTBOOKS_KEY, bookService.getAllBooks());
            return modelAndView;
        }
    }

    @PostMapping("/off")
    public ModelAndView userOff(@RequestParam long id, @RequestParam String type) {
        ModelAndView modelAndView = new ModelAndView(USERPAGE_JSP);
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
        return modelAndView;
    }


    @PostMapping("/on")
    public ModelAndView userOn(@RequestParam long id) {
        ModelAndView modelAndView = new ModelAndView(USERPAGE_JSP);
        authenticateService.authenticateOn(id);
        modelAndView.addObject(AUTHENTICATES_KEY, authenticateService.findAll());
        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView userDelete(@RequestParam long id, @RequestParam long adminid) {
        ModelAndView modelAndView = new ModelAndView(USERPAGE_JSP);
        if (id != adminid) {
            authenticateService.deleteById(id);
            userService.deleteById(id);
            modelAndView.addObject(AUTHENTICATES_KEY, authenticateService.findAll());
        } else {
            modelAndView.addObject(AUTHENTICATES_KEY, authenticateService.findAll());

        }
        return modelAndView;
    }
}
