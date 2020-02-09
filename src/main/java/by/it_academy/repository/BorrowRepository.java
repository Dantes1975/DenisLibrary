package by.it_academy.repository;

import by.it_academy.bean.Borrow;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRepository extends CrudRepository<Borrow, Long> {

   public List<Borrow> getBorrowsByUser(long id);
   public List<Borrow> getBorrowsByBook_Id(long id);
   public void deleteByUser(long id);
   public void deleteByBookId(long id);

   @Query(value = "select b.book.id from Borrow b where b.user=:id")
    public List<Long> getBooksIdByUserId(@Param("id") long id);


}
