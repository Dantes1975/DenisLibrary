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
    @SequenceGenerator(name = "BOOKIMAGE_ID_SEQ_GEN", sequenceName = "BOOKIMAGE_ID_SEQ_GEN", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOKIMAGE_ID_SEQ_GEN")
    private long id;
    private long bookId;
    private String filename;

    @EqualsAndHashCode.Exclude
    @Lob
    private byte[] bookimage;


}
