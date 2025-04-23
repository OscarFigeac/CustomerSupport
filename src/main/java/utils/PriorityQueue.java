package utils;

import business.Ticket;

public class PriorityQueue {
    private int numOfElements;
    private Node first;
    private Node last;

    public PriorityQueue(){
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
     * Takes in two parameters to be compared
     * @param t1 The parameter being checked
     * @param t2 The parameter being checked
     * @return a negative integer if t1< t2; a positive integer if t1>t2 and zero if equal
     * @throws IllegalArgumentException if any of the parameters is null
     * @author Oscar Figeac
     */
    public int compare(Ticket t1, Ticket t2){
        if (t1 == null || t2 == null){
            throw new IllegalArgumentException("Passed parameter cannot be null");
        }
        return Integer.compare(t1.getPriorityLvl(), t2.getPriorityLvl());
    }

    /**
     * Adds another object to the queue making sure it keeps the sorted order in the queue
     * @param value the value to be added
     * @throws IllegalArgumentException if the parameter is null
     * @author Oscar Figeac
     */
    public void enqueue(Ticket value){
        if (value == null){
            throw new IllegalArgumentException("Passed parameter cannot be null");
        }

        Node newNode = new Node(value);

        if (isEmpty()){
            first = newNode;
            last = newNode;
        } else if (compare(value, first.data) < 0){
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
        } else if (compare(value, last.data) > 0){
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        } else {
            Node current = first;
            while (current != null){
                if (compare(value, current.data) < 0){
                    newNode.next = current;
                    newNode.prev = current.prev;
                    if (current.prev != null) {
                        current.prev.next = newNode;
                    }
                    current.prev = newNode;
                    if (current == first){
                        first = newNode;
                    }
                    numOfElements++;
                    return;
                }
                current = current.next;
            }
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
        numOfElements++;
    }

    /**
     * Retrieves the first element in the queue
     * @return the found data
     * @throws IllegalArgumentException if the queue is empty
     * @author Oscar Figeac
     */
    public Ticket peek(){
        if (isEmpty()){
            throw new IllegalArgumentException("Queue is empty");
        }
        return first.data;
    }

    /**
     * Removes the first element in the queue and keeps the sorted order
     * @return the removed element
     * @author Oscar Figeac
     */
    public Ticket dequeue(){
        if (isEmpty()){
            return null;
        }
        Ticket lost = first.data;
        first = first.next;
        if (first != null){
            first.prev = null;
        } else{
            last = null;
        }
        numOfElements--;
        return lost;
    }

    public static class Node {
        private Ticket data;
        private Node next;
        private Node prev;

        public Node(Ticket data) {
            this.data = data;
            this.next = null;
        }
    }
}
