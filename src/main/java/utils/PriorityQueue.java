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

    public static class Node {
        private Ticket data;
        private LinkedList.Node next;

        public Node(Ticket data) {
            this.data = data;
            this.next = null;
        }
    }
}
