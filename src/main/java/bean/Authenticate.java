package bean;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Authenticate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String login;
    @Column
    private String password;
    @Column
    private String profile_enable;

    @Override
    public String toString() {
        return "Authenticate{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", profile_enable='" + profile_enable + '\'' +
                '}';
    }
}

