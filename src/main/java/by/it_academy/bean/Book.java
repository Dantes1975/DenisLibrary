package by.it_academy.bean;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {

    @Id
    @SequenceGenerator(name = "BOOK_ID_SEQ_GEN", sequenceName = "BOOK_ID_SEQ_GEN", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOK_ID_SEQ_GEN")
    private long id;

    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private Author author;

    private String bookname;

    @EqualsAndHashCode.Exclude
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private Genre genre;
    private int stock;


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author=" + author +
                ", bookname='" + bookname + '\'' +
                ", genre=" + genre +
                ", stock=" + stock +
                '}';
    }
}
