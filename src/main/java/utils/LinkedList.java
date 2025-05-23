package utils;

import business.Ticket;

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
    public void add(Ticket user){
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

    /**
     * Indicates the user Object stored at a position passed as a parameter.
     * @param pos the position being looked for
     * @return the data found at the provided position
     * @throws IndexOutOfBoundsException if the passed parameter is less than 0 or
     * greater than the number of elements
     * @author Oscar Figeac
     */
    public Ticket get(int pos) {
        if (isEmpty() || pos < 0 || pos >= numOfElements) {
            throw new IndexOutOfBoundsException("Index must be between 0 and " + (numOfElements-1) + ". " +
                    "(Supplied index was " + pos + ").");
        }
        Node current = first;
        for (int i = 0; i < pos; i++) {
            current = current.next;
        }
        return current.data;
    }

    public Ticket remove(int pos){
        if(isEmpty() || pos < 0 || pos >= numOfElements){
            throw new IndexOutOfBoundsException("Index must be between 0 and " + (numOfElements-1) + " inclusive. " +
                    "(Supplied" +
                    " index was" +
                    " " + pos+")");
        }

        Ticket removed = null;
        if (pos == 0){
            removed = first.data;
            first = first.next;
            if (first == null){
                last = null;
            }
        } else{
            Node current = first;
            Node prev = null;

            for (int i = 0; i < pos; i++) {
                prev = current;
                current = current.next;
            }
            removed = current.data;
            prev.next = current.next;
            current.next = null;
            if (prev.next == null){
                last = prev;
            }
        }
        numOfElements--;

        return removed;
    }

    /**
     * Takes in a user and retrieves its position
     * @param user The user being looked for
     * @return The position in which the object was found, -1 otherwise
     * @throws IllegalArgumentException if the provided data is null
     * @author Oscar Figeac
     */
    public int indexOf(Ticket user){
        if (user == null){
            throw new IllegalArgumentException("Passed parameter cannot be null");
        }
        Node newNode = new Node(user);
        Node current = first;
        for (int i = 0; i <= numOfElements; i++) {
            if (current.equals(newNode)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    public static class Node {
        private Ticket data;
        private Node next;

        public Node(Ticket data) {
            this.data = data;
            this.next = null;
        }
    }
}
