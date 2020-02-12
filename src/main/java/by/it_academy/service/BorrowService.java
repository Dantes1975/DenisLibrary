package by.it_academy.service;

import by.it_academy.bean.Borrow;
import by.it_academy.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BorrowService {
    @Autowired
    private BorrowRepository borrowRepository;

    public Borrow save(Borrow borrow) {
        return borrowRepository.save(borrow);
    }

    @Transactional(readOnly = true)
    public List<Long> getBooksIdByUserId(long id) {
        return borrowRepository.getBooksIdByBorrow(id);
    }

    public void deleteByUser(long id) {
        borrowRepository.deleteByBorrow(id);
    }

    public void deleteByBook(long id) {
        borrowRepository.deleteByBookId(id);
    }

    @Transactional(readOnly = true)
    public List<Borrow> getBorowsByUser(long id) {
        return (List<Borrow>) borrowRepository.getBorrowsByUserId(id);
    }

    @Transactional(readOnly = true)
    public List<Borrow> getBorowsByBookId(long id) {
        return (List<Borrow>) borrowRepository.getBorrowsByBook_Id(id);
    }
}
