package co.edu.umanizales.tads.controller;

import co.edu.umanizales.tads.dto.KidByLocationDTO;
import co.edu.umanizales.tads.dto.KidDTO;
import co.edu.umanizales.tads.dto.ResponseDTO;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.service.ListSEService;
import co.edu.umanizales.tads.service.LocationService;
import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping(path="/location")

public class LocationController {
    @Autowired
    private LocationService locationService;
    @Autowired
    private ListSEService listSEService;
    @GetMapping
    public ResponseEntity<ResponseDTO> getAllLocations()
    {
        return new ResponseEntity<>(new ResponseDTO(200, locationService.getLocations(),null), HttpStatus.OK);
    }
    @GetMapping(path="/countries")
    public ResponseEntity<ResponseDTO>getCountries()
    {
        return new ResponseEntity<>(new ResponseDTO(200, locationService.getLocationByCodeSize(3),null),HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<ResponseDTO>getDepartments()
    {
        return new ResponseEntity<ResponseDTO>(new ResponseDTO(200, locationService.getLocationByCodeSize(5),null),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO>addKid(@RequestBody KidDTO kidDTO)
    {
        Location location= locationService.getLocationByCode(kidDTO.getCodeLocation());
        if(location==null)
        {
            return new ResponseEntity<>(new ResponseDTO(404,"La ubicación no existe",null),HttpStatus.OK);
        }
        listSEService.getKids().add(
        new Kid(kidDTO.getIdentification(), kidDTO.getName(), kidDTO.getAge(),kidDTO.getGender(),location));
        return new ResponseEntity<>(new ResponseDTO(200,"Se ha adicionado al niño",null),HttpStatus.OK);
    }
    @GetMapping(path="/kidsbylocation")
    public ResponseEntity<ResponseDTO>getKidsByLocation()
    {
        List<KidByLocationDTO>kidByLocationDTOSList=new ArrayList<>();
        for(Location loc:locationService.getLocations())
        {
            int count=listSEService.getKids().getCountKidsByLocationCode(loc.getCode());
            if(count>0)
            {
                kidByLocationDTOSList.add(new KidByLocationDTO(loc,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200, kidByLocationDTOSList,null),HttpStatus.OK);
    }

}
