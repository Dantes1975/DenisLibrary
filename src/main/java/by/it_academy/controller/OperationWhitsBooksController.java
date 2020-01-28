package by.it_academy.controller;


import by.it_academy.bean.*;
import by.it_academy.repository.BookRepository;
import by.it_academy.repository.BookimageRepository;
import by.it_academy.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import java.sql.Date;
import java.time.LocalDate;

import static by.it_academy.utill.ApplicationConstants.*;

@Controller
@RequestMapping("/")
public class OperationWhitsBooksController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BorrowRepository borrowRepository;

    @Autowired
    BookimageRepository bookimageRepository;

    @GetMapping("/createBookByAdmin")
    public ModelAndView loadCreateBookPage() {
        ModelAndView modelAndView = new ModelAndView("createBook");
        Book book = new Book();
        Author author = new Author();
        Genre genre = new Genre();
        Bookimage bookimage = new Bookimage();
        modelAndView.addObject("book", book);
        modelAndView.addObject("author", author);
        modelAndView.addObject("genre", genre);
        modelAndView.addObject("bookimage", bookimage);
        return modelAndView;
    }

    @PostMapping("/createBookByAdmin")
    public ModelAndView createBookByAdmin(@ModelAttribute Book book, @ModelAttribute Author author,
                                          @ModelAttribute Genre genre,
                                          @RequestParam(value = "image", required = false) MultipartFile file){
        ModelAndView modelAndView = new ModelAndView("admin");
        book.setAuthor(author);
        book.setGenre(genre);
        book.setStock(5);
        book = bookRepository.save(book);
        Bookimage bookimage = new Bookimage();
        bookimage.setBookId(book.getId());
        bookimage.setFilename(file.getName());
        //bookimage.setBookimage();
        modelAndView.addObject("book", book);
        modelAndView.addObject("bookimage", bookimage);
        return modelAndView;
    }

    @PostMapping("/takebook")
    public ModelAndView takeBook(@RequestParam long bookId, @RequestParam long userId, @RequestParam int days) {
        ModelAndView modelAndView = new ModelAndView("books");
        bookRepository.takeBook(bookId);
        Borrow borrow = new Borrow(bookRepository.findById(bookId).orElse(null),
                userId, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(days)));
        borrowRepository.save(borrow);
        modelAndView.addObject(LISTBOOKS_KEY, bookRepository.findAll());
        modelAndView.addObject(BORROWS_KEY, borrowRepository.findAll());
        return modelAndView;
    }

    @PostMapping("/listbooks")
    public ModelAndView listBooks() {
        ModelAndView modelAndView = new ModelAndView("books");
        modelAndView.addObject(LISTBOOKS_KEY, bookRepository.findAll());
        return modelAndView;
    }

    @PostMapping("/returnbook")
    public ModelAndView returnBook(@RequestParam long bookId, @RequestParam long userId) {
        ModelAndView modelAndView = new ModelAndView("books");
        bookRepository.returnBook(bookId);
        borrowRepository.deleteByUser(userId);
        modelAndView.addObject(LISTBOOKS_KEY, bookRepository.findAll());
        modelAndView.addObject(BORROWS_KEY, borrowRepository.findAll());
        return modelAndView;
    }

@PostMapping("/description")
    public ModelAndView getDescription(@RequestParam long id){
    ModelAndView modelAndView = new ModelAndView("image");
    Bookimage bookimage = bookimageRepository.getByBookId(id);
    modelAndView.addObject("bookimage", bookimage);
    return modelAndView;
}


}
