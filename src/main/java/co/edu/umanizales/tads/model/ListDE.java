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
            NodeDE newNodeDE= new NodeDE(pet);
            temp.setNext(newNodeDE);
            newNodeDE.setPrevious(temp);
        }
        else
        {
            headDE=new NodeDE(pet);
        }

    }

    public void addToStartDE(Pet pet)
    {
        if (headDE!=null)
        {

            NodeDE newNodeDE= new NodeDE(pet);
            newNodeDE.setNext(headDE);
            headDE.setPrevious(newNodeDE);
            headDE=newNodeDE;
        }
        else
        {
            headDE= new NodeDE(pet);
        }
    }



}


