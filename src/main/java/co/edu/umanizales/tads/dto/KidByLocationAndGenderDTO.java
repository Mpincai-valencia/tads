package co.edu.umanizales.tads.dto;

import co.edu.umanizales.tads.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
public class KidByLocationAndGenderDTO {

    private Location city;
    private List<KidByGenderDTO> genders;

    private int total;

    public KidByLocationAndGenderDTO(Location city) {
        this.city = city;
        this.total = 0;
        this.genders = new ArrayList<>();
        this.genders.add(new KidByGenderDTO('M',0));
        this.genders.add(new KidByGenderDTO('F',0));
    }
}
