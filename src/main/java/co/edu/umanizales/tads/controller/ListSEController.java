package co.edu.umanizales.tads.controller;
import co.edu.umanizales.tads.controller.dto.*;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.ListSE;
import co.edu.umanizales.tads.model.Location;
import co.edu.umanizales.tads.service.ListSEService;
import co.edu.umanizales.tads.service.LocationService;
import co.edu.umanizales.tads.service.RangeService;
import jakarta.validation.Valid;
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

    @Autowired
    private RangeService rangeService;

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
    @GetMapping(path="/addtostartnamechar/{letter}")
    public ResponseEntity<ResponseDTO>addToStartNameChar(String letter)
    {
        try {
            listSEService.getKids().addToEndNameChar(letter);
            } catch (ListSEException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(new ResponseDTO(200,"Se han agregado al inicio los nombres que inician con la letra ingresada",null),HttpStatus.OK);
    }

    @GetMapping(path="/deletekid/{identificayion}/{position}")
    public ResponseEntity<ResponseDTO>deleteKid(Kid kid, int position)
    {
        try {
            listSEService.getKids().deleteKid(kid.getIdentification(),position);
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(200,"Se ha eliminado al niño",null),HttpStatus.OK);
    }

    @GetMapping(path = "/change_extremes")
    public ResponseEntity<ResponseDTO>changeExtremes()
    {
        listSEService.getKids().changeExtremes();
        return new ResponseEntity<>(new ResponseDTO(200,"Se han intercambiado los extremos",null),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ResponseDTO> addKid(@RequestBody @Valid KidDTO kidDTO){
        Location location = locationService.getLocationByCode(kidDTO.getCodeLocation());
        if(location == null){
            return new ResponseEntity<>(new ResponseDTO(
                    404,"La ubicación no existe",
                    null), HttpStatus.OK);
        }
        try {
            listSEService.getKids().add(
                    new Kid(kidDTO.getIdentification(),
                            kidDTO.getName(), kidDTO.getAge(),
                            kidDTO.getGender(), location));
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(
                200,"Se ha adicionado el petacón",
                null), HttpStatus.OK);

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
    public ResponseEntity<ResponseDTO> getReportKisLocationGenders(byte age) {
        ReportKidsLocationGenderDTO report =
                new ReportKidsLocationGenderDTO(locationService.getLocationByCodeSize(8));
        listSEService.getKids()
                .getReportKidsByLocationGendersByAge(age,report);
        return new ResponseEntity<>(new ResponseDTO(
                200,report,
                null), HttpStatus.OK);
    }
    @GetMapping(path="/deletekidbyage/{age}")
    public ResponseEntity<ResponseDTO> deleteKidsByAge(byte age)
    {

        try {
            listSEService.getKids().deleteKidsByAge(age);
        } catch (ListSEException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(new ResponseDTO(200,"Se han eliminado los niños de la edad dada",null),HttpStatus.OK);

    }

    @GetMapping(path="/quantitykidsbyagerange")
    public ResponseEntity<ResponseDTO>getQuantityKidsByRange()
    {
        List<QuantityRangeKidsDTO> quantityRangeKidsDTOList= new ArrayList<>();
        for (RangeKidsDTO range: rangeService.getRanges())
        {
            int count= listSEService.getKids().quantityByRangeAge(range.getMinimum(), range.getMaximum());
            if (count>0)
            {
                quantityRangeKidsDTOList.add(new QuantityRangeKidsDTO(range,count));
            }
        }
        return new ResponseEntity<>(new ResponseDTO(200,quantityRangeKidsDTOList,null),HttpStatus.OK);
    }

    @GetMapping(path="passpositions/{identification}/{postion}")
    public ResponseEntity<ResponseDTO>passPositions(int position,String identification)
    {
        try {
            listSEService.getKids().passPositions(identification,position);
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(200,"El niño ha adelantado las posiciones deseadas",null),HttpStatus.OK);
    }
    @GetMapping(path="lostpositions/{identification}/{postion}")
    public ResponseEntity<ResponseDTO>lostPositions(int position,String identification)
    {
        try {
            listSEService.getKids().lostPositions(identification,position);
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(200,"El niño ha perdido las posiciones deseadas",null),HttpStatus.OK);
    }
    @GetMapping(path ="intercalatekids")
    public ResponseEntity<ResponseDTO>intercalateKids()
    {
        try {
            listSEService.getKids().intercalateKidByGender();
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(200,"Se han intercalado los niños",null),HttpStatus.OK);
    }
    @GetMapping(path="orderboystostart")
    public ResponseEntity<ResponseDTO>orderBoysToStart()
    {
        try {
            listSEService.getKids().orderBoysToStart();
        } catch (ListSEException e) {
            return new ResponseEntity<>(new ResponseDTO(
                    409,e.getMessage(),
                    null), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseDTO(200,"Se han añadido los niños al inicio",null),HttpStatus.OK);
    }
    @GetMapping(path="averagebyage")
    public ResponseEntity<ResponseDTO>averageByAge()
    {

        return new ResponseEntity<>(new ResponseDTO(200,listSEService.getKids().averageAge(),null),HttpStatus.OK);
    }
}
