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
public class ListDE {
    private NodeDE headDE;
    private int size;

    private List<Pet>pets=new ArrayList<>();
    public NodeDE getHead() {
        return headDE;
    }

    public void addDE(Pet pet)throws ListSEException
    {
        if(headDE!=null)
        {
            NodeDE temp=headDE;
            while(temp.getNext()!=null)
            {
                if(temp.getData().getIdentification().equals(pet.getIdentification()))
                {
                    throw new ListSEException("Ya existe una mascota con esa identificacion");
                }
                temp.getNext();
            }
            if(temp.getData().getIdentification().equals(pet.getIdentification()))
            {
                throw new ListSEException("Ya existe una mascota con esa identificacion");
            }
            NodeDE newNodeDE = new NodeDE(pet);
            temp.setNext(newNodeDE);
            newNodeDE.setPrevious(temp);
        }
        else
        {
            headDE=new NodeDE(pet);
        }

    }

    public void addToStartDE(Pet pet)throws ListSEException
    {
        if (headDE!=null)
        {
            NodeDE temp = headDE;
            while(temp.getNext() !=null)
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
            newNodeDE.setNext(headDE);
            headDE.setPrevious(newNodeDE);
            headDE=newNodeDE;
        }
        else
        {
            headDE= new NodeDE(pet);
        }
    }

