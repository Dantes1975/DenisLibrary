package bean;

import lombok.*;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
    private long id;
    private String genrename;



    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", genre='" + genrename + '\'' +
                '}';
    }
}
