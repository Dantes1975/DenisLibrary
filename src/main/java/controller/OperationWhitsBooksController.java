package controller;

import bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import repository.BookDao;
import repository.BookimageDao;
import repository.BorrowDao;

import java.sql.Date;
import java.time.LocalDate;

import static utill.ApplicationConstants.BORROWS_KEY;
import static utill.ApplicationConstants.LISTBOOKS_KEY;

@Controller
@RequestMapping("/")
public class OperationWhitsBooksController {

    @Autowired
    BookDao bookDao;

    @Autowired
    BorrowDao borrowDao;

    @Autowired
    BookimageDao bookimageDao;

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
        book = bookDao.save(book);
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
        bookDao.takeBook(bookId);
        Borrow borrow = new Borrow(bookDao.findById(bookId).orElse(null),
                userId, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(days)));
        borrowDao.save(borrow);
        modelAndView.addObject(LISTBOOKS_KEY, bookDao.findAll());
        modelAndView.addObject(BORROWS_KEY, borrowDao.findAll());
        return modelAndView;
    }

    @PostMapping("/listbooks")
    public ModelAndView listBooks() {
        ModelAndView modelAndView = new ModelAndView("books");
        modelAndView.addObject(LISTBOOKS_KEY, bookDao.findAll());
        return modelAndView;
    }

    @PostMapping("/returnbook")
    public ModelAndView returnBook(@RequestParam long bookId, @RequestParam long userId) {
        ModelAndView modelAndView = new ModelAndView("books");
        bookDao.returnBook(bookId);
        borrowDao.deleteByUser(userId);
        modelAndView.addObject(LISTBOOKS_KEY, bookDao.findAll());
        modelAndView.addObject(BORROWS_KEY, borrowDao.findAll());
        return modelAndView;
    }

@PostMapping("/description")
    public ModelAndView getDescription(@RequestParam long id){
    ModelAndView modelAndView = new ModelAndView("image");
    Bookimage bookimage = bookimageDao.getByBookId(id);
    modelAndView.addObject("bookimage", bookimage);
    return modelAndView;
}


}
