package controller;

import bean.Authenticate;
import bean.Book;
import bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import repository.*;


import java.util.List;

import static utill.ErrorConstant.*;


@Controller
public class OperationWhithUserController {
    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthenticateDao authenticateDao;

    @Autowired
    private BorrowDao borrowDao;

    @Autowired
    private BookDao bookDao;


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

            user = userDao.save(user);
            authenticate = authenticateDao.save(authenticate);

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("user", user);
            modelAndView.addObject("authenticate", authenticate);
            modelAndView.addObject("authenticates", authenticateDao.findAll());
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

            user = userDao.save(user);
            authenticate = authenticateDao.save(authenticate);
            authenticate.setProfile_enable("ON");

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("user", user);
            modelAndView.addObject("authenticate", authenticate);
            modelAndView.addObject("authenticates", authenticateDao.findAll());
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
            authenticateDao.authOff(id);
            List<Long> booksID = borrowDao.getBooksIdByUserId(id);
            for (Long bookId : booksID) {
                bookDao.returnBook(bookId);
            }
            borrowDao.deleteByUser(id);
        } else {
            authenticateDao.authBlock(id);
        }
        modelAndView.addObject("authenticates", authenticateDao.findAll());
        modelAndView.setViewName("admin");
        return modelAndView;
    }


    @PostMapping("/on")
    public ModelAndView userOn(@RequestParam long id) {
        ModelAndView modelAndView = new ModelAndView();
        authenticateDao.authOn(id);
        modelAndView.addObject("authenticates", authenticateDao.findAll());
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView userDelete(@RequestParam long id) {
        ModelAndView modelAndView = new ModelAndView();
        authenticateDao.deleteById(id);
        userDao.deleteById(id);
        modelAndView.addObject("authenticates", authenticateDao.findAll());
        modelAndView.setViewName("admin");
        return modelAndView;
    }
}
