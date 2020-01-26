package repository.daoImpl;

import bean.Message;
import repository.dao.AbstractDao;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class MessageDaoImpl extends AbstractDao<Message> {

    public List<Message> getMyMessages(long recipientId) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Message> criteria = cb.createQuery(Message.class);
        Root<Message> root = criteria.from(Message.class);
        criteria.select(root).where(cb.equal(root.get("recipient"), recipientId));
        List<Message> list = em.createQuery(criteria).getResultList();
        return list;
    }

}
