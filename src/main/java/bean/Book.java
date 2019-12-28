package bean;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private long id;
    private Author author;
    private String bookname;
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
