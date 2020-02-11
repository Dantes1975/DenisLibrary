package by.it_academy.repository;

import by.it_academy.bean.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query(value = "select u from User u join fetch u.authenticate")
    User getById(long id);

}
