package by.it_academy.bean;

import lombok.*;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Message {
    @Id
    @SequenceGenerator(name = "MESSAGE_ID_SEQ_GEN", sequenceName = "MESSAGE_ID_SEQ_GEN", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MESSAGE_ID_SEQ_GEN")
    private long id;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    private Authenticate sender;
    private long recipient;
    private String text;

    public Message(Authenticate sender, long recipient, String text) {
        this.sender = sender;
        this.recipient = recipient;
        this.text = text;
    }

}
