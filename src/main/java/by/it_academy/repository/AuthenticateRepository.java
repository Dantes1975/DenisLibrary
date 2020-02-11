package by.it_academy.repository;

import by.it_academy.bean.Authenticate;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthenticateRepository extends CrudRepository<Authenticate, Long> {
    Authenticate getByLoginAndPassword(String login, String password);

    boolean existsByLogin(String login);

    @Modifying
    @Query(value = "update Authenticate a set a.profile_enable='ON' where a.id=:id")
    void authOn(@Param("id") long id);

    @Modifying
    @Query(value = "update Authenticate a set a.profile_enable='OFF' where a.id=:id")
    void authOff(@Param("id") long id);

    @Query(value = "select a from Authenticate  a join fetch a.user")
    List<Authenticate> getAllAuthenticates();
}
