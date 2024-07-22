
//also known as max heap (priority queue implemented using heap)
public class MaxPriorityQueue
{/*if you need to store an array of integer values in a collection, you must use Integer[] because collections can only store objects. 
    Additionally, if you need to pass an array of integer values to a method that expects an Object[] parameter, you must use Integer[]. */
    Integer[] heap;
    //size of the queue
    int n;

    public MaxPriorityQueue(int capacity){
        //capacity+1 since we keep the 0 index empty
        heap = new Integer[capacity + 1];
        n = 0;
    }

    //method to check if the priority queue is empty or not
    public boolean isEmpty(){
        return n == 0;
    }

    //returns size of the priority queue
    public int size(){
        return n;
    }

    //method to insert value
    public void insert(int x){
        if(n == heap.length - 1){
            resize(2 * heap.length);
        }
        n++;
        heap[n] = x;
        swim(n);
    }

    //bottom up re-heapify
    //checks if the child is greater or lesser than the parent. If greater, it shifts it up.
    private void swim(int k){
        while(k > 1 && heap[k/2] < heap[k]){
            int temp = heap[k];
            heap[k] = heap[k/2];
            heap[k/2] = temp;
            k = k/2;//because we may have to keep shifting up till new value is inserted at the correct position
        }
    }

    //re sizes the queue
    public void resize(int capacity){
        Integer[] temp = new Integer[capacity];
        for(int i = 0; i < heap.length; i++){
            temp[i] = heap[i];
        }
        heap = temp;
    }

    //method to print the max heap
    public void printMaxHeap(){
        for(int i = 1; i <= n; i++){
            System.out.print(heap[i] + " ");
        }
    }

    //deleting the max element
    public int deleteMax(){
        //as the max value is at the start of the heap
        int max = heap[1];
        swap(1, n);
        n--;
        sink(1);
        //make the last value as null (to delete the max value (previous one))
        heap[n+1] = null;
        //we resize if there are a lot of nulls at the end to save space (i.e we reduce the size of the heap)
        if(n > 0 && n == ((heap.length - 1) / 4)){
            resize(heap.length/2);
        }
        return max;
    }

    //method to swap elements
    public void swap(int a, int b){
        int temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }

    //top bottom reheapify (sink)
    private void sink(int k){
        while(2 * k <= n){
            int j = 2 * k;
            //iterating till either j > n or if the next element to current element is greater than the current element
            if(j < n && heap[j] < heap[j+1]){
                j++;
            }
            //if kth element is greater or equal to jth element it means we have the max value so we can exit
            if(heap[k] >= heap[j]){
                break;
            }
            //if we didnt break the loop, then we swap the kth and jth values as it implies jth value is greater than kth value
            //then continue the operation with k = j.
            swap(k, j);
            k = j;
        }
    }

    public static void main(String[] args) {
        //creating a priority queue of capacity 5
        MaxPriorityQueue maxpq = new MaxPriorityQueue(5);

        //inserting values into the queue
        maxpq.insert(4);
        maxpq.insert(5);
        maxpq.insert(2);
        maxpq.insert(6);
        maxpq.insert(1);
        maxpq.insert(3);

        //size of the priority queue
        System.out.println(maxpq.size());

        //to check is queue is empty or not
        System.out.println(maxpq.isEmpty());
    
        //printing the queue
        //notice how the largest element is in the front (others may be in different order)
        maxpq.printMaxHeap();

        //deleting the max element
        System.out.println();
        System.out.println(maxpq.deleteMax() + " is deleted");
        maxpq.printMaxHeap();
    }
}
