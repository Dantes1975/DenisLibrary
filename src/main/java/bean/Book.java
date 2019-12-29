package bean;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Author author;
    private String bookname;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Genre genre;
    private int stock;

    public Book(long id, String bookname) {
        this.id = id;
        this.bookname = bookname;
    }

    public Book(Author author, String bookname, Genre genre, int stock) {
        this.author = author;
        this.bookname = bookname;
        this.genre = genre;
        this.stock = stock;
    }

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
