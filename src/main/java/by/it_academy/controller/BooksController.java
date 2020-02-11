package by.it_academy.controller;


import by.it_academy.bean.*;
import by.it_academy.service.BookImageService;
import by.it_academy.service.BookService;
import by.it_academy.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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


    @GetMapping({"/", "guest"})
    protected ModelAndView loadGuestPage() {
        ModelAndView modelAndView = new ModelAndView(GUEST_JSP);
        modelAndView.addObject(LISTBOOKS_KEY, bookService.getAllBooks());
        return modelAndView;
    }

    @GetMapping("/main")
    protected ModelAndView loadMainPage() {
        ModelAndView modelAndView = new ModelAndView(MAIN_JSP);
        modelAndView.addObject(LISTBOOKS_KEY, bookService.getAllBooks());
        return modelAndView;
    }

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
    public ModelAndView updateBook(@PathVariable long id) {
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
        book = bookService.save(book);
        bookimage.setBookId(book.getId());
        bookImageService.save(bookimage);
        return new ModelAndView(REDIRECT_MAIN);
    }

    @GetMapping(value = "/loadImage/{id}", produces = {MediaType.IMAGE_JPEG_VALUE})
    @ResponseBody
    public byte[] loadImage(@PathVariable int id) {

        if (id >= 1 && id <= 3) {
            return bookImageService.getMockImage(--id);
        }

        return bookImageService.getByBookId(id).getBookimage();
    }

    @PostMapping("/takebook")
    public ModelAndView takeBook(@RequestParam long bookid, @RequestParam long userid, @RequestParam int days) {
        bookService.takeBook(bookid);
        Borrow borrow = new Borrow(bookService.findById(bookid),
                userid, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(days)));
        borrowService.save(borrow);
        return new ModelAndView(REDIRECT_MAIN);
    }

    @PostMapping("/returnbook")
    public ModelAndView returnBook(@RequestParam long bookid) {
        bookService.returnBook(bookid);
        borrowService.deleteByBook(bookid);
        return new ModelAndView(REDIRECT_MAIN);
    }

    @PostMapping("/description")
    public ModelAndView getDescription(@RequestParam long bookid) {
        ModelAndView modelAndView = new ModelAndView(IMAGE_JSP);
        Book book = bookService.findById(bookid);
        modelAndView.addObject(BOOK_KEY, book);
        modelAndView.addObject(BORROWS_KEY, borrowService.getBorowsByBookId(bookid));
        return modelAndView;
    }


}
