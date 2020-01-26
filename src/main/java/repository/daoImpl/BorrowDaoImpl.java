package repository.daoImpl;

import bean.Borrow;
import repository.dao.AbstractDao;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class BorrowDaoImpl extends AbstractDao<Borrow> {

    private final String DELETE_BY_BOOK_ID = "delete from Borrow b where b.book.id=:bookId";
    private final String DELETE_BY_USER_ID = "delete from Borrow b where b.user=:userId";
    private final String SELECT_BORROWS_BY_USER_ID = "select b from Borrow b where b.user=:userId";
    private final String SELECT_BOOKID_BY_USER_ID = "select b.book.id from Borrow b where b.user=:userId";

    public List<Borrow> getBorrowsByUserId(long id) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery(SELECT_BORROWS_BY_USER_ID);
        List<Borrow> list = query.setParameter("userId", id).getResultList();
        em.close();
        return list;
    }

    public List<Long> getBooksIdByUserId(long id) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery(SELECT_BOOKID_BY_USER_ID);
        List<Long> list = query.setParameter("userId", id).getResultList();
        em.close();
        return list;
    }

    public void deleteByUserId(long id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery(DELETE_BY_USER_ID);
        query.setParameter("userId", id).executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void deleteByBookId(long id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery(DELETE_BY_BOOK_ID);
        query.setParameter("bookId", id).executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}

