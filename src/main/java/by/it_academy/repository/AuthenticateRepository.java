package by.it_academy.repository;

import by.it_academy.bean.Authenticate;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticateRepository extends CrudRepository<Authenticate, Long> {
    Authenticate getByLoginAndPassword(String login, String password);

    boolean existsByLogin(String login);

    @Modifying
    @Query(value = "update Authenticate a set a.profile_enable='ON' where a.id=:id")
    void authOn(@Param("id") long id);

    @Modifying
    @Query(value = "UPDATE AUTHENTICATE SET PROFILE_ENABLE='OFF' WHERE ID=:id", nativeQuery = true)
    void authOff(@Param("id") long id);

    @Modifying
    @Query(value = "update Authenticate a set a.profile_enable='BOCK' where a.id=:id")
    void authBlock(@Param("id") long id);
}
