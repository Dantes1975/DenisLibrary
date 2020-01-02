package repository.daoImpl;

import bean.Authenticate;
import bean.Bookimage;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import repository.dao.AbstractDao;
import repository.dao.BookimageDao;
import repository.database.DataBaseConnector;

import javax.persistence.EntityManager;
import java.io.*;

public class BookimageDaoImpl extends AbstractDao<Bookimage> implements BookimageDao {


    @Override
    public Bookimage insert(Bookimage bookimage) {
        EntityManager em = getEntityManager();
        Session session = em.unwrap(Session.class);
        session.beginTransaction();
        File file = new File("D:/" + bookimage.getFilename());
        byte[] imageData = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(imageData);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        bookimage.setBookimage(imageData);
        session.save(bookimage);
        session.getTransaction().commit();
        session.close();
        return bookimage;
    }

//    @Override
//    public Bookimage getById(long id) {
//        Bookimage bookimage = null;
//        EntityManager em = getEntityManager();
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Bookimage> criteria = cb.createQuery(Bookimage.class);
//        Root<Bookimage> root = criteria.from(Bookimage.class);
//        Predicate expression = cb.equal(root.get("id"), id);
//        criteria.select(root).where(expression);
//        bookimage = em.createQuery(criteria).getSingleResult();
//        return bookimage;
//    }

}
