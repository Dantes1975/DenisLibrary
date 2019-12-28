package bean;

import lombok.*;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

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
