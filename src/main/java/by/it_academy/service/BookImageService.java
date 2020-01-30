package by.it_academy.service;

import by.it_academy.bean.Bookimage;
import by.it_academy.repository.BookimageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BookImageService {
    @Autowired
    private BookimageRepository bookimageRepository;

    public Bookimage getByBookId(long id){
        return bookimageRepository.getByBookId(id);
    }
}
