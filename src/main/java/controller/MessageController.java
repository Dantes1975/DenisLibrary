package controller;

import bean.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import repository.MessageDao;
import repository.UserDao;

@Controller
public class MessageController {
    @Autowired
    private MessageDao messageDao;

    @Autowired
    private UserDao userDao;

    @GetMapping("/message")
    public ModelAndView loadMessagePage() {
        ModelAndView modelAndView = new ModelAndView("login");
        Message message = new Message();
        modelAndView.addObject("message", message);
        return modelAndView;
    }

}
