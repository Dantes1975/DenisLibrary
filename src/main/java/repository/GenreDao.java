package repository;

import bean.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreDao extends CrudRepository<Genre, Long> {
}
