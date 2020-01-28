package by.it_academy.repository;

import by.it_academy.bean.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    @Query(value = "update Book b set b.stock=(b.stock-1) where b.id=:id")
    public void takeBook(@Param("id") long id);

    @Query(value = "update Book b set b.stock=(b.stock+1) where b.id=:id")
    public void returnBook(long id);
}
