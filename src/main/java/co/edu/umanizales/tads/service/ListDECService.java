package co.edu.umanizales.tads.service;

import co.edu.umanizales.tads.model.ListDE;
import co.edu.umanizales.tads.model.ListDEC;
import co.edu.umanizales.tads.model.NodeDE;
import co.edu.umanizales.tads.model.Pet;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Data
public class ListDECService {
    private ListDEC petsdc;
    public ListDECService() {
        petsdc = new ListDEC();
    }
    public List<Pet> getPetsListDEC() {
        List<Pet> petList = new ArrayList<>();
        NodeDE temp = petsdc.getHeadDEC();
        if (temp != null) {
            NodeDE temp2 = temp;
            while (temp2.getNext() != temp) {
                petList.add(temp2.getData());
                temp2 = temp2.getNext();
            }
            petList.add(temp2.getData());
        }
        return petList;
    }
}
