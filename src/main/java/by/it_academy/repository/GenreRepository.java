package by.it_academy.repository;


import by.it_academy.bean.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {
}
