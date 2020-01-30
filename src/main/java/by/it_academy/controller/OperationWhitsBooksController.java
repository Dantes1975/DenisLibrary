package by.it_academy.controller;


import by.it_academy.bean.*;
import by.it_academy.repository.BookimageRepository;
import by.it_academy.service.AuthenticateService;
import by.it_academy.service.BookImageService;
import by.it_academy.service.BookService;
import by.it_academy.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import static by.it_academy.utill.ApplicationConstants.*;

@Controller
@RequestMapping("/")
public class OperationWhitsBooksController {

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
        Author author = new Author();
        Genre genre = new Genre();
        //Bookimage bookimage = new Bookimage();
        modelAndView.addObject("book", book);
        modelAndView.addObject("author", author);
        modelAndView.addObject("genre", genre);
        //modelAndView.addObject("bookimage", bookimage);
        return modelAndView;
    }

    @PostMapping("/createBookByAdmin")
    public ModelAndView createBookByAdmin(@ModelAttribute Book book, @ModelAttribute Author author,
                                          @ModelAttribute Genre genre,
                                          @RequestParam(value = "image", required = false) MultipartFile file) {
        ModelAndView modelAndView = new ModelAndView(ADMIN_JSP);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setStock(5);
        book = bookService.save(book);
        Bookimage bookimage = new Bookimage();
        bookimage.setBookId(book.getId());
        bookimage.setFilename(file.getName());
        try {
            byte[] image = file.getBytes();
            bookimage.setBookimage(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        modelAndView.addObject("book", book);
        modelAndView.addObject(BOOKIMAGE_KEY, bookimage);
        return modelAndView;
    }

    @PostMapping("/takebook")
    public ModelAndView takeBook(@RequestParam long bookid, @RequestParam long userid, @RequestParam int days) {
        ModelAndView modelAndView = new ModelAndView("books");
        bookService.takeBook(bookid);
        Borrow borrow = new Borrow(bookService.findById(bookid),
                userid, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(days)));
        borrowService.save(borrow);
        modelAndView.addObject(LISTBOOKS_KEY, bookService.getAllBooks());
        modelAndView.addObject(BORROWS_KEY, borrowService.findAll());
        modelAndView.addObject(AUTHENTICATE_KEY, authenticateService.getById(userid));
        return modelAndView;
    }

    @PostMapping("/listbooks")
    public ModelAndView listBooks() {
        ModelAndView modelAndView = new ModelAndView("books");
        modelAndView.addObject(LISTBOOKS_KEY, bookService.getAllBooks());
        return modelAndView;
    }

    @PostMapping("/returnbook")
    public ModelAndView returnBook(@RequestParam long bookid, @RequestParam long userid) {
        ModelAndView modelAndView = new ModelAndView(BOOKS_JSP);
        bookService.returnBook(bookid);
        borrowService.deleteByBook(bookid);
        modelAndView.addObject(AUTHENTICATE_KEY, authenticateService.getById(userid));
        modelAndView.addObject(LISTBOOKS_KEY, bookService.getAllBooks());
        modelAndView.addObject(BORROWS_KEY, borrowService.findAll());
        return modelAndView;
    }

    @PostMapping("/description")
    public ModelAndView getDescription(@RequestParam long bookid) {
        ModelAndView modelAndView = new ModelAndView();
        if (bookid <= 3) {
            modelAndView.setViewName(DESCRIPTION_JSP);
        } else {
            Bookimage bookimage = bookImageService.getByBookId(bookid);
            modelAndView.addObject(BOOKIMAGE_KEY, bookimage);
        }
        return modelAndView;
    }


}
