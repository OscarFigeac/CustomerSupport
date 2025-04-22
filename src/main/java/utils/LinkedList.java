package utils;

import business.User;

public class LinkedList {
    private int numOfElements;
    private Node first;
    private Node last;

    public LinkedList(){
        this.first = null;
        this.last = null;
        this.numOfElements = 0;
    }

    /**
     * Indicates the size of the List
     * @return the number of elements stored in the List
     * @author Oscar Figeac
     */
    public int size(){
        return numOfElements;
    }

    /**
     * Indicates whether the List is empty or not
     * @return true if the List is empty, false otherwise
     * @author Oscar Figeac
     */
    public boolean isEmpty(){
        return numOfElements == 0;
    }

    /**
     * Takes in a User object to be added to the end of the List
     * @param user the object to be added
     * @throws IllegalArgumentException if the parameter is null
     * @author Oscar Figeac
     */
    public void add(User user){
        if (user == null){
            throw new IllegalArgumentException("Passed parameter cannot be null");
        }
        Node newNode = new Node(user);
        if (first == null){
            first = newNode;
            last = newNode;
        } else{
            last.next = newNode;
            last = newNode;
        }
        numOfElements++;
    }

    public static class Node {
        private User data;
        private Node next;

        public Node(User data) {
            this.data = data;
            this.next = null;
        }
    }
}
