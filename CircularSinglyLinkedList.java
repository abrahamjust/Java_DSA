import java.util.NoSuchElementException;

public class CircularSinglyLinkedList 
{
    private ListNode last;
    private int length;

    private class ListNode{
        
        private ListNode next;
        private int data;

        public ListNode(int data){
            this.data = data;
        }
    }

    public CircularSinglyLinkedList(){
        last = null;
        length = 0;
    }

    public int length(){
        return  length;
    }

    public boolean isEmpty(){
        return length == 0;
    }

    public void createCircularSinglyLinkedList(){
        ListNode first = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode fourth = new ListNode(4);

        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = first;
        last = fourth;

        length = 4;
    }

    //method to display the cicular singly linked list
    public void display(){
        //if list is empty
        if(last == null){
            return;
        }
        //last.next is nothing but the head node or the first node
        ListNode first = last.next;
        while(first != last){
            System.out.print(first.data + " ");
            first = first.next;
        }
        //as last node doesn't get it's data printed
        System.out.print(first.data);
    }

    //inserting a node at the beginning of the list
    public void insertFirst(int value){
        ListNode temp = new ListNode(value);
        //if list is empty
        if(last == null){
            //as there are no nodes, last node is now temp
            last = temp;
        } else{
            //inseting the node at the beginning
            //temp is linked with the first node.
            //now both last and temp both link to first node
            temp.next = last.next;
        }
        //the first node now is made as temp
        //last node is now linked with temp
        //if there was no nodes except the one inserted now, the node now points to itself
        last.next = temp;
        length++;
    }

    //inserting a node at the end of the list
    public void insertLast(int value){
        ListNode temp = new ListNode(value);
        //if list is empty
        if(last == null){
            //then newly created list becomes last node
            last = temp;
            last.next = last;
        } else{
            temp.next = last.next;
            last.next = temp;
            last = temp;
        }
        length++;
    }

    //delete the first node from the list
    public ListNode deleteFirst(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        //temp points to first node
        ListNode temp = last.next;
        //only one node in the list
        if(last.next == last){
            last = null;
        } else{
            //the first node is de referenced and last.next now points to the next to first node
            //that is link to last node and first node is broken
            last.next = temp.next;
        }
        //removing the links of the deleted node
        //deleted node now points to null
        temp.next = null;
        length--;
        return temp;
    }

    public static void main(String[] args) {
        CircularSinglyLinkedList csll = new CircularSinglyLinkedList();

        //creating a circular singly linked list
        csll.createCircularSinglyLinkedList();

        //inserting a node in the beginning of the list
        csll.insertFirst(0);

        //inserting a node at the end of the list
        csll.insertLast(5);

        //deleting the first element from the list
        System.out.println(csll.deleteFirst().data + " is deleted");

        //displaying the list
        csll.display();
    }
}
