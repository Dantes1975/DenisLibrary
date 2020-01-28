package by.it_academy.repository;

import by.it_academy.bean.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
    public List<Message> getMessagesByRecipient(long recipientId);
}
