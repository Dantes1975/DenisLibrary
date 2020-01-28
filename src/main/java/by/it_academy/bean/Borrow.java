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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.EAGER)
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
                ", book=" + book +
                ", user=" + user +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                '}';
    }


}

