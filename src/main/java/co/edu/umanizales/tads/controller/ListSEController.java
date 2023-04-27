package co.edu.umanizales.tads.controller;
import co.edu.umanizales.tads.controller.dto.KidByLocationDTO;
import co.edu.umanizales.tads.controller.dto.KidDTO;
import co.edu.umanizales.tads.controller.dto.ReportKidsLocationGenderDTO;
import co.edu.umanizales.tads.controller.dto.ResponseDTO;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.service.ListSEService;
import co.edu.umanizales.tads.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.edu.umanizales.tads.exception.ListSEException;
import java.util.List;
import java.util.ArrayList;
@RestController
@RequestMapping(path="/listse")
public class ListSEController {
    @Autowired
    private ListSEService listSEService;
    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getKids()
    {
        return new ResponseEntity<>(new ResponseDTO(200,listSEService.getKids().getHead(),null), HttpStatus.OK);
    }
    //No entiendo por que se llama antes del dto al service
    @GetMapping(path="/invertlist")
    public ResponseEntity<ResponseDTO> invertList()
    {
        listSEService.invert();
        return new ResponseEntity<>(new ResponseDTO(200,"La lista se ha invertido",null ),HttpStatus.OK);
    }
    @GetMapping(path="/addtostartnamechar")
    public ResponseEntity<ResponseDTO>addToStartNameChar(String letra)
    {
        listSEService.getKids().addToStartNameChar(letra);
        return new ResponseEntity<>(new ResponseDTO(200,"Se han agregado al inicio los nombres que inician con la letra ingresada",null),HttpStatus.OK);
    }

    @GetMapping(path="/deletekid")
    public ResponseEntity<ResponseDTO>deleteKid(Kid kid, int posicion)
    {
        listSEService.getKids().deleteKid(kid.getIdentification(),posicion);
        return new ResponseEntity<>(new ResponseDTO(200,"Se ha eliminado al niño",null),HttpStatus.OK);
    }

    @GetMapping(path = "/change_extremes")
    public ResponseEntity<ResponseDTO>changeExtremes()
    {
        listSEService.getKids().changeExtremes();
        return new ResponseEntity<>(new ResponseDTO(200,"Se han intercambiado los extremos",null),HttpStatus.OK);
    }

    @GetMapping(path="/createKid")
    public ResponseEntity<ResponseDTO>createKid(@RequestBody Kid kid)
    {
        Kid findKid=listSEService.getKids().getKidById(kid.getIdentification());
        if (findKid==null)
        {
            listSEService.getKids().add(kid);
            return new ResponseEntity<>(new ResponseDTO(200,"El niño fue agregado",null),HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(new ResponseDTO(409,"El niño ya existe",null),HttpStatus.OK);
        }
    }
    @GetMapping(path = "/kidsbylocations")
    public ResponseEntity<ResponseDTO> getKidsByLocation(){
        List<KidByLocationDTO> kidsByLocationDTOList = new ArrayList<>();
        for(Location loc: locationService.getLocations()){
            int count = listSEService.getKids().getCountKidsByLocationCode(loc.getCode());
            if(count>0){
                kidsByLocationDTOList.add(new KidByLocationDTO(loc,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,kidsByLocationDTOList,
                null), HttpStatus.OK);
    }
    @GetMapping(path = "/kidsbylocationgenders/{age}")
    public ResponseEntity<ResponseDTO> getReportKisLocationGenders(@PathVariable byte age) {
        ReportKidsLocationGenderDTO report =
                new ReportKidsLocationGenderDTO(locationService.getLocationByCodeSize(8));
        listSEService.getKids()
                .getReportKidsByLocationGendersByAge(age,report);
        return new ResponseEntity<>(new ResponseDTO(
                200,report,
                null), HttpStatus.OK);
    }

}
