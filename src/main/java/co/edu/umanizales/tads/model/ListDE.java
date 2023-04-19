package co.edu.umanizales.tads.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListDE {
    private NodeDE headDE;
    private int size;

    public NodeDE getHead() {
        return headDE;
    }

    public void addDE(Pet pet)
    {
        if(headDE!=null)
        {
            NodeDE temp=headDE;
            while(temp.getNext()!=null)
            {
                temp.getNext();
            }
            NodeDE newNodeDE= new NodeDE(pet,temp.getNext(),temp.getPrevious());
            temp.setNext(newNodeDE);
        }

    }

    public void addToStartDE(Pet pet)
    {
        if (headDE!=null)
        {
            NodeDE temp=headDE;
            NodeDE newNodeDE= new NodeDE(pet,temp.getNext(),temp.getPrevious());
            newNodeDE.setNext(headDE);
            headDE=newNodeDE;
        }
        else
        {
            NodeDE temp=headDE;
            headDE= new NodeDE(pet,temp.getNext(),temp.getPrevious());
        }
    }



}


