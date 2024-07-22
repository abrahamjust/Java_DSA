public class MergeSortedSLL
{
    //code for the Singly linked list declaration
    private ListNode head;
    static class ListNode
    {
        private int data;
        private ListNode next;
        ListNode(int data){
            this.data = data;
            this.next = null;
        }
    }

    //used for inserting values to the sll while creating it
    public void insertLast(int value){
        ListNode newnode = new ListNode(value);
        //if head is null, sll is empty so make newnode as the head node
        if(head == null){
            head = newnode;
            return;
        }
        ListNode current = head;
        //traverse the node till we reach the last node
        //lastnode has no next node connected to it, so current.next will be null
        while(current.next != null){
            current = current.next;
        }
        //connect the last node to newnode.
        current.next = newnode;
    }

    //method to display the elements
    public void display(){
        ListNode current = head;
        //if current = null then end of the sll
        while(current != null){
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.print("null\n");
    }

    //merging two sorted lists using merge() using dummy
    public static ListNode merge(ListNode a, ListNode b){
        //creating 'dummy' list node which holds a dummy value (0)
        //it is for having a concrete node structure in the heap memory
        ListNode dummy = new ListNode(0);
        //'tail' node points to dummy and will add the sorted values to the dummy node
        ListNode tail = dummy;
        //runs till any one on the list is travered till end
        while(a != null && b != null){
            //adds the node to dummy 
            if(a.data <= b.data){
                tail.next = a;
                a = a.next;
            } else{
                tail.next = b;
                b = b.next;
            }
            //after adding the node, tail now references the newly added node
            tail = tail.next;
        }
        //if only one of the list is null, add the remaining elements of the other list.
        if(a == null){
            tail.next = b;
        } else{
            tail.next = a;
        }
        //as dummy is mearly a placeholder to add upon, we need to return the nodes after it only
        return dummy.next;
    } 
/* 
    //without using 'dummy' node
    public static ListNode merge(ListNode a, ListNode b){
        //finding the concrete node and having temp point to it
        //if we use dummy then we don't have to write this at all and can go straight to while loop
        ListNode temp = null;
        if(a.data <=  b.data){
            temp = a;
            a = a.next;
        } else{
            temp = b;
            b = b.next;
        }
        //'tail' node points to temp
        ListNode tail = temp;
        //runs till any one on the list is travered till end
        while(a != null && b != null){
            //adds the node to dummy 
            if(a.data <= b.data){
                tail.next = a;
                a = a.next;
            } else{
                tail.next = b;
                b = b.next;
            }
            //after adding the node, tail now references the newly added node
            tail = tail.next;
        }
        //if only one of the list is null, add the remaining elements of the other list.
        if(a == null){
            tail.next = b;
        } else{
            tail.next = a;
        }
        //temp itself contains a value, so return that too
        return temp;
    } */

    //main method
    public static void main(String[] args) {
        //creating two SORTED lists
        MergeSortedSLL a = new MergeSortedSLL();
        a.head = new ListNode(1);
        a.insertLast(4);
        a.insertLast(8);

        MergeSortedSLL b = new MergeSortedSLL();
        b.head = new ListNode(3);
        b.insertLast(5);
        b.insertLast(8); 
        b.insertLast(9);
        b.insertLast(14);
        b.insertLast(18);
        
        a.display();
        b.display();

        //merging the lists
        //creating new list to store the results
        MergeSortedSLL result = new MergeSortedSLL();
        result.head = merge(a.head, b.head);
        result.display();
    }    
}
