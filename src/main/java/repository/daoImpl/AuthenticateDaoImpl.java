package repository.daoImpl;

import bean.Authenticate;
import repository.dao.AbstractDao;
import repository.dao.AuthenticateDao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.*;


public class AuthenticateDaoImpl extends AbstractDao<Authenticate> implements AuthenticateDao {


    public Authenticate getByLogin(String login) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Authenticate> criteria = cb.createQuery(Authenticate.class);
        Root<Authenticate> root = criteria.from(Authenticate.class);
        Predicate expression = cb.equal(root.get("login"), login);
        criteria.select(root).where(expression);
        Authenticate authenticate = em.createQuery(criteria).getSingleResult();
        return authenticate;

    }


    public boolean isExistByLogin(String login) throws NoResultException {
        Authenticate authenticate = null;
        EntityManager em = getEntityManager();
//        Session session = em.unwrap(Session.class);
//        Query queryHib = session.createQuery("select a from Authenticate  a where a.login=?1").setParameter(1, login);
//        authenticate = (Authenticate) queryHib.getSingleResult();
//        em.close();
//        if (authenticate == null) {
//            return false;
//        }

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Authenticate> criteria = cb.createQuery(Authenticate.class);
        Root<Authenticate> root = criteria.from(Authenticate.class);
        Predicate expression = cb.equal(root.get("login"), login);
        try {
            criteria.select(root).where(expression);
            authenticate = em.createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return false;
        }
        return true;
    }


    public Authenticate authOn(long id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<Authenticate> update = cb.createCriteriaUpdate(Authenticate.class);
        Root<Authenticate> root = update.from(Authenticate.class);
        update.set("profile_enable", "ON");
        update.where(cb.equal(root.get("id"), id));
        em.createQuery(update).executeUpdate();
        em.getTransaction().commit();
        em.close();
        return getById(id);
    }

    public Authenticate authOff(long id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<Authenticate> update = cb.createCriteriaUpdate(Authenticate.class);
        Root<Authenticate> root = update.from(Authenticate.class);
        update.set("profile_enable", "OFF");
        update.where(cb.equal(root.get("id"), id));
        em.createQuery(update).executeUpdate();
        em.getTransaction().commit();
        em.close();
        return getById(id);
    }

    public Authenticate authBlock(long id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaUpdate<Authenticate> update = cb.createCriteriaUpdate(Authenticate.class);
        Root<Authenticate> root = update.from(Authenticate.class);
        update.set("profile_enable", "BLOCK");
        update.where(cb.equal(root.get("id"), id));
        em.createQuery(update).executeUpdate();
        em.getTransaction().commit();
        em.close();
        return getById(id);
    }
}
