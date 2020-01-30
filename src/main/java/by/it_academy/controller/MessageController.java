package by.it_academy.controller;


import by.it_academy.bean.Message;
import by.it_academy.bean.Role;
import by.it_academy.bean.User;
import by.it_academy.repository.MessageRepository;
import by.it_academy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static by.it_academy.utill.ApplicationConstants.*;


@Controller
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticateService authenticateService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BorrowService borrowService;

    @PostMapping("/message")
    public ModelAndView createMessage(@RequestParam long sender, @RequestParam long recipient, @RequestParam String text) {
        ModelAndView modelAndView = new ModelAndView();
        Message message = new Message(sender, recipient, text);
        message = messageService.save(message);
        modelAndView.addObject(MESSAGE_KEY, message);
        modelAndView.addObject(AUTHENTICATE_KEY, authenticateService.getById(sender));
        modelAndView.addObject(LISTBOOKS_KEY, bookService.getAllBooks());
        modelAndView.addObject(BORROWS_KEY, borrowService.getBorowsByUser(sender));
        modelAndView.addObject(MYMESSAGES_KEY, messageService.getMessagesByRecipient(sender));
        modelAndView.addObject(AUTHENTICATES_KEY, authenticateService.findAll());
        User user = userService.getById(sender);
        if (user.getRole() == Role.USER) {
            modelAndView.setViewName(BOOKS_JSP);
        } else {
            modelAndView.setViewName(ADMIN_JSP);
        }
        return modelAndView;
    }

    @PostMapping("/deleteMessage")
    public ModelAndView deleteMessage(@RequestParam long recipient, @RequestParam long id) {
        ModelAndView modelAndView = new ModelAndView();
        messageService.deleteById(id);
        modelAndView.addObject(MYMESSAGES_KEY, messageService.getMessagesByRecipient(recipient));
        modelAndView.addObject(AUTHENTICATE_KEY, authenticateService.getById(recipient));
        modelAndView.addObject(LISTBOOKS_KEY, bookService.getAllBooks());
        modelAndView.addObject(BORROWS_KEY, borrowService.getBorowsByUser(recipient));
        modelAndView.addObject(AUTHENTICATES_KEY, authenticateService.findAll());
        User user = userService.getById(recipient);
        if (user.getRole() == Role.USER) {
            modelAndView.setViewName(BOOKS_JSP);
        } else {
            modelAndView.setViewName(ADMIN_JSP);
        }
        return modelAndView;
    }
}
