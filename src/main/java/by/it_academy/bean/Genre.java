package by.it_academy.bean;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Genre {
    @Id
    @SequenceGenerator(name = "GENRE_ID_SEQ_GEN", sequenceName = "GENRE_ID_SEQ_GEN", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GENRE_ID_SEQ_GEN")
    private long id;
    private String genrename;

    public Genre(String genrename) {
        this.genrename = genrename;
    }


    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", genre='" + genrename + '\'' +
                '}';
    }
}
