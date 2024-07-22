public class SinglyLinkedList
{
    //instance variable of type ListNode of name head
    private ListNode head;

    //constructor for this class
    public SinglyLinkedList(){
        this.head = null;
    }

    private static class ListNode{

        private int data;
        private ListNode next;

        //node for the list
        public ListNode(int data){
            this.data = data;
            this.next = null;
        }
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
    
    //method to count the number of nodes in the sll
    public int length(){
        //if head = null, then sll doesn't exist
        if(head == null){
            return 0;
        }
        int count = 0;
        ListNode current = head;
        //if current = null then end of the sll
        while(current != null){
            count++;
            current = current.next;
        }
        return count;
    }

    //method to insert element in the beginning of the list
    public void insertFirst(int value){
        //a new node is created with the data given in 'value'
        ListNode newnode = new ListNode(value);
        //newnode is linked with head
        newnode.next = head;
        //newnode is now head
        head = newnode;
    }

    //inserting node at the end of the list
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

    //inserting a node at any position
    public void insert(int position, int value){
        ListNode newnode = new ListNode(value);
        //inserting at first position
        if(position == 1){
            newnode.next = head;
            head = newnode;
        } else{
            //for inserting in other positions
            ListNode previous = head;
            int count = 1;
            //traverses to the node before the required position
            while(count < position){
                previous = previous.next;
                count++;
            }
            ListNode current = previous.next;
            previous.next = newnode;
            newnode.next = current;
        }
    }

    //deleting the first node of the list
    public ListNode deleteFirst(){
        //if list is empty
        if(head == null){
            return null;
        }
        ListNode temp = head;
        head = head.next;
        //the temp node is cut of all connections, so it will be garbage collected after this method is exited
        temp.next = null;
        return temp;
    }

    //deleting the last node of the list
    public ListNode deleteLast(){
        if(head == null || head.next == null){
            return head;
        }
        ListNode current = head;
        ListNode previous = null;
        //traversing to he last node
        while(current.next != null){
            previous = current;
            current = current.next;
        }
        previous.next = null;
        return current;
    }

    //deleting any node from the list
    public void delete(int position){
        if(position == 1){
            head = head.next;
        } else{
            ListNode previous = head;
            int count = 1;
            while(count < position - 1){
                previous = previous.next;
                count++;
            }
            ListNode current = previous.next;
            previous.next = current.next;
        }
    }

    //searching for an element in the list
    public boolean find(int searchKey){
        if(head == null){
            return false;
        }
        ListNode current = head;
        while(current != null){
            if(current.data == searchKey){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    //reversing the list
    public void reverse(){
        if(head == null){
            return;
        }
        ListNode current = head;
        ListNode previous = null;
        ListNode next = null;
        while(current != null){
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        head = previous;
    }

    //finding the nth node from the end of the list
    public ListNode getNthNodeFromEnd(int n){
        if(head == null){
            return null;
        }
        if(n <= 0){
            throw new IllegalArgumentException("INvalid value: n = " + n);
        }
        ListNode mainPtr = head;
        ListNode refPtr = head;
        int count = 0;
        while(count < n){
            if(refPtr == null){
                throw new IllegalArgumentException(n + " is greater than the number of nodes in the list");
            }
            refPtr = refPtr.next;
            count++;
        }
        while(refPtr != null){
            refPtr = refPtr.next;
            mainPtr = mainPtr.next;
        }
        //as mainPtr is n nodes before the refPtr, it means that when refPtr refers to the last node, mainPtr is nth position from the last node
        return mainPtr;
    }

    //removing duplicates from a sorted singly linked list
    public void removeDuplicates(){
        if(head == null){
            return;
        }
        ListNode current = head;
        while(current != null && current.next != null){
            if(current.data == current.next.data){
                current.next = current.next.next;
            } else{
                current = current.next;
            }
        }
    }

    //inserting a node in the sorted list
    public void insertInSortedList(int value){
        ListNode newNode = new ListNode(value);
        if(head == null){
            head = newNode;
            return;
        }
        ListNode current = head;
        ListNode temp = null;
        while(current != null && current.data < newNode.data){
            temp = current;
            current = current.next;
        }
        newNode.next = current;
        temp.next = newNode;
    }

    //remove a given key from the list
    public void deleteNode(int key){
        ListNode current = head;
        ListNode temp = null;
        if(current != null && current.data == key){
            head = current.next;
            return;
        }
        while(current != null && current.data != key){
            temp = current;
            current = current.next;
        }
        if(current == null){
            return;
        }
        temp.next = current.next;
    }

    //method that detects a loop in a singly linked list
    public boolean containsLoop(){
        //create the fast and slow pointers
        ListNode fastPtr = head;
        ListNode slowPtr = head;
        while(fastPtr != null && fastPtr.next != null){
            fastPtr = fastPtr.next.next;
            slowPtr = slowPtr.next;
            if(slowPtr == fastPtr){
                return true;
            }
        }
        return false;
    }

    //floyd's cycle detection methods(both the ones below this)
    //returns the slowPtr if loop exists
    public ListNode StartNodeInALoop(){
        //create the fast and slow pointers
        ListNode fastPtr = head;
        ListNode slowPtr = head;
        while(fastPtr != null && fastPtr.next != null){
            fastPtr = fastPtr.next.next;
            slowPtr = slowPtr.next;
            if(slowPtr == fastPtr){
                return getStartingNode(slowPtr);
            }
        }
        return null;
    }

    //to get the starting position
    //make it private to prevent calling it accidentally
    private ListNode getStartingNode(ListNode slowPtr){
        ListNode temp = head;
        while(temp != slowPtr){
            temp = temp.next;
            slowPtr = slowPtr.next;
        }
        //the while loop is excited when temp = slowPtr which is the starting node
        return temp;
    }

    //removing loop from the singly liked list
    public void removeLoop(){
        //create the fast and slow pointers
        ListNode fastPtr = head;
        ListNode slowPtr = head;
        while(fastPtr != null && fastPtr.next != null){
            fastPtr = fastPtr.next.next;
            slowPtr = slowPtr.next;
            if(slowPtr == fastPtr){
                removeLoop(slowPtr);
                return;
            }
        }
    }

    //make it private to prevent accidentally calling it
    private void removeLoop(ListNode slowPtr){
        ListNode temp = head;
        while(temp.next != slowPtr.next){
            temp = temp.next;
            slowPtr = slowPtr.next;
        }
        slowPtr.next = null;
    }

    public static void main(String args[]){
        //initialize the sll(as soon as we do this, head is null)
        SinglyLinkedList sll = new SinglyLinkedList();

        //comment this part out for reversing the list part
        sll.head = new ListNode(10);
        
        //use the below line for reversing singly linked list
        //creating three more nodes
        ListNode second = new ListNode(1);
        ListNode third = new ListNode(8);
        ListNode fourth = new ListNode(11);

        //connecting all the nodes.
        sll.head.next = second;//10 -> 1
        second.next = third;//10 -> 1 -> 8
        third.next = fourth;//10 -> 1 -> 8 -> 11 -> null

        //inserting element in the beginning of the list
        sll.insertFirst(20);
        sll.insertFirst(22);

        //inserting elements at the end of the sll
        sll.insertLast(7);
        sll.insertLast(69);

        //inserting at any position
        sll.insert(1, 3);
        sll.insert(5, 77);

        //deleting first node
        //we put .data as the node has both connection and data affiliated to it
        //so we need to specify what to print as the node returned by the method has both
        System.out.println(sll.deleteFirst().data + " is deleted");

        //deleting the last node of the list
        System.out.println(sll.deleteLast().data + " is deleted");

        //deleting a node from any position
        sll.delete(5);

        //deleting a node using a key
        sll.deleteNode(1);
        System.out.println("node deleted");

        //calling method to display the list
        sll.display();

        //calling method to print the length of the sll
        System.out.println(sll.length());

        //to search for an element in the list
        if(sll.find(20)){
            System.out.println("present");
        } else{
            System.out.println("not present");
        }

        //reversing a singly linked list
        sll.reverse();
        sll.display();

        //finding the nth node (here 2nd node from the end) from the end of the list
        System.out.println(sll.getNthNodeFromEnd(2).data);

        //creating a sorted linked list
        SinglyLinkedList sortedsll = new SinglyLinkedList();
        sortedsll.head = new ListNode(1);
        ListNode second1 = new ListNode(1);
        ListNode third1 = new ListNode(2);
        ListNode fourth1 = new ListNode(3);
        ListNode fifth1 = new ListNode(3);
        ListNode sixth1 = new ListNode(4);
        sortedsll.head.next = second1;
        second1.next = third1;
        third1.next = fourth1;
        fourth1.next = fifth1;
        fifth1.next = sixth1;
        sixth1.next = null;
        
        sortedsll.display();

        sortedsll.removeDuplicates();
        sortedsll.display();

        //Inserting a node in the sorted list
        sortedsll.insertInSortedList(3);
        sortedsll.display();

        //create a singly linked list that has a loop
        SinglyLinkedList loopedSLL = new SinglyLinkedList();
        loopedSLL.head = new ListNode(100);
        ListNode first3 = new ListNode(101);
        ListNode second3 = new ListNode(102);
        ListNode third3 = new ListNode(103);
        ListNode fourth3 = new ListNode(104);
        ListNode fifth3 = new ListNode(105);

        loopedSLL.head.next = first3;
        first3.next = second3;
        second3.next = third3;
        third3.next = fourth3;
        fourth3.next = fifth3;
        //now the loop is created (5th is connected to 3rd)
        //3rd node is the starting node (it has data = 103)
        fifth3.next = third3;

        //invoking the method to check if it has a loop
        System.out.println();
        System.out.println(loopedSLL.containsLoop());

        //floyd's cycle detection algorithm demonstration
        System.out.println(loopedSLL.StartNodeInALoop().data);

        //removing loop from the sll
        loopedSLL.removeLoop();
        //if loop exists, display() runs forever
        loopedSLL.display();
    }
}