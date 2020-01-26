package repository;

import bean.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorDao extends CrudRepository<Author, Long> {
}
