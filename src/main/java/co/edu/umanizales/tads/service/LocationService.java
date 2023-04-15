package co.edu.umanizales.tads.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import java.util.List;
import javax.xml.stream.Location;
import java.util.ArrayList;
@Service
@Data
public class LocationService {
    private List<Location> locations;

    public LocationService() //Conectar a una base de datos
    {

    }
}
