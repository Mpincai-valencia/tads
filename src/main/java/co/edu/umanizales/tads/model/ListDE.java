package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.exception.ListSEException;
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

    public void addInPosicionDE(Pet pet, int posicion)
    {
        NodeDE temp=headDE;
        if(headDE!=null)
        {
            if(posicion==1)
            {
                addToStartDE(pet);
            }
            else
            {
                for(int i=0;i<posicion-1;i++)
                {
                    temp = temp.getNext();
                }
                NodeDE newNodeDE= new NodeDE(pet);
                temp.setNext(newNodeDE);
                newNodeDE.setPrevious(temp);
            }
        }

    }
    public void deletePetDE(String identification, int posicion)//este también se puede crear con una lista copia
    {   NodeDE temp=headDE;
        if(headDE!=null)
        {
            if(headDE.getData().equals(identification))
            {
                headDE=temp.getNext();
            }
            else
            {
                int pos=1;
                while(temp != null)
                {
                    temp = temp.getNext();
                    pos++;
                    if(pos == posicion-1 )
                    {
                        break;
                    }

                }
                temp.setNext(temp.getNext().getNext());
                temp.getNext().getNext().setPrevious(temp);
            }
        }
        else
        {
            headDE=null;
        }
    }
    public void deletePetsByAgeDE(byte age)throws ListSEException
    {
        if (headDE != null)
        {
            ListDE listCp = new ListDE();
            NodeDE temp = headDE;
            while (temp != null)
            {
                if (temp.getData().!= age)
                {
                    listCp.addDE(temp.getData());
                }
                temp=temp.getNext();
            }

        }
    }





}


