package co.edu.umanizales.tads.model;

import co.edu.umanizales.tads.controller.dto.ReportKidsLocationGenderDTO;
import co.edu.umanizales.tads.exception.ListSEException;
import lombok.*;

@Data

public class ListSE {
    private Node head;
    private int size;
    public Node getHead() {
        return head;
    }

    public void add(Kid kid) throws ListSEException {
        if(head != null){
            Node temp = head;
            while(temp.getNext() !=null)
            {
                if(temp.getData().getIdentification().equals(kid.getIdentification()))
                {
                    throw new ListSEException("Ya existe un niño");
                }
                temp = temp.getNext();

            }
            if(temp.getData().getIdentification().equals(kid.getIdentification())){
                throw new ListSEException("Ya existe un niño");
            }
            /// Parado en el último
            Node newNode = new Node(kid);
            temp.setNext(newNode);
        }
        else {
            head = new Node(kid);
        }
        size ++;
    }

    public void addToStart(Kid kid)throws ListSEException
    {
        if(head!=null)
        {
            Node temp = head;
            while(temp.getNext() !=null)
            {
                if(temp.getData().getIdentification().equals(kid.getIdentification()))
                {
                    throw new ListSEException("Ya existe un niño");
                }
                temp = temp.getNext();

            }
            if(temp.getData().getIdentification().equals(kid.getIdentification()))
            {
                throw new ListSEException("Ya existe un niño");
            }
            Node newNode= new Node(kid);
            newNode.setNext(head);
            head=newNode;
        }
        else
        {
            head= new Node(kid);
        }
    }
    public void addInPosicion(Kid kid, int posicion)throws ListSEException
    {
        Node temp=head;
        if(head!=null)
        {

            if(posicion==1)
            {
                addToStart(kid);
            }
            else
            {
                for(int i=1;i<posicion-1;i++)
                {
                    temp = temp.getNext();
                }
                Node newNode= new Node(kid);
                newNode.setNext(temp.getNext());
                temp.setNext(newNode);

            }
        }
        else head= new Node(kid);

    }

    public void deleteKid(String identification)throws ListSEException
    {
        if(head!=null)
        {
            ListSE listCp=new ListSE();
            Node temp=head;
            while(temp!= null)
            {
                if(!temp.getData().getIdentification().equals(identification))
                {
                    listCp.add(temp.getData());
                }
                temp=temp.getNext();
            }

            head = listCp.getHead();
        }
        else { throw  new ListSEException("No hay niños");}

    }
    public void deleteKidsByAge(byte age)throws ListSEException
    {
        if (head != null)
        {
            ListSE listCp = new ListSE();
            Node temp = head;
            while (temp != null)
            {
                if (temp.getData().getAge() != age)
                {
                    listCp.add(temp.getData());
                }
                temp=temp.getNext();
            }
            head = listCp.getHead();
        }
        else { throw  new ListSEException("No hay niños");}
    }
    public void invertList()throws ListSEException
    {
        if(head!=null)
        {
         ListSE listCp=new ListSE();
         Node temp=head;
         while(temp!=null)
         {
             listCp.addToStart(temp.getData());
             temp= temp.getNext();
         }
         head=listCp.getHead();
        }
        else { throw  new ListSEException("No hay niños");}
    }
    public void addToEndNameChar(String letter) throws ListSEException {
        if (head != null)
        {
            ListSE listCp = new ListSE();
            Node temp = head;
            while (temp != null)
            {
                if (temp.getData().getName().startsWith(letter))
                {
                    listCp.add(temp.getData());
                }
                else
                {
                    listCp.addToStart(temp.getData());
                }
                temp = temp.getNext();
            }
            head = listCp.getHead();
        }
        else { throw  new ListSEException("No hay niños");}
    }
    public void changeExtremes()
    {
        if(this.head!=null && this.head.getNext()!=null)
        {
            Node temp=this.head;
            while(temp.getNext()!=null)
            {
                temp=temp.getNext();
            }
            Kid copy=this.head.getData();
            this.head.setData(temp.getData());
            temp.setData(copy);
        }
    }

    public void orderBoysToStart()throws ListSEException {
        if(this.head !=null){
            ListSE listCp = new ListSE();
            Node temp = this.head;
            while(temp != null){
                if(temp.getData().getGender().equals("M"))
                {
                    listCp.addToStart(temp.getData());
                }
                else{
                    listCp.add(temp.getData());
                }

                temp = temp.getNext();
            }
            this.head = listCp.getHead();
        }
        else { throw  new ListSEException("No hay niños");}
    }

