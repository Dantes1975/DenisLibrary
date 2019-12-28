package bean;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users_roles {
    private long id;
    private long user_id;
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
