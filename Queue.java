import java.util.NoSuchElementException;

public class Queue 
{

    private ListNode front;
    private ListNode rear;
    private int length;

    public Queue(){
        this.front = null;
        this.rear = null;
        this.length = 0;
    }

    private class ListNode
    {
        private int data;
        private ListNode next;

        public ListNode(int data){
            this.data = data;
            this.next = null;
        }
    }

    //method to return length of the queue
    public int length(){
        return length;
    }

    //method to check if queue is empty
    public boolean isEmpty(){
        return length == 0;
    }

    //method to enter an element
    public void enQueue(int data){
        ListNode temp = new ListNode(data);
        //if queue is empty, then newly created node is front,
        //then we put rear = temp at end. So both rear and temp point to that node
        if(isEmpty()){
            front = temp;
        } else{
            //if not, rear is conntected to the new node, and then we do rear = temp making rear point to temp
            rear.next = temp;
        }
        rear = temp;
        length++;
    }

    //method to delete an element from the queue
    public int deQueue(){
        if(isEmpty()){
            throw new NoSuchElementException("Queue is already empty");
        }
        //as deletion is at the front, just make front as the next node front points to
        //that is front = front.next;
        //now the previous front node is dereferenced and will be garbage collected
        int result = front.data;
        front = front.next;
        if(front == null){
            rear = null;
        }
        length--;
        return result;
    }

    //method to return first entered element in queue
    public int first(){
        if(isEmpty()){
            throw new NoSuchElementException("Queue is empty");
        }
        return front.data;
    }

    //method to return the last entered element in the queue
    public int last(){
        if(isEmpty()){
            throw new NoSuchElementException("Queue is empty");
        }
        return rear.data;
    }

    //method to print the elements in the queue
    public void print(){
        if(isEmpty()){
            return;
        }
        ListNode current = front;
        while(current != null){
            System.out.print(current.data + " --> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        //creating a queue
        Queue queue = new Queue();
        
        //inserting elements
        queue.enQueue(10);
        queue.enQueue(20);
        queue.enQueue(30);
        queue.enQueue(40);
        queue.enQueue(50);

        //delete an element
        System.out.println(queue.deQueue() + " is deleted");
    
        //print the elements in the queue
        queue.print();
    }
}
