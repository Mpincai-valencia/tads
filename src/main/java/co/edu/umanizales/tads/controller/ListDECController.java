package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.PetDTO;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.exception.ListSEException;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.model.Pet;
import co.edu.umanizales.tads.service.ListDECService;
import co.edu.umanizales.tads.service.ListDEService;
import co.edu.umanizales.tads.service.LocationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/listdec")
public class ListDECController {
    @Autowired
    private ListDECService listDECService;
    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getPets()
    {
        return new ResponseEntity<>(new ResponseDTO(200,listDECService.getPetsListDEC(),null), HttpStatus.OK);
    }
    @PostMapping(path = "/adddec")
    public ResponseEntity<ResponseDTO> addPet(@RequestBody @Valid PetDTO petDTO){
        Location location = locationService.getLocationByCode(petDTO.getCodelocation());
        if(location == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La ubicación no existe",
                    null), HttpStatus.OK);
        }
        try {
            listDECService.getPetsdc().addPetDEC(
                    new Pet(petDTO.getName(),petDTO.getAge(),petDTO.getIdentification(),
                            petDTO.getRace(), petDTO.getSex(),location));
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se ha adicionado la mascota",
                null), HttpStatus.OK);

    }
    @PostMapping(path = "/addtostartdec")
    public ResponseEntity<ResponseDTO> addToStart(@RequestBody @Valid PetDTO petDTO){
        Location location = locationService.getLocationByCode(petDTO.getCodelocation());
        if(location == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La ubicación no existe",
                    null), HttpStatus.OK);
        }
        try {
            listDECService.getPetsdc().addPetToStartDEC(
                    new Pet( petDTO.getName(),petDTO.getAge(),petDTO.getIdentification(),
                            petDTO.getRace(),petDTO.getSex(), location));
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se ha adicionado la mascota",
                null), HttpStatus.OK);

    }
    @PostMapping(path = "/addinposition/{position}")
    public ResponseEntity<ResponseDTO> addInPosition(@RequestBody @Valid PetDTO petDTO,@PathVariable int position){
        Location location = locationService.getLocationByCode(petDTO.getCodelocation());
        if(location == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La ubicación no existe",
                    null), HttpStatus.OK);
        }
        try {
            listDECService.getPetsdc().addInPosicionDEC(
                    new Pet( petDTO.getName(),petDTO.getAge(),petDTO.getIdentification(),
                            petDTO.getRace(),petDTO.getSex(), location),position);
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se ha adicionado la mascota",
                null), HttpStatus.OK);

    }
    @GetMapping(path="/deletepetde/{identification}")
    public ResponseEntity<ResponseDTO>deletePet(@PathVariable String identification)
    {
        try {
            listDECService.getPetsdc().deletePetDEC(identification);
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(200,"Se ha eliminado la mascota",null),HttpStatus.OK);
    }

}
