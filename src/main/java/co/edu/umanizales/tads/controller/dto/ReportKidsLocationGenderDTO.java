package co.edu.umanizales.tads.controller.dto;
import co.edu.umanizales.tads.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data

public class ReportKidsLocationGenderDTO {

    private List<LocationGenderQuantityDTO>locationGenderQuantityDTOList;
    public ReportKidsLocationGenderDTO(List<Location> cities)
    {
        locationGenderQuantityDTOList=new ArrayList<>();
        for(Location location: cities)
        {
            locationGenderQuantityDTOList.add(new LocationGenderQuantityDTO(location.getName()));//Por qué no se ingresaron los demás parámtros
        }

    }
    public void updateQuantity(String city,String gender){
        for(LocationGenderQuantityDTO loc:locationGenderQuantityDTOList){
            if(loc.getCity().equals(city)){
                for(GenderQuantityDTO genderDTO: loc.getGenders()){
                    if(genderDTO.getGender().equals(gender)){
                        genderDTO.setQuantity(genderDTO.getQuantity()+1);
                        loc.setTotal(loc.getTotal()+1);
                        return;
                    }
                }
            }
        }
    }
}
