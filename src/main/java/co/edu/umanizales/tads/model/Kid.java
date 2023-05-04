package co.edu.umanizales.tads.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Data
@AllArgsConstructor
@Getter
@Setter
public class Kid {
    private String identification;
    private String name;
    private byte age;

    private String gender;
    private Location location;


}
