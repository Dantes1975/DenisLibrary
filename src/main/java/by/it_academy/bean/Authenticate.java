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
    @SequenceGenerator(name = "AUTHENTICATE_ID_SEQ_GEN", sequenceName = "AUTHENTICATE_ID_SEQ_GEN", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTHENTICATE_ID_SEQ_GEN")
    private long id;

    @Column
    @NotEmpty
    private String login;

    @Column
    @NotEmpty
    @Size(min = 2, max = 10)
    private String password;

    @EqualsAndHashCode.Exclude
    @Column
    private String profile_enable;

    @EqualsAndHashCode.Exclude
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
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

