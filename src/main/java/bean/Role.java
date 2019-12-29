package bean;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String role;


    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }


}
