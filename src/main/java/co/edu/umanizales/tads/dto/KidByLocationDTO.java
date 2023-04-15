package co.edu.umanizales.tads.dto;

import co.edu.umanizales.tads.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KidByLocationDTO {
    private Location location;
    private int quantity;
}

