import java.util.NoSuchElementException;

public class DoublyLinkedList 
{
    //instance variables of this class
    private ListNode head;
    private ListNode tail;
    private int length = 0;

    //constructor of this class
    //when we create an object to this class, then the following get initialised
    public DoublyLinkedList(){
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    //inner class to create the structure of a node
    private class ListNode{
        private int data;
        private ListNode next;
        private ListNode previous;

        //constructor of this class
        public ListNode(int data){
            this.data = data;
            this.next = null;
            this.previous = null;
        }
    }

    //to check is the list is empty
    public boolean isEmpty(){
        //returns true is length is 0, else returns false
        return length == 0;
    }

    //to return the length of the list
    public int length(){
        return length;
    }

    //printing values in forward direction
    public void displayForward(){
        if(head == null){
            return;
        }
        ListNode temp = head;
        while(temp != null){
            System.out.print(temp.data + " --> ");
            temp = temp.next;
        }
        System.out.print("null\n");
    }

    //printing values in reverse direction
    public void displayBackward(){
        if(tail == null){
            return;
        }
        ListNode temp = tail;
        while(temp != null){
            System.out.print(temp.data + " --> ");
            temp = temp.previous;
        }
        System.out.println("null\n");
    }

    //metod to insert values in the beginning of the list
    public void insertFirst(int value){
        ListNode newNode = new ListNode(value);
        if(isEmpty()){
            tail = newNode;
        } else{
            head.previous = newNode;
        }
        newNode.next = head;
        head = newNode;
        length++;
    }

    //inserting values at the end of the list
    public void insertLast(int value){
        ListNode newNode = new ListNode(value);
        if(isEmpty()){
            head = newNode;
        } else{
            tail.next = newNode;
            newNode.previous =tail;
        }
        tail = newNode;
        length++;
    }

    //inserting in any position in the list
    public void insertAnywhere(int position, int value){
        ListNode newNode = new ListNode(value);
        if(position == 1){
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        } else{
            //for inserting in other positions
            ListNode previous = head;
            int count = 1;
            //traverses to the node before the required position
            while(count < position-1){
                previous = previous.next;
                count++;
            }
            //if we have to insert at the last position
            if(count == length){
                tail.next = newNode;
                newNode.previous = tail;
                tail = newNode;
                length++;
                return;
            }
            ListNode current = previous.next;
            previous.next = newNode;
            newNode.previous = previous;
            newNode.next = current;
            current.previous = newNode;
            length++;
        }
    }

    //delete from the first
    public ListNode deleteFirst(){
        //if list is empty
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        ListNode temp = head;
        //if only one element
        if(head == tail){
            tail = null;
        } else{
            head.next.previous = null;
        }
        head = head.next;
        temp.next = null;
        length--;
        return temp;
    }

    //delete from the end
    public ListNode deleteLast(){
        //if list is empty
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        ListNode temp = tail;
        //only one element;
        if(head == tail){
            head = null;
        } else{
            tail.previous.next = null;
        }
        tail = tail.previous;
        temp.previous = null;
        length--;
        return temp;
    }

    public ListNode deleteAnywhere(int position){
        if(isEmpty()){
            throw new NoSuchElementException();
        } else if(position == 1){
            ListNode temp = head;
            head = head.next;
            head.previous = null;
            temp.next = null;
            length--;
            return temp;
        }else{
            ListNode previous = head;
            int count = 1;
            while(count < position - 1){
                previous = previous.next;
                count++;
            }
            ListNode delnode = previous.next;
            ListNode nextNode = previous.next.next;
            previous.next = nextNode;
            nextNode.previous = previous;
            length--;
            delnode.next = null;
            delnode.previous = null;
            return delnode;
        }
    }

    //main method
    public static void main(String args[]){
        DoublyLinkedList dll = new DoublyLinkedList();

        //inserting values in the beginning of the list
        dll.insertFirst(3);
        dll.insertFirst(2);
        dll.insertFirst(1);

        //inserting at the end
        dll.insertLast(4);
        dll.insertLast(5);
        dll.insertLast(6);

        //inserting at the middle
        dll.insertAnywhere(7, 0);

        //deleting the first node
        System.out.println(dll.deleteFirst().data + " is deleted");
        
        //delete the last node
        System.out.println(dll.deleteLast().data + " is deleted");

        //delete any node
        System.out.println(dll.deleteAnywhere(3).data + " is deleted");

        //displaying the lists
        dll.displayForward();
        dll.displayBackward();
    }
}
