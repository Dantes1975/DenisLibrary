package repository;

import bean.Bookimage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookimageDao extends CrudRepository<Bookimage, Long> {
    public Bookimage getByBookId(long id);
}
