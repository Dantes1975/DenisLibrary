package by.it_academy.controller;


import by.it_academy.bean.Message;
import by.it_academy.repository.MessageRepository;
import by.it_academy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MessageController {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/message")
    public ModelAndView loadMessagePage() {
        ModelAndView modelAndView = new ModelAndView("login");
        Message message = new Message();
        modelAndView.addObject("message", message);
        return modelAndView;
    }

}
