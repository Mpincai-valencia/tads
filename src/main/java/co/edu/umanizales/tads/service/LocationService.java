package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.model.Location;
import lombok.Data;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
@Service
@Data
public class LocationService {
    private List<Location>locations;

    public LocationService() //Conectar a una base de datos
    {
        locations= new ArrayList<>();
        locations.add(new Location("169","Colombia"));
        locations.add(new Location("16905","Antioquia"));
        locations.add(new Location("16917","Caldas"));
        locations.add(new Location("16963","Risaralda"));
        locations.add(new Location("16905001","Medellín"));
        locations.add(new Location("16963001","Pereira"));
        locations.add(new Location("16917001","Manizales"));
        locations.add(new Location("16917003","Chinchiná"));
    }
}
