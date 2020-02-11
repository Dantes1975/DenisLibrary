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

    @PostMapping("/createMessage")
    public ModelAndView createMessage(HttpSession httpSession, @RequestParam long recipient, @RequestParam String text) {
        Authenticate sender = (Authenticate) httpSession.getAttribute(AUTHENTICATE_KEY);
        Message message = new Message(sender, recipient, text);
        messageService.save(message);
        return new ModelAndView(REDIRECT_MESSAGE);
    }

    @PostMapping("/deleteMessage")
    public ModelAndView deleteMessage(@RequestParam long id) {
        messageService.deleteById(id);
        return new ModelAndView(REDIRECT_MESSAGE);
    }
}
