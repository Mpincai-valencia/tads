package co.edu.umanizales.tads.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KidByLocationAndGenderDTO {

    private KidByGenderDTO kidByGenderDTO;
    private KidByLocationDTO kidByLocationDTO;
}
