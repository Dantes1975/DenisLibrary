package repository;

import bean.Borrow;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowDao extends CrudRepository<Borrow, Long> {

   public List<Borrow> getBorrowsByUser(long id);
   public void deleteByUser(long id);
   public void deleteByBookId(long id);

   @Query(value = "select b.book.id from Borrow b where b.user=:id")
    public List<Long> getBooksIdByUserId(@Param("id") long id);


}