    public int  getCountKidsByLocationCode(String code)
    {
        int count=0;
        if (this.head!=null)
        {
            Node temp=head;
            while (temp!=null)
            {
                if(temp.getData().getLocation().getCode().equals(code))
                {count++;}
                temp=temp.getNext();
            }

        }
        return count;

    }


    public int getCountKidsByLocationSize(int size)
    {
        int count=0;
        if (head!=null)
        {
            Node temp=head;
            while(temp!=null)
            {
                if(temp.getData().getLocation().getCode().length()==size)
                {count++;}
                temp=temp.getNext();
            }

        }
        return count;
    }

    public Kid getKidById(String code)
    {
        if(head!=null)
        {
            if(head.getData().equals(code))
            {
                return head.getData();
            }
            else {
                Node temp = head;
                while (temp.getNext() != null) {
                    temp = temp.getNext();
                    if (temp.getData().equals(code)) {
                        return temp.getData();
                    }
                    break;
                }
            }
        }
        return null;
    }
    public void getReportKidsByLocationGendersByAge(byte age, ReportKidsLocationGenderDTO report) {
        if(head !=null){
            Node temp = this.head;
            while(temp!=null)
            {
                if(temp.getData().getAge()>age)
                {
                    report.updateQuantity(temp.getData().getLocation().getName(), temp.getData().getGender());
                }
                temp = temp.getNext();
            }
        }

    }

    public int quantityByRangeAge(int min,int max)
    {
        int count = 0;
        if (head != null)
        {
            Node temp = head;
            while (temp!= null)
            {
                if(temp.getData().getAge()>=min && temp.getData().getAge()<=max)
                {
                    count++;
                }
                temp=temp.getNext();
            }
        }
        return count;
    }

    public void passPositions(String identification, int position)throws ListSEException
    {
        if(head!=null)
        {
            Node temp=head;
            int count=1;
            while(temp.getNext()!=null && !temp.getNext().getData().getIdentification().equals(identification))
            {
                temp=temp.getNext();
                count++;
            }
            if(count<position)
            {
                throw new ListSEException("No se puede adelantar este numero de posiciones");
            }
            int positiontoadd=(count+1)-position;
            Kid kidcopy=temp.getNext().getData();
            deleteKid(kidcopy.getIdentification());
            addInPosicion(kidcopy,positiontoadd);

        }
        else { throw  new ListSEException("No hay niños");}
    }
    public void lostPositions(String identification, int position)throws ListSEException
    {
        if(head!=null)
        {

            Node temp=head;
            if(head.getData().getIdentification().equals(identification))
            {
                Kid headcopy=temp.getData();
                deleteKid(headcopy.getIdentification());
                addInPosicion(headcopy,(position+1));
            }
            else
            {
                int count = 1;
                while (temp.getNext() != null && !temp.getNext().getData().getIdentification().equals(identification)) {
                    temp = temp.getNext();
                    count++;
                }
                int positiontoadd = (count + 1) + position;
                Kid kidcopy = temp.getNext().getData();
                deleteKid(kidcopy.getIdentification());
                addInPosicion(kidcopy, positiontoadd);
            }
        }
        else { throw  new ListSEException("No hay niños");}
    }
    public void intercalateKidByGender() throws ListSEException
    {
        if(head!=null)
        {
            ListSE listM = new ListSE();
            ListSE listF = new ListSE();
            ListSE interspersedlist = new ListSE();
            Node temp = head;
            while (temp != null) {
                if (temp.getData().getGender().equals("M")) {
                    listM.add(temp.getData());
                } else {
                    listF.add(temp.getData());
                }
                temp = temp.getNext();
            }
            Node tempM = listM.getHead();
            Node tempF = listF.getHead();

            while (tempM != null && tempF != null) {
                interspersedlist.add(tempF.getData());
                interspersedlist.add(tempM.getData());
                tempF = tempF.getNext();
                tempM = tempM.getNext();
            }
            while (tempF != null) {
                interspersedlist.add(tempF.getData());
                tempF = tempF.getNext();
            }

            while (tempM != null) {
                interspersedlist.add(tempM.getData());
                tempM = tempM.getNext();
            }
            head = interspersedlist.getHead();
        }
        else { throw  new ListSEException("No hay niños");}
    }
    public float averageAge()
    {
        if(head != null)
        {
            Node temp = head;
            int countkids = 0;
            int ageskids = 0;
            while (temp != null)
            {
                countkids++;
                ageskids = ageskids + temp.getData().getAge();
                temp = temp.getNext();
            }
            return (float) ageskids/countkids;
        }
        else
        {

            return 0;
        }
    }
}



