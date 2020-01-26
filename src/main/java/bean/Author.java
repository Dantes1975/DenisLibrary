package bean;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "firstname")
    private String name;
    @Column(name = "lastname")
    private String surname;

    public Author(String authorname, String authorsurame) {
        this.name = authorname;
        this.surname = authorsurame;
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
