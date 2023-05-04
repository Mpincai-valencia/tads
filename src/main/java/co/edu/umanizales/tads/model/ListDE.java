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
                if (temp.getData().getAge()!= age)
                {
                    listCp.addDE(temp.getData());
                }
                temp=temp.getNext();
            }
            listCp.headDE= headDE;
        }
    }
    public void invertList()
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

    public void addToEndNameChar(String letra) throws ListSEException
    {

        if(headDE!=null)
        {
            ListDE listCp=new ListDE();
            NodeDE temp=headDE;
            if(temp.getData().getName().startsWith(letra))
            {
                listCp.addDE(temp.getData());
                temp=temp.getNext();
            }
            else
            {
                listCp.addToStartDE(temp.getData());
                temp=temp.getNext();
            }
            headDE=listCp.getHead();
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
                if(temp.getData().getSex()=='M')
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
    public void passPositions(String identification, int position)
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
            deletePetDE(temp.getNext().getData().getIdentification(),count);
            addInPosicionDE(petcopy,positiontoadd);

        }
    }
    public void lostPositions(String identification, int position)
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
            deletePetDE(temp.getNext().getData().getIdentification(),count);
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
            if(temp.getData().getSex()=='M')
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
            if(tempInterspersed.getData().getSex()=='M')
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






}


