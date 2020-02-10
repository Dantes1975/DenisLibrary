package by.it_academy.repository;

import by.it_academy.bean.Bookimage;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookimageRepository extends CrudRepository<Bookimage, Long> {
    Bookimage getByBookId(long id);

    @Modifying
    void deleteByBookId(long id);
}
