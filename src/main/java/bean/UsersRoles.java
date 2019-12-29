package bean;


import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UsersRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private long user_id;
    @Column
    private long role_id;


    @Override
    public String toString() {
        return "Users_roles{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", role_id=" + role_id +
                '}';
    }

}
