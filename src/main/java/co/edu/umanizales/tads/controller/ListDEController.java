package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.controller.dto.*;
import co.edu.umanizales.tads.exception.ListSEException;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.model.Pet;
import co.edu.umanizales.tads.service.*;
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
    @GetMapping(path="/invertlistde")
    public ResponseEntity<ResponseDTO> invertListDE()
    {
        listDEService.invertDE();
        return new ResponseEntity<>(new ResponseDTO(200,"La lista se ha invertido",null ),HttpStatus.OK);
    }
    @GetMapping(path="/addtostartnamecharde")
    public ResponseEntity<ResponseDTO>addToStartNameCharDE(String letra)
    {

        try {
            listDEService.getPets().addToEndNameChar(letra);
        } catch (ListSEException e) {
            throw new RuntimeException(e);
        }

        return new ResponseEntity<>(new ResponseDTO(200,"Se han agregado al inicio los nombres que inician con la letra ingresada",null),HttpStatus.OK);
    }
    @GetMapping(path="/deletekidde")
    public ResponseEntity<ResponseDTO>deletePet(Pet pet, int posicion)
    {
        listDEService.getPets().deletePetDE(pet.getIdentification(),posicion);
        return new ResponseEntity<>(new ResponseDTO(200,"Se ha eliminado la mascota",null),HttpStatus.OK);
    }
    @GetMapping(path = "/change_extremesde")
    public ResponseEntity<ResponseDTO>changeExtremesDE()
    {
        listDEService.getPets().changeExtremes();
        return new ResponseEntity<>(new ResponseDTO(200,"Se han intercambiado los extremos",null),HttpStatus.OK);
    }
    @GetMapping(path="/deletepetbyage")
    public ResponseEntity<ResponseDTO> deletePetsByAge(byte age)
    {
        try {
            listDEService.getPets().deletePetsByAgeDE(age);
        } catch (ListSEException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(new ResponseDTO(200,"Se han eliminado los niños de la edad dada",null),HttpStatus.OK);

    }
    @GetMapping(path="passpositions")
    public ResponseEntity<ResponseDTO>passPositions(int position,String identification)
    {
        listDEService.getPets().passPositions(identification,position);
        return new ResponseEntity<>(new ResponseDTO(200,"La mascots ha adelantado las posiciones deseadas",null),HttpStatus.OK);
    }
    @GetMapping(path="lostpositions")
    public ResponseEntity<ResponseDTO>lostPositions(int position,String identification)
    {
        listDEService.getPets().lostPositions(identification,position);
        return new ResponseEntity<>(new ResponseDTO(200,"La mascota ha perdido las posiciones deseadas",null),HttpStatus.OK);
    }
    @GetMapping(path ="intercalatepets")
    public ResponseEntity<ResponseDTO>intercalatePets()
    {
        try {
            listDEService.getPets().intercalatePetBySex();
        } catch (ListSEException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(new ResponseDTO(200,"Se han intercalado las mascotas",null),HttpStatus.OK);
    }
    @GetMapping(path="ordermalesstostart")
    public ResponseEntity<ResponseDTO>orderMalesToStart()
    {
        try {
            listDEService.getPets().orderMaleToStart();
        } catch (ListSEException e) {
            throw new RuntimeException(e);
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

}

