package by.it_academy.service;

import by.it_academy.bean.Bookimage;
import by.it_academy.repository.BookimageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
@Transactional
public class BookImageService {

    @Autowired
    private BookimageRepository bookimageRepository;

    public Bookimage save(Bookimage bookimage) {
        File file = new File("d:/" + bookimage.getFilename());
        try {
            byte[] bytes = Files.readAllBytes(file.toPath());
            bookimage.setBookimage(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookimageRepository.save(bookimage);
    }

    public Bookimage getByBookId(long id) {
        return bookimageRepository.getByBookId(id);
    }

    public void deleteByBookId(long id){
        bookimageRepository.deleteByBookId(id);
    }
}
