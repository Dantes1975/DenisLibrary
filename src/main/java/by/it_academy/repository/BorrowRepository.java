package by.it_academy.repository;

import by.it_academy.bean.Borrow;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends CrudRepository<Borrow, Long> {

    @Query(value = "SELECT b from Borrow b JOIN FETCH b.book where b.user=:id")
    List<Borrow> getBorrowsByUserId(@Param("id") long id);

    @Query(value = "SELECT b from Borrow b JOIN FETCH b.book where b.book.id=:id")
    List<Borrow> getBorrowsByBook_Id(@Param("id") long id);

    @Modifying
    void deleteByUser(long id);

    @Modifying
    void deleteByBookId(long id);

    @Query(value = "select b.book.id from Borrow b where b.user=:id")
    List<Long> getBooksIdByUser(@Param("id") long id);


}
