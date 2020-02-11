package by.it_academy.bean;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Author {
    @Id
    @SequenceGenerator(name = "AUTHOR_ID_SEQ_GEN", sequenceName = "AUTHOR_ID_SEQ_GEN", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTHOR_ID_SEQ_GEN")
    private long id;
    @Column(name = "firstname")
    private String name;
    @Column(name = "lastname")
    private String surname;

    public Author(String name, String surame) {
        this.name = name;
        this.surname = surame;
    }


    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
