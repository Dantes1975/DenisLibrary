package bean;

import lombok.*;

import java.util.Objects;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private long id;
    private String role;



    @Override
    public String toString() {
        return "Roles{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }


}
