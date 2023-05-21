package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.exception.ListSEException;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListDEC {
    private NodeDE headDEC;
    private int size;

    private List<Pet> pets=new ArrayList<>();
    public NodeDE getHeadDEC() {
        return headDEC;
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
        if(headDEC!=null)
        {
            NodeDE temp=headDEC;
            while(temp.getNext()!=(headDEC))
            {
                if(temp.getData().getIdentification().equals(pet.getIdentification()))
                {
                    throw new ListSEException("Ya existe una mascota con esa identificacion");
                }
                temp= temp.getNext();
            }
            NodeDE newNodeDE = new NodeDE(pet);
            newNodeDE.setNext(headDEC);
            headDEC.getPrevious().setNext(newNodeDE);
            newNodeDE.setPrevious(headDEC.getPrevious());
            headDEC.setPrevious(newNodeDE);

        }
        else
        {
            headDEC=new NodeDE(pet);
            headDEC.setPrevious(headDEC);
            headDEC.setNext(headDEC);
        }

    }
    public void addPetToStartDEC(Pet pet)throws ListSEException
    {
        if(headDEC!=null)
        {
            NodeDE temp=headDEC;
            while(!temp.getNext().equals(headDEC))
            {
                if(temp.getData().getIdentification().equals(pet.getIdentification()))
                {
                    throw new ListSEException("Ya existe una mascota con esa identificacion");
                }
                temp= temp.getNext();
            }

            NodeDE newNodeDE = new NodeDE(pet);
            newNodeDE.setNext(headDEC);
            headDEC.getPrevious().setNext(newNodeDE);
            newNodeDE.setPrevious(headDEC.getPrevious());
            headDEC.setPrevious(newNodeDE);
            headDEC=newNodeDE;

        }
        else
        {
            headDEC=new NodeDE(pet);
            headDEC.setPrevious(headDEC);
            headDEC.setNext(headDEC);
        }

    }
    public void addInPosicionDE(Pet pet, int posicion)throws ListSEException
    {
        NodeDE temp=headDEC;
        if(headDEC!=null)
        {
            if(posicion==1)
            {
                addPetToStartDEC(pet);
            }
            else
            {
                for(int i=1;i<posicion-1;i++)
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
        else headDEC= new NodeDE(pet);

    }
    public void deletePetDEC(String identification)throws ListSEException
    {
        if(headDEC!=null)
        {
            ListDEC listCp=new ListDEC();
            NodeDE temp=headDEC;
            while(temp.getNext()!= headDEC)
            {
                if(!temp.getData().getIdentification().equals(identification))
                {
                    listCp.addPetDEC(temp.getData());
                }
                temp=temp.getNext();
            }
            if(!temp.getData().getIdentification().equals(identification))
            {
                listCp.addPetDEC(temp.getData());
            }

            headDEC = listCp.getHeadDEC();
        }
        else { throw  new ListSEException("No hay mascotas");}

    }
    public void showerPetsByLeft()throws ListSEException
    {
        if(headDEC!=null)
        {
            NodeDE temp=headDEC;
            Random random = new Random();
            int count=1;
            while(count!= random.nextInt())
            {
                temp=temp.getNext();
                count++;
            }
            temp.getData().setShower(true);
        }
        else { throw  new ListSEException("No hay mascotas");}

    }
    public void showerPetsByRight()throws ListSEException
    {
        if(headDEC!=null)
        {
            NodeDE temp=headDEC;
            Random random = new Random();
            int count=1;
            while(count!= random.nextInt())
            {
                temp=temp.getPrevious();
                count++;
            }
            temp.getData().setShower(true);
        }
        else { throw  new ListSEException("No hay mascotas");}

    }


}
