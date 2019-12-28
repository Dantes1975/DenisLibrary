package bean;

import lombok.*;

import java.util.Arrays;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bookimage {
    private long id;
    private long bookId;
    private String filename;
    private byte[] bookimage;




}
