package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.*;
import co.edu.umanizales.tads.exception.ListSEException;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.model.Pet;
import co.edu.umanizales.tads.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/listde")
public class ListDEController {
    @Autowired
    private ListDEService listDEService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private RangeServiceDE rangeServiceDE;
    @GetMapping
    public ResponseEntity<ResponseDTO> getPets()
    {
        return new ResponseEntity<>(new ResponseDTO(200,listDEService.getPetslist(),null), HttpStatus.OK);
    }
    @PostMapping(path = "/addde")
    public ResponseEntity<ResponseDTO> addPet(@RequestBody @Valid PetDTO petDTO){
        Location location = locationService.getLocationByCode(petDTO.getCodelocation());
        if(location == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La ubicación no existe",
                    null), HttpStatus.OK);
        }
        try {
            listDEService.getPets().addDE(
                    new Pet(petDTO.getName(),petDTO.getAge(),petDTO.getIdentification(),
                            petDTO.getRace(), petDTO.getSex(), petDTO.isShower(), location));
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se ha adicionado la mascota",
                null), HttpStatus.OK);

    }
    @PostMapping(path = "/addtostart")
    public ResponseEntity<ResponseDTO> addToStart(@RequestBody @Valid PetDTO petDTO){
        Location location = locationService.getLocationByCode(petDTO.getCodelocation());
        if(location == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La ubicación no existe",
                    null), HttpStatus.OK);
        }
        try {
            listDEService.getPets().addToStartDE(
                    new Pet( petDTO.getName(),petDTO.getAge(),petDTO.getIdentification(),
                            petDTO.getRace(),petDTO.getSex(), petDTO.isShower(), location));
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
            listDEService.getPets().addInPosicionDE(
                    new Pet( petDTO.getName(),petDTO.getAge(),petDTO.getIdentification(),
                            petDTO.getRace(),petDTO.getSex(), petDTO.isShower(), location),position);
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se ha adicionado la mascota",
                null), HttpStatus.OK);

    }
    @GetMapping(path="/invertlistde")
    public ResponseEntity<ResponseDTO> invertListDE()
    {
        try {
            listDEService.getPets().invertList();
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(200,"La lista se ha invertido",null ),HttpStatus.OK);
    }
    @GetMapping(path="/addtoendnamecharde/{letter}")
    public ResponseEntity<ResponseDTO>addToEndNameCharDE(@PathVariable String letter)
    {
        try {
            listDEService.getPets().addToEndNameChar(letter);
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409,e.getMessage(),
                    null), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseDTO(200,"Se han agregado al final los nombres que inician con la letra ingresada",null),HttpStatus.OK);
    }
    @GetMapping(path="/deletepetde/{identification}")
    public ResponseEntity<ResponseDTO>deletePet(@PathVariable String identification)
    {
        try {
            listDEService.getPets().deletePet(identification);
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(200,"Se ha eliminado la mascota",null),HttpStatus.OK);
    }
    @GetMapping(path = "/change_extremesde")
    public ResponseEntity<ResponseDTO>changeExtremesDE()
    {
        listDEService.getPets().changeExtremes();
        return new ResponseEntity<>(new ResponseDTO(200,"Se han intercambiado los extremos",null),HttpStatus.OK);
    }
    @GetMapping(path="/deletepetbyagede/{age}")
    public ResponseEntity<ResponseDTO> deletePetsByAge(@PathVariable byte age)
    {
        try {
            listDEService.getPets().deletePetsByAgeDE(age);
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(200,"Se han eliminado las mascotas de la edad dada",null),HttpStatus.OK);

    }
    @GetMapping(path="/passpositions/{identification}/{position}")
    public ResponseEntity<ResponseDTO>passPositions(@PathVariable int position, @PathVariable String identification)
    {
        try {
            listDEService.getPets().passPositions(identification,position);
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(200,"La mascota ha adelantado las posiciones deseadas",null),HttpStatus.OK);
    }
    @GetMapping(path="/lostpositionsde/{identification}/{position}")
    public ResponseEntity<ResponseDTO>lostPositions(@PathVariable int position,@PathVariable String identification)
    {
        try {
            listDEService.getPets().lostPositions(identification,position);
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(200,"La mascota ha perdido las posiciones deseadas",null),HttpStatus.OK);
    }
    @GetMapping(path ="/intercalatepets")
    public ResponseEntity<ResponseDTO>intercalatePets()
    {
        try {
            listDEService.getPets().intercalatePetBySex();
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(200,"Se han intercalado las mascotas",null),HttpStatus.OK);
    }
    @GetMapping(path="/ordermalesstostart")
    public ResponseEntity<ResponseDTO>orderMalesToStart()
    {
        try {
            listDEService.getPets().orderMaleToStart();
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(200,"Se han añadido los machos al inicio",null),HttpStatus.OK);
    }
    @GetMapping(path="/quantitypetsbyagerange")
    public ResponseEntity<ResponseDTO>getQuantityPetsByRange()
    {
        List<QuantityRangePetsDTO> quantityRangePetsDTOList= new ArrayList<>();
        for (RangePetsDTO rangepets: rangeServiceDE.getRanges())
        {
            int count= listDEService.getPets().quantityByRangeAgeDE(rangepets.getMinimum(), rangepets.getMaximum());
            if (count>0)
            {
                quantityRangePetsDTOList.add(new QuantityRangePetsDTO(rangepets,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200,quantityRangePetsDTOList,null),HttpStatus.OK);
    }

    @GetMapping(path = "/petsbylocations")
    public ResponseEntity<ResponseDTO> getPetsByLocation(){
        List<PetByLocationDTO> petsByLocationDTOList = new ArrayList<>();
        for(Location loc: locationService.getLocations())
        {
            int count = listDEService.getPets().getCountPetsByLocationCode(loc.getCode());
                petsByLocationDTOList.add(new PetByLocationDTO(loc,count));
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,petsByLocationDTOList,
                null), HttpStatus.OK);
        }
    @GetMapping(path="/averagebyage")
    public ResponseEntity<ResponseDTO>averageByAge()
    {

        return new ResponseEntity<>(new ResponseDTO(200,listDEService.getPets().averageAgeDE(),null),HttpStatus.OK);
    }
    @GetMapping(path="/deleteinposition/{identification}")
    public ResponseEntity<ResponseDTO>deleteinposition(@PathVariable String identification)
    {

        try {
            listDEService.getPets().deleteInPosition(identification);
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409,e.getMessage(),
                    null), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ResponseDTO(200,"Se ha eliminado la mascota",null),HttpStatus.OK);
    }
}

