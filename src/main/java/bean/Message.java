package bean;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long sender;
    private long recipient;
    private String text;

    public Message(long sender, long recipient, String text) {
        this.sender = sender;
        this.recipient = recipient;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", sender=" + sender +
                ", recipient=" + recipient +
                ", text='" + text + '\'' +
                '}';
    }
}
