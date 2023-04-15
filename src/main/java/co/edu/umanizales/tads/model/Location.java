package co.edu.umanizales.tads.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class Location {
    private String code;
    private String name;
}
