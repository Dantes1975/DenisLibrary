package by.it_academy.service;

import by.it_academy.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;
}
