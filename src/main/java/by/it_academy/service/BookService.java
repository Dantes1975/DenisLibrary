package by.it_academy.service;

import by.it_academy.bean.Book;
import by.it_academy.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book save(Book book){
        return bookRepository.save(book);
    }

    public Book findById(long id){
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Book> getAllBooks(){
        return (List<Book>) bookRepository.findAll();
    }

    public void takeBook(long id) {
        Book book = bookRepository.findById(id).orElse(null);
        int stock = book.getStock() - 1;
        book.setStock(stock);
        bookRepository.save(book);
    }

    public void returnBook(long id) {
        Book book = bookRepository.findById(id).orElse(null);
        int stock = book.getStock() + 1;
        book.setStock(stock);
        bookRepository.save(book);
    }
}
