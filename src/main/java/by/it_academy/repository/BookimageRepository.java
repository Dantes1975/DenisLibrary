package by.it_academy.repository;

import by.it_academy.bean.Bookimage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookimageRepository extends CrudRepository<Bookimage, Long> {
    public Bookimage getByBookId(long id);
}
