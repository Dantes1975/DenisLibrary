package by.it_academy.service;

import by.it_academy.bean.Message;
import by.it_academy.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Message save(Message message){
        return messageRepository.save(message);
    }

    public void deleteById(long id){
        messageRepository.deleteById(id);
    }

    public List<Message> getMessagesByRecipient(long id){
        return messageRepository.getMessagesByRecipient(id);
    }

}

