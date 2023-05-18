package co.edu.umanizales.tads.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class Pet
{
    private String name;
    private byte age;
    private String identification;
    private String race;
    private String sex;
    private Location location;
}
