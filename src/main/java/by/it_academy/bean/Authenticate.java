package by.it_academy.bean;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Authenticate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotEmpty
    private String login;

    @Column
    @NotEmpty
    @Size(min = 2, max = 10)
    private String password;

    @Column
    private String profile_enable;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @PrimaryKeyJoinColumn
    private User user;


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

