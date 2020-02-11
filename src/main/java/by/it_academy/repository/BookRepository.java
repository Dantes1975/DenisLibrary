package by.it_academy.repository;

import by.it_academy.bean.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    @Query(value = "select b from Book b join fetch b.author join fetch b.genre")
    List<Book> getAllBooks();
}
