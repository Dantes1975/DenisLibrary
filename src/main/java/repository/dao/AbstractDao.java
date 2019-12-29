package repository.dao;

import org.hibernate.LazyInitializationException;
import repository.dao.CrudDao;
import repository.database.DataBaseConnector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T> implements CrudDao<T> {
    private DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = JpaEntityManagerFactoryUtil.getEntityManagerFactory();

    private final Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    protected EntityManager getEntityManager() {
        return ENTITY_MANAGER_FACTORY.createEntityManager();
    }


    protected abstract String getAllSqlQuery();

    protected abstract String getByIdSqlQuery(long id);

    protected abstract String getInsertQuery(T t);

    protected abstract String getUpdateQuery(T t);

    protected abstract String getDeleteQuery(long id);

    protected abstract T resultSetMapper(ResultSet k) throws SQLException;


    @Override
    public T getById(long id) throws LazyInitializationException {
        EntityManager em = getEntityManager();
        T t = em.find(clazz, id);
        em.close();
        return t;
    }


    @Override
    public T insert(T t) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        em.close();
        return t;
    }

    @Override
    public T update(T t) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        t = em.merge(t);
        em.getTransaction().commit();
        em.close();
        return t;
    }

    @Override
    public void delete(long id) {
        EntityManager em = getEntityManager();
        T t = em.find(clazz, id);
        em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<T> getAll() {
        EntityManager em = getEntityManager();
        List<T> list = em.createQuery(String.format("select t from %s t", clazz.getName()), clazz)
                .getResultList();
        em.close();
        return list;
    }

    protected T getSingleResultByQuery(String query) {
        EntityManager em = getEntityManager();
        T t = em.createQuery(query, clazz).getSingleResult();
        em.close();
        return t;
    }

    public List<T> getResultListByQuery(String query) {
        EntityManager em = getEntityManager();
        List<T> t = em.createQuery(query, clazz).getResultList();
        em.close();
        return t;
    }


}
