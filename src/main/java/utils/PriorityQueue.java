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



    public static class Node {
        private Ticket data;
        private LinkedList.Node next;

        public Node(Ticket data) {
            this.data = data;
            this.next = null;
        }
    }
}
