package repository.daoImpl;

import bean.Bookimage;
import org.hibernate.Session;

import repository.dao.AbstractDao;
import repository.dao.BookimageDao;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.*;


public class BookimageDaoImpl extends AbstractDao<Bookimage> implements BookimageDao {

    private final String SELECT_IMAGE = "select b from Bookimage b where b.bookId=:bookId";

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

    @Override
    public Bookimage getById(long id) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery(SELECT_IMAGE);
        query.setParameter("bookId", id);
        Bookimage bookimage = (Bookimage) query.getSingleResult();
        em.getTransaction().commit();
        em.close();
        return bookimage;
    }

}
