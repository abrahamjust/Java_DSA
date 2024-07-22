public class HashTable
{
    //instance variables
    private HashNode[] buckets;
    private int numOfBuckets;
    private int size;
    
    //note the difference between size and capacity
    //capacity is the total no. of buckets (i.e size of array) 
    //size is the no. of hash nodes
    public HashTable(int capacity){
        this.numOfBuckets = capacity;
        this.buckets = new HashNode[capacity];
    }

    //private class to create structure of hash node
    private class HashNode
    {
        //reason why we use Integer instead of int is because we need to use the equals() method
        //in put() and get() methods for easiness
        private Integer key;
        private String value;
        private HashNode next;

        public HashNode(Integer key, String value){
            this.key = key;
            this.value = value;
        }
    }

    //returns the size of the hash table
    public int size(){
        return size;
    }

    //checks if the hashtable is empty or not
    public boolean isEmpty(){
        return size == 0;
    }

    //hash function (modular)
    public int getBucketIndex(Integer key){
        return key % numOfBuckets;
    }

    //method to insert key value pair into the hash table
    public void put(Integer key, String value){
        //is any argument is null throw errore
        if(key == null || value == null){
            throw new IllegalArgumentException("key or value is null");
        }
        //get index using hash function
        int bucketIndex = getBucketIndex(key);
        //head points to the value in that index
        HashNode head = buckets[bucketIndex];
        //if head is not null it means a node is there so 
        //check if the key already exists, if not keep traversing till head is null
        while(head != null){
            //checks if current key is already present
            if(head.key.equals(key)){
                head.value = value;
                return;
            }
            head = head.next;
        }
        //increment size since we are adding a new node 
        size++;
        //head again points to the value in that index
        head = buckets[bucketIndex];
        //creating the hash node
        HashNode node = new HashNode(key, value);
        //making the new node point to head
        //essentially we are adding a node in front of the old node (here head node is the old node)
        node.next = head;
        //making the index of array point to the node (remember arrays store stuff as references (i.e they are collections of objects))
        buckets[bucketIndex] = node;
    }

    //method to get a value
    public String get(Integer key){
        //if key is null throw error
        if(key == null){
            throw new IllegalArgumentException("key is null");
        }
        //get index using hash function
        int bucketIndex = getBucketIndex(key);
        //head points to node in that index
        HashNode head = buckets[bucketIndex];
        //if head is not null, execute
        while(head != null){
            //if any key is equal to given key, return data in that position
            if(head.key.equals(key)){
                return head.value;
            }
            //traverse to the next node
            head = head.next;
        }
        //if no key matches, return null
        return null;
    }

    //to remove a key value pair
    public String remove(Integer key){
        int bucketIndex = getBucketIndex(key);
        HashNode head = buckets[bucketIndex];
        HashNode previous = null;
        while(head != null){
            //if node to be deleted is reached, break
            if(head.key.equals(key)){
                break;
            }
            previous = head;
            head = head.next;
        }
        //if head is null, then no nodes so return null
        if(head == null){
            return null;
        }
        size--;
        //if previous is not equal to null, it means there are many nodes so dereference head node 
        if(previous != null){
            previous.next = head.next;
        } 
        //if previous is null, only one node so make the array point to null at the index position
        else{
            buckets[bucketIndex] = head.next;
        }
        //return the value that is now deleted
        return head.value;
    }

    public static void main(String args[]){
        //creating the hash table
        HashTable h = new HashTable(10);
        h.put(10, "Kim");
        h.put(11, "Kardashian");
        h.put(21, "Chris");

        System.out.println(h.size());

        //getting value using key from hash table
        System.out.println(h.get(10));

        //removing value from hash table using key
        System.out.println(h.remove(10) + " is deleted");
        System.out.println(h.get(10));
    }
}
