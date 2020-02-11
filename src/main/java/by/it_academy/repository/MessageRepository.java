package by.it_academy.repository;

import by.it_academy.bean.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
    @Query(value = "select m from Message m join fetch m.sender where m.recipient=:id")
    List<Message> getMessagesByRecipient(@Param("id") long Id);
}
