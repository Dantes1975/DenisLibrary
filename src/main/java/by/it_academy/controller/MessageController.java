package by.it_academy.controller;


import by.it_academy.bean.Authenticate;
import by.it_academy.bean.Message;
import by.it_academy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static by.it_academy.utill.ApplicationConstants.*;


@Controller
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/message")
    public ModelAndView loadMessagePage(HttpSession httpSession) {
        Authenticate authenticate = (Authenticate) httpSession.getAttribute(AUTHENTICATE_KEY);
        ModelAndView modelAndView = new ModelAndView(MESSAGE_JSP);
        modelAndView.addObject(MYMESSAGES_KEY, messageService.getMessagesByRecipient(authenticate.getId()));
        return modelAndView;
    }

    @PostMapping("/message")
    public ModelAndView createMessage(@RequestParam long sender, @RequestParam long recipient, @RequestParam String text) {
        ModelAndView modelAndView = new ModelAndView(MESSAGE_JSP);
        Message message = new Message(sender, recipient, text);
        message = messageService.save(message);
        modelAndView.addObject(MESSAGE_KEY, message);
        modelAndView.addObject(MYMESSAGES_KEY, messageService.getMessagesByRecipient(sender));
        return modelAndView;
    }

    @PostMapping("/deleteMessage")
    public ModelAndView deleteMessage(@RequestParam long recipient, @RequestParam long id) {
        ModelAndView modelAndView = new ModelAndView(MESSAGE_JSP);
        messageService.deleteById(id);
        modelAndView.addObject(MYMESSAGES_KEY, messageService.getMessagesByRecipient(recipient));
        return modelAndView;
    }
}
