package by.it_academy.bean;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
