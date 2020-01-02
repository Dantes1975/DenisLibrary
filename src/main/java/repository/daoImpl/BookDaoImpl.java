package repository.daoImpl;


import bean.Book;
import repository.dao.AbstractDao;
import repository.dao.BookDao;

import javax.persistence.EntityManager;


public class BookDaoImpl extends AbstractDao<Book> implements BookDao {

    public void takeBook(long id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Book book = em.find(Book.class, id);
        int stock = book.getStock();
        book.setStock(stock - 1);
        em.merge(book);
        em.getTransaction().commit();
        em.close();
    }

    public void returnBook(long id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Book book = em.find(Book.class, id);
        int stock = book.getStock();
        book.setStock(stock + 1);
        em.merge(book);
        em.getTransaction().commit();
        em.close();
    }


}
