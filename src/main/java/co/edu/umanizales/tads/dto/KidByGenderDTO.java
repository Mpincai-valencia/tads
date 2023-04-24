package co.edu.umanizales.tads.dto;

import co.edu.umanizales.tads.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KidByGenderDTO {

    private KidByLocationDTO data;
    private int quantitygenderM;
    private int quantitygenderF;

}
