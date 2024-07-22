//stack using singly linked list

import java.util.EmptyStackException;

public class Stack
{
    //instance variables
    private ListNode top;
    private int length;

    //node structure of the list
    private class ListNode
    {
        private int data;
        private ListNode next;

        public ListNode(int data){
            this.data = data;
        }
    }

    //constructor of the Stack class
    public Stack(){
        top = null;
        length = 0;
    }

    //returns length of the stack
    public int length(){
        return length;
    }

    //method to check if stack is empty
    //true is stack is empty
    public boolean isEmpty(){
        return length == 0;
    }

    //inserts an element to the list
    //it is an insert node at first position operation
    public void push(int value){
        ListNode temp = new ListNode(value);
        temp.next = top;
        top = temp;
        length++;
    }

    //deletes an element
    //deletes the most recent inserted element
    //basically a delete first node operation
    public int pop(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        int result = top.data;
        top = top.next;
        length++;
        return result;
    }

    //returns the top most element of the stack
    public int peek(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return top.data;
    }

    public static void main(String[] args) {
        Stack s = new Stack();
        s.push(10);
        s.push(20);
        s.push(30);

        System.out.println(s.peek() + " is the peek element");

        System.out.println(s.pop() + " is popped");

        System.out.println(s.peek() + " is the peek element");
    }
}