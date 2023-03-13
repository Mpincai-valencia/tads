package co.edu.umanizales.tads.service;
import co.edu.umanizales.tads.model.ListSE;
import co.edu.umanizales.tads.model.Kid;
import co.edu.umanizales.tads.model.Node;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@Data
@Getter

public class ListSEService {
    private ListSE kids;
    public ListSEService()
    {
        kids=new ListSE();
        kids.add(new Kid("123","Mariana",(byte)18));
        kids.add(new Kid("123","Mariana",(byte)18));
        kids.add(new Kid("123","Mariana",(byte)18));
    }
    public Node getKids()
    {
        return kids.getHead();
    }

    public Node invertList(Node head) {
        Node previous = null;
        Node temp = head;
        Node next = null;

        while (temp!= null) {
            next = temp.getNext();
            temp.setNext(previous);
            previous = temp;
            temp = next;
        }

        return previous;
    }


}
