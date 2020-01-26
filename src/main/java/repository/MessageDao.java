package repository;

import bean.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDao extends CrudRepository<Message, Long> {
    public List<Message> getMessagesByRecipient(long recipientId);
}