    public void addInPosicionDE(Pet pet, int posicion)throws ListSEException
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
    public void deletePetDE(String identification)throws ListSEException
    {
        if(headDE!=null)
        {
            ListDE listCp=new ListDE();
            NodeDE temp=headDE;
            while(temp!= null)
            {
                if(temp.getData().getIdentification().equals(identification))
                {
                    temp=temp.getNext();
                }
                listCp.addDE(temp.getData());
                temp = temp.getNext();
            }

            headDE = listCp.getHead();
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
                if (temp.getData().getAge()!= age)
                {
                    listCp.addDE(temp.getData());
                }
                temp=temp.getNext();
            }
            listCp.headDE= headDE;
        }
    }
    public void invertList() throws ListSEException
    {
        if(headDE!=null)
        {
            ListDE listCp=new ListDE();
            NodeDE temp=headDE;
            while(temp!=null)
            {
                listCp.addToStartDE(temp.getData());
                temp= temp.getNext();
            }
            headDE=listCp.getHead();
        }
    }

    public void addToEndNameChar(String letter) throws ListSEException {
        if (headDE != null)
        {
            ListDE listCp = new ListDE();
            NodeDE temp = headDE;
            while (temp != null)
            {
                if (temp.getData().getName().startsWith(letter))
                {
                    listCp.addDE(temp.getData());
                }
                else
                {
                    listCp.addToStartDE(temp.getData());
                }
                temp = temp.getNext();
            }
            headDE = listCp.getHead();
        }
    }

    public void changeExtremes()
    {
        if(headDE!=null && headDE.getNext()!=null)
        {
            NodeDE temp=headDE;
            while(temp.getNext()!=null)
            {
                temp=temp.getNext();
            }
            Pet copy=headDE.getData();
            headDE.setData(temp.getData());
            temp.setData(copy);
        }
    }
    public void orderMaleToStart()throws ListSEException {
        if(this.headDE !=null){
            ListDE listCp = new ListDE();
            NodeDE temp = this.headDE;
            while(temp != null){
                if(temp.getData().getSex().equals('M'))
                {
                    listCp.addToStartDE(temp.getData());
                }
                else{
                    listCp.addDE(temp.getData());
                }

                temp = temp.getNext();
            }
            this.headDE = listCp.getHead();
        }
    }
    public void passPositions(String identification, int position)throws ListSEException
    {
        if(headDE!=null)
        {
            NodeDE temp=headDE;
            int count=1;
            while(temp!=null && !temp.getData().equals(identification))
            {
                temp=temp.getNext();
                count++;
            }
            int positiontoadd=count-position;
            Pet petcopy=temp.getNext().getData();
            deletePetDE(temp.getNext().getData().getIdentification());
            addInPosicionDE(petcopy,positiontoadd);

        }
    }
    public void lostPositions(String identification, int position)throws ListSEException
    {
        if(headDE!=null)
        {
            NodeDE temp=headDE;
            int count=1;
            while(temp!=null && !temp.getData().equals(identification))
            {
                temp=temp.getNext();
                count++;
            }
            int positiontoadd=count+position;
            Pet petcopy=temp.getNext().getData();
            deletePetDE(temp.getNext().getData().getIdentification());
            addInPosicionDE(petcopy,positiontoadd);

        }
    }
    public void intercalatePetBySex() throws ListSEException
    {
        ListDE listM=new ListDE();
        ListDE listF=new ListDE();
        ListDE interspersedlist= new ListDE();
        NodeDE temp=headDE;
        while(temp!=null)
        {
            if(temp.getData().getSex().equals('M'))
            {
                listM.addDE(temp.getData());
            }
            else
            {
                listF.addDE(temp.getData());
            }
            temp.getNext();
        }
        NodeDE tempM=listM.getHead();
        NodeDE tempF=listF.getHead();
        NodeDE tempInterspersed= interspersedlist.headDE;
        while( tempM!=null && tempF!=null)
        {
            if(tempInterspersed.getData().getSex().equals('M'))
            {
                interspersedlist.addDE(tempF.getData());
            }
            else
            {
                interspersedlist.addDE(tempM.getData());
            }
            tempInterspersed.getNext();
        }
        headDE=interspersedlist.getHead();

    }
    public int quantityByRangeAgeDE(int min,int max)
    {
        int count = 0;
        if (headDE != null)
        {
            NodeDE temp = headDE;
            while (temp != null)
            {
                if(temp.getData().getAge()>=min && temp.getData().getAge()<=max)
                {
                    count++;
                }
            }
        }
        return count;
    }
    public int  getCountPetsByLocationCode(String code)
    {
        int count=0;
        if (this.headDE!=null)
        {
            NodeDE temp=headDE;
            while (temp!=null)
            {
                if(temp.getData().getLocation().getCode().equals(code))
                {count++;}
            }
            temp=temp.getNext();
        }
        return count;
    }
    public float averageAgeDE()
    {
        if(headDE != null)
        {
            NodeDE temp = headDE;
            int countkidsDE = 0;
            int ageskidsDE = 0;
            while (temp.getNext() != null)
            {
                countkidsDE++;
                ageskidsDE = ageskidsDE + temp.getData().getAge();
                temp = temp.getNext();
            }
            return (float) ageskidsDE/countkidsDE;
        }
        else
        {

            return 0;
        }
    }
    /*Necesito el id
    Hay datos?
    Si
     -La cabeza es el niño que estoy buscando?
      Si
       -Nombro a la cabeza al siguiente
       -Digo que si la cabeza es diferente de null, suelte su previus
      No
       -Recorro todas las mascotas hasta estar parada en la que tiene el id
       -Parado en la mascota correspondiente le digo a mi anterior que agarre a mi siguiente y si mi siguiente
        es diferente de nulo le digo que agarre a mi anterior
   No
    -No hay datos
     */

    public void deleteInPosition(String identification)throws ListSEException
    {
        if(headDE!=null)
        {
            if(headDE.getData().getIdentification().equals(identification))
            {
                headDE=headDE.getNext();
                if(headDE != null) {
                    headDE.setPrevious(null);
                }
            }
            else
            {
                NodeDE temp=headDE;
                while(temp!=null)
                {
                    if(temp.getData().getIdentification().equals(identification))
                    {
                        temp.getPrevious().setNext(temp.getNext());
                        if(temp.getNext()!=null)
                        {
                            temp.getNext().setPrevious(temp.getPrevious());
                        }
                        break;
                    }
                    if(temp.getData().getIdentification()!=identification)
                    {
                        throw new ListSEException("No existe una mascota con ese id");
                    }
                    temp = temp.getNext();
                }
            }

        }
    }






}


