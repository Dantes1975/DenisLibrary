package by.it_academy.bean;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    @NotEmpty
    private String name;
    @Column
    @NotEmpty
    private String surname;
    @Column
    @NotEmpty
    private String email;
    @Column
    @Digits(integer = 3, fraction = 0)
    private int age;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "user", orphanRemoval = true)
    private Authenticate authenticate;

    public User(String name, String surname, String email, int age) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.age = age;
    }

    public User(long id, String name, String surname, String email, int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.age = age;
    }


    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }


}
