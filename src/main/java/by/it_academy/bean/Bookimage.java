package by.it_academy.bean;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Bookimage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long bookId;
    private String filename;
    @Lob
    private byte[] bookimage;


}
