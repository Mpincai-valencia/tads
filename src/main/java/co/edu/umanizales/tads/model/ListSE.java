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
            if(head.getClass().equals(posicion))
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

    public void deleteKid(String Identification, int posicion)
    {   Node temp=head;
        if(head!=null)
        {
            if(head.getData().equals(Identification))
            {
                head=null;
                head=temp.getNext();
            }
            else
            {
                for (int i = 0; i < posicion-1; i++)
                {
                    temp = temp.getNext();
                }
                temp.setNext(temp.getNext());
            }
        }
        else
        {
            head=null;
        }

    }

}

