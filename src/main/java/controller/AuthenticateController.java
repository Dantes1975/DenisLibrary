package controller;


import bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import repository.AuthenticateDao;
import repository.BookDao;
import repository.BorrowDao;
import repository.UserDao;


import static utill.ApplicationConstants.*;
import static utill.ErrorConstant.*;


@Controller
public class AuthenticateController {

    @Autowired
    private AuthenticateDao authenticateDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private BorrowDao borrowDao;

    @Autowired
    UserDao userDao;


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

            Authenticate authenticate = authenticateDao.getByLoginaAndPassword(login, password);
            ModelAndView modelAndView = new ModelAndView();

            if (authenticate != null) {
                modelAndView.addObject("authenticate", authenticate);

                if (authenticate.getUser().getRole() == Role.USER) {
                    modelAndView.addObject(LISTBOOKS_KEY, bookDao.findAll());
                    modelAndView.addObject(BORROWS_KEY, borrowDao.findAll());
                    modelAndView.setViewName("book");
                } else {
                    modelAndView.addObject("authenticates", authenticateDao.findAll());
                    modelAndView.setViewName("admin");
                }

            } else {
                modelAndView.setViewName("start");
            }
            return modelAndView;
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

    @GetMapping("/registration")
    public ModelAndView loadRegistrationPage() {
        ModelAndView modelAndView = new ModelAndView("login");

        User user = new User();
        user.setRole(Role.USER);
        Authenticate authenticate = new Authenticate();
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


            user = userDao.save(user);
            authenticate = authenticateDao.save(authenticate);

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("user", user);
            modelAndView.addObject("authenticate", authenticate);

            if (authenticate.getUser().getRole() == Role.USER) {
                modelAndView.addObject(LISTBOOKS_KEY, bookDao.findAll());
                modelAndView.addObject(BORROWS_KEY, borrowDao.findAll());
                modelAndView.setViewName("book");
            } else {
                modelAndView.addObject("authenticates", authenticateDao.findAll());
                modelAndView.setViewName("admin");
            }

            return modelAndView;

        } catch (RuntimeException e) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("error", e.getMessage());
            modelAndView.setViewName("registration");

            return modelAndView;
        }
    }
}
