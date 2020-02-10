package by.it_academy.controller;


import by.it_academy.bean.*;
import by.it_academy.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.time.LocalDate;

import static by.it_academy.utill.ApplicationConstants.*;

@Controller
public class BooksController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BorrowService borrowService;

    @Autowired
    private BookImageService bookImageService;

    @Autowired
    private AuthenticateService authenticateService;


    @GetMapping("/createBookByAdmin")
    public ModelAndView loadCreateBookPage() {
        ModelAndView modelAndView = new ModelAndView(CREATE_BOOK_JSP);
        Book book = new Book();
        book.setAuthor(new Author());
        book.setGenre(new Genre());
        book.setStock(5);
        Bookimage bookimage = new Bookimage();
        modelAndView.addObject(BOOK_KEY, book);
        modelAndView.addObject(BOOKIMAGE_KEY, bookimage);
        return modelAndView;
    }

    @GetMapping("/updateBook/{id}")
    public ModelAndView updateBook(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView(CREATE_BOOK_JSP);
        Book book = bookService.findById(id);
        Bookimage bookimage = new Bookimage();
        bookImageService.deleteByBookId(id);
        modelAndView.addObject(BOOK_KEY, book);
        modelAndView.addObject(BOOKIMAGE_KEY, bookimage);
        return modelAndView;
    }


    @PostMapping("/createBookByAdmin")
    public ModelAndView createBookByAdmin(@ModelAttribute Book book, @ModelAttribute Bookimage bookimage) {
        ModelAndView modelAndView = new ModelAndView(USERPAGE_JSP);
        book = bookService.save(book);
        bookimage.setBookId(book.getId());
        bookImageService.save(bookimage);
        modelAndView.addObject(AUTHENTICATES_KEY, authenticateService.findAll());
        return modelAndView;
    }

    @GetMapping(value = "/loadImage/{id}", produces = {MediaType.IMAGE_JPEG_VALUE})
    @ResponseBody
    public byte[] loadImage(@PathVariable int id) {
        byte[] bytes = new byte[0];
        try {
            if (id == 1) {
                File file = new File(PATH_NAME + "Osn_oper.jpg");
                bytes = Files.readAllBytes(file.toPath());
            } else if (id == 2) {
                File file = new File(PATH_NAME + "Oper_pr.jpg");
                bytes = Files.readAllBytes(file.toPath());
            } else if (id == 3) {
                File file = new File(PATH_NAME + "Antikiller.jpg");
                bytes = Files.readAllBytes(file.toPath());
            } else {
                bytes = bookImageService.getByBookId(id).getBookimage();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    @PostMapping("/takebook")
    public ModelAndView takeBook(@RequestParam long bookid, @RequestParam long userid, @RequestParam int days) {
        ModelAndView modelAndView = new ModelAndView(MAIN_JSP);
        bookService.takeBook(bookid);
        Borrow borrow = new Borrow(bookService.findById(bookid),
                userid, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(days)));
        borrowService.save(borrow);
        modelAndView.addObject(LISTBOOKS_KEY, bookService.getAllBooks());
        return modelAndView;
    }

    @PostMapping("/returnbook")
    public ModelAndView returnBook(@RequestParam long bookid, @RequestParam long userid) {
        ModelAndView modelAndView = new ModelAndView(USERPAGE_JSP);
        bookService.returnBook(bookid);
        borrowService.deleteByBook(bookid);
        modelAndView.addObject(LISTBOOKS_KEY, bookService.getAllBooks());
        modelAndView.addObject(BORROWS_KEY, borrowService.getBorowsByUser(userid));
        return modelAndView;
    }

    @PostMapping("/description")
    public ModelAndView getDescription(@RequestParam long bookid) {
        ModelAndView modelAndView = new ModelAndView();
        Book book = bookService.findById(bookid);
        modelAndView.addObject(BOOK_KEY, book);
        modelAndView.addObject(BORROWS_KEY, borrowService.getBorowsByBookId(bookid));
        modelAndView.setViewName(IMAGE_JSP);
        return modelAndView;
    }


}
