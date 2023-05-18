package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.exception.ListSEException;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListDEC {
    private NodeDE headDE;
    private int size;

    private List<Pet> pets=new ArrayList<>();
    public NodeDE getHead() {
        return headDE;
    }

    /*
    Hay datos?
    Si
     -Llamo a un temporal y le digo que se pare en cabeza
     -Creo una nueva mascota
     -Le digo que acceda al previus de cabeza
     -Le digo al previus de cabeza que agarre e nuevo nodo
     -Le digo al siguiente de cabeza que agarre a nuevo nodo
     -Le digo al siguiente de nuevo nodo que agarre a la cbeza
     -le digo al previus de nuevo nodo que agarre a cabeza
    No
     -Le digo que nuevo nodo es cabeza
     */
    public void addPetDEC(Pet pet)throws ListSEException
    {
        if(headDE!=null)
        {
            NodeDE temp=headDE;
            while(!temp.getNext().equals(headDE))
            {
                if(temp.getData().getIdentification().equals(pet.getIdentification()))
                {
                    throw new ListSEException("Ya existe una mascota con esa identificacion");
                }
                temp= temp.getNext();
            }

            NodeDE newNodeDE = new NodeDE(pet);
            newNodeDE.setNext(headDE);
            headDE.getPrevious().setNext(newNodeDE);
            newNodeDE.setPrevious(headDE.getPrevious());
            headDE.setPrevious(newNodeDE);

        }
        else
        {
            headDE=new NodeDE(pet);
            headDE.setPrevious(headDE);
            headDE.setNext(headDE);
        }

    }
    public void addPetToStartDEC(Pet pet)throws ListSEException
    {
        if(headDE!=null)
        {
            NodeDE temp=headDE;
            while(!temp.getNext().equals(headDE))
            {
                if(temp.getData().getIdentification().equals(pet.getIdentification()))
                {
                    throw new ListSEException("Ya existe una mascota con esa identificacion");
                }
                temp= temp.getNext();
            }

            NodeDE newNodeDE = new NodeDE(pet);
            newNodeDE.setNext(headDE);
            headDE.getPrevious().setNext(newNodeDE);
            newNodeDE.setPrevious(headDE.getPrevious());
            headDE.setPrevious(newNodeDE);
            headDE=newNodeDE;

        }
        else
        {
            headDE=new NodeDE(pet);
            headDE.setPrevious(headDE);
            headDE.setNext(headDE);
        }

    }
    public void addInPosicionDEC(Pet pet, int posicion)throws ListSEException
    {
        if(headDE!=null)
        {
            if(posicion==1)
            {
                addPetToStartDEC(pet);
            }
            else
            {
                NodeDE temp=headDE;
                while(temp!=null)
                {
                    if(temp.getData().getIdentification().equals(pet.getIdentification()))
                    {
                        throw new ListSEException("Ya existe una mascota");
                    }
                    temp = temp.getNext();
                }
                if(temp.getData().getIdentification().equals(pet.getIdentification()))
                {
                    throw new ListSEException("Ya existe una mascota");
                }
                NodeDE newNodeDE= new NodeDE(pet);
                newNodeDE.setNext(temp.getNext());
                temp.setNext(newNodeDE);
                newNodeDE.setPrevious(temp);
            }
        }
        else headDE= new NodeDE(pet);

    }
    public void deletePetDEC(String identification)throws ListSEException
    {
        if(headDE!=null)
        {
            ListDEC listCp=new ListDEC();
            NodeDE temp=headDE;
            while(temp.getNext()!= headDE)
            {
                if(!temp.getData().getIdentification().equals(identification))
                {
                    listCp.addPetDEC(temp.getData());
                }
                temp=temp.getNext();
            }

            headDE = listCp.getHead();
        }
        else { throw  new ListSEException("No hay mascotas");}

    }

}
