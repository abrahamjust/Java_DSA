//implementation of a stack using array
public class StackUsingArray
{
    private int top = -1;
    private int[] stack;

    //constructor
    //initializes the stack with a pre determined size
    StackUsingArray(int size){
        stack = new int[size];
    }

    //pushes element to the stack
    public void push(int data){
        if(isFull()){
            throw new RuntimeException("Stack is full !!");
        }
        top++;
        stack[top] = data;
    }

    //deletes the most recently added element
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("Stack is empty !!");
        }
        int result = stack[top];
        top--;
        return result;
    }

    //returns th top most element of the stack
    public int peek(){
        if(isEmpty()){
            throw new RuntimeException("Stack is empty !!");
        }
        return stack[top];
    }

    //returns true if stack is full, i.e top == size of the stack
    public boolean isFull(){
        return stack.length == size();
    }

    //returns true is stack is empty, i.e top < 0
    public boolean isEmpty(){
        return top < 0;
    }

    //returns the size of the stack
    //top + 1 as array is zero indexed
    public int size(){
        return top+1;
    }

    public static void main(String[] args) {
        
        //creating a stack of size 5 (can store 5 elements)
        StackUsingArray s = new StackUsingArray(5);
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        s.push(5);

        System.out.println(s.peek() + " is the topmost element");
    
        s.pop();

        System.out.println(s.peek() + " is the topmost element");
    }
}
