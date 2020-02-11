package by.it_academy.bean;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Borrow {
    @Id
    @SequenceGenerator(name = "BORROW_ID_SEQ_GEN", sequenceName = "BORROW_ID_SEQ_GEN", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BORROW_ID_SEQ_GEN")
    private long id;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    private Book book;

    private long user;
    private Date borrowDate;
    private Date returnDate;

    public Borrow(Book book, long user, Date borrowDate, Date returnDate) {
        this.book = book;
        this.user = user;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "id=" + id +
                ", user=" + user +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                '}';
    }


}

