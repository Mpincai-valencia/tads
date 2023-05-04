package co.edu.umanizales.tads.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@Getter
@Setter
public class Kid {
    private String identification;
    private String name;
    private byte age;

    private char gender;
    private Location location;


}
