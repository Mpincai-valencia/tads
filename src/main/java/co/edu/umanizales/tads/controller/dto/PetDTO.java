package co.edu.umanizales.tads.controller.dto;

import co.edu.umanizales.tads.model.Location;
import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class PetDTO {
    @NotNull
    @Size(min = 5, max = 30,message = "El nombre debe contener un mínimo 5 letras y un máximo de 30")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Sólo se permiten letras en este campo")
    private String name;
    @Min(1)
    @Max(15)
    private byte age;
    private String identification;
    private String race;
    @Pattern(regexp = "^[MF]$", message = "El género debe ser M o F")
    private String sex;
    private boolean shower;
    private String codelocation;


}
