package repository.daoImpl;

import bean.Borrow;
import repository.dao.AbstractDao;
import repository.dao.BorrowDao;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class BorrowDaoImpl extends AbstractDao<Borrow> implements BorrowDao {


    public List<Borrow> getBooksByUserId(Long id) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Borrow> criteria = cb.createQuery(Borrow.class);
        Root<Borrow> root = criteria.from(Borrow.class);
        criteria.select(root).where(cb.equal(root.get("user"), id));
        List<Borrow> list = em.createQuery(criteria).getResultList();
        return list;
    }

//    public List<Long> getBooksIdByUserId(Long id) {
////        EntityManager em = getEntityManager();
////        CriteriaBuilder cb = em.getCriteriaBuilder();
////        CriteriaQuery<Borrow> criteria = cb.createQuery(Borrow.class);
////        Root<Borrow> root = criteria.from(Borrow.class);
////        criteria.select(root).where(cb.equal(root.get("user"), id));
////        List<Long> list = em.createQuery(criteria).getResultList();
////        return list;
////    }

    public void deleteUserById(long id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaDelete<Borrow> delete = cb.createCriteriaDelete(Borrow.class);
        Root<Borrow> root = delete.from(Borrow.class);
        delete.where(cb.equal(root.get("user"), id));
        em.createQuery(delete).executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}

