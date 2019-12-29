package repository.daoImpl;

import bean.Authenticate;
import repository.dao.AbstractDao;
import repository.dao.AuthenticateDao;
import repository.dao.JpaEntityManagerFactoryUtil;
import repository.database.DataBaseConnector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.criteria.*;
import java.sql.*;

public class AuthenticateDaoImpl extends AbstractDao<Authenticate> implements AuthenticateDao {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = JpaEntityManagerFactoryUtil.getEntityManagerFactory();
    protected EntityManager getEntityManager() {
        return ENTITY_MANAGER_FACTORY.createEntityManager();
    }

    @Override
    protected String getAllSqlQuery() {
        return "SELECT * FROM AUTHENTICATE";
    }

    @Override
    protected String getByIdSqlQuery(long id) {
        return "SELECT * FROM AUTHENTICATE WHERE id =" + id;
    }

    @Override
    protected String getInsertQuery(Authenticate authenticate) {
        return "INSERT INTO Authenticate (login, password) VALUES ('" + authenticate.getLogin() +
                "','" + authenticate.getPassword() + "')";
    }

    @Override
    protected String getUpdateQuery(Authenticate authenticate) {
        return "UPDATE AUTHENTICATE SET login='" + authenticate.getLogin() + "', password='" +
                authenticate.getPassword() + "' WHERE ID=" + authenticate.getId();
    }

    @Override
    protected String getDeleteQuery(long id) {
        return "DELETE FROM AUTHENTICATE WHERE id =" + id;
    }

    @Override
    protected Authenticate resultSetMapper(ResultSet k) throws SQLException {
        Authenticate authenticate = new Authenticate();
        authenticate.setId(k.getLong("id"));
        authenticate.setLogin(k.getString("login"));
        authenticate.setPassword(k.getString("password"));
        authenticate.setProfile_enable(k.getString("profile_enable"));
        return authenticate;
    }


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
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Authenticate> criteria = cb.createQuery(Authenticate.class);
        Root<Authenticate> root = criteria.from(Authenticate.class);
        Predicate expression = cb.equal(root.get("login"), login);
        criteria.select(root).where(expression);
        authenticate = em.createQuery(criteria).getSingleResult();
        if (authenticate == null) {
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
