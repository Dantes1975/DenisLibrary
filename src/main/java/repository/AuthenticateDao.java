package repository;

import bean.Authenticate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticateDao extends CrudRepository<Authenticate, Long> {
    public Authenticate getByLogin(String login);
    public boolean existsByLogin(String login);

    @Query(value = "update Authenticate a set a.profile_enable='ON' where a.id=:id")
    public Authenticate authOn(@Param("id") long id);

    @Query(value = "update Authenticate a set a.profile_enable='OFF' where a.id=:id")
    public Authenticate authOff(@Param("id") long id);

    @Query(value = "update Authenticate a set a.profile_enable='BOCK' where a.id=:id")
    public Authenticate authBlock(@Param("id") long id);
}
