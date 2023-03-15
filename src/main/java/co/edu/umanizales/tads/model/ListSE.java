package co.edu.umanizales.tads.model;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListSE {
    private Node head;

    public Node getHead() {
        return head;
    }

    public void add(Kid kid)
    {
        if(head!=null)
        {
            Node temp=head;
            while(temp.getNext()!=null)
            {
                temp= temp.getNext();
            }
            Node newNode= new Node(kid);
            temp.setNext(newNode);
        }
        else
        {
            head= new Node(kid);
        }
    }
    public void addToStar(Kid kid)
    {
        if(head!=null)
        {
            Node newNode= new Node(kid);
            newNode.setNext(head);
            head=newNode;
        }
        else
        {
            head= new Node(kid);
        }
    }
    public void addInPosicion(Kid kid, int posicion)
    {
        Node temp=head;
        if(head!=null)
        {
            if(posicion==1)
            {
                addToStar(kid);
            }
            else
            {
                for(int i=0;i<posicion-1;i++)
                {
                    temp = temp.getNext();
                }
                Node newNode= new Node(kid);
                temp.setNext(newNode);
            }
        }

    }

    public void deleteKid(String identification, int posicion)
    {   Node temp=head;
        if(head!=null)
        {
            if(head.getData().equals(identification))
            {
                head=temp.getNext();
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
            }
        }
        else
        {
            head=null;
        }
    }
    public void invertList()
    {
        if(head!=null)
        {
         ListSE listCp=new ListSE();
         Node temp=head;
         while(temp!=null)
         {
             listCp.addToStar(temp.getData());
             temp= temp.getNext();
         }
         head=listCp.getHead();
        }
    }
    public void addToStartNameChar(String letra)
    {

        if(head!=null)
        {
            ListSE listCp=new ListSE();
            Node temp=head;
            if(temp.getData().getName().startsWith(letra))
            {
                listCp.add(temp.getData());
                temp=temp.getNext();
            }
            else
            {
                listCp.addToStar(temp.getData());
                temp=temp.getNext();
            }
            head=listCp.getHead();
        }
    }


}

