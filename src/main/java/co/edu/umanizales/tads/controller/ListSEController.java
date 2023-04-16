package co.edu.umanizales.tads.controller;
import co.edu.umanizales.tads.dto.ResponseDTO;
import co.edu.umanizales.tads.model.ListSE;
import co.edu.umanizales.tads.model.Node;
import co.edu.umanizales.tads.service.ListSEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/listse")
public class ListSEController {
    @Autowired
    private ListSEService listSEService;

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
    @GetMapping(path = "/change_extremes")
    public ResponseEntity<ResponseDTO>changeExtremes()
    {
        listSEService.getKids().changeExtremes();
        return new ResponseEntity<>(new ResponseDTO(200,"Se han intercambiado los extremos",null),HttpStatus.OK);
    }

}
