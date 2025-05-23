package utils;

import org.junit.jupiter.api.Test;
import business.Ticket;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PriorityQueueTest {

    @Test
    void testSize_Empty() {
        PriorityQueue test = new PriorityQueue();
        assertEquals(0, test.size(), "Size should be 0");
    }

    @Test
    void testSize_Populated() {
        PriorityQueue test = new PriorityQueue();
        Ticket ticket = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        test.enqueue(ticket);
        assertEquals(1, test.size(), "Size should be 0");
    }

    @Test
    void isEmpty_Empty() {
        PriorityQueue test = new PriorityQueue();
        assertTrue(test.isEmpty(), "Queue should be empty");
    }

    @Test
    void isEmpty_Populated() {
        PriorityQueue test = new PriorityQueue();
        Ticket ticket = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        test.enqueue(ticket);
        assertTrue(test.isEmpty(), "Queue should not be empty");
    }

    @Test
    void compare_LowPriorityFirst() {
        PriorityQueue test = new PriorityQueue();
        Ticket ticket = new Ticket("123", "Test", 5, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        Ticket ticket1 = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        assertTrue(test.compare(ticket, ticket1) > 0);
    }

    @Test
    void compare_HighPriorityFirst() {
        PriorityQueue test = new PriorityQueue();
        Ticket ticket = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        Ticket ticket1 = new Ticket("123", "Test", 3, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        assertTrue(test.compare(ticket, ticket1) < 0);
    }

    @Test
    void compare_SamePriority() {
        PriorityQueue test = new PriorityQueue();
        Ticket ticket = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        Ticket ticket1 = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        assertEquals(0, test.compare(ticket, ticket1));
    }

    @Test
    void compare_ExceptionHandling() {
        PriorityQueue test = new PriorityQueue();
        Ticket ticket = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        assertThrows(IllegalArgumentException.class, () -> test.compare(ticket, null), "Should throw an exception");
    }

    @Test
    void enqueue_Null() {
        PriorityQueue test = new PriorityQueue();
        assertThrows(IllegalArgumentException.class, () -> test.enqueue(null), "Should throw an exception");
    }

    @Test
    void enqueue_EmptyQueue(){
        PriorityQueue test = new PriorityQueue();
        Ticket ticket = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        test.enqueue(ticket);
        assertEquals(1, test.size());
    }

    @Test
    void enqueue_HigherPriorityFirst(){
        PriorityQueue test = new PriorityQueue();
        Ticket ticket = new Ticket("123", "Test", 5, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        Ticket ticket2 = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        test.enqueue(ticket);
        test.enqueue(ticket2);
        assertEquals(2, test.size());
        System.out.println(test.peek().getPriorityLvl());
    }

    @Test
    void enqueue_LowerPriorityFirst(){
        PriorityQueue test = new PriorityQueue();
        Ticket ticket = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        Ticket ticket2 = new Ticket("123", "Test", 5, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        test.enqueue(ticket);
        test.enqueue(ticket2);
        assertEquals(2, test.size());
        System.out.println(test.peek().getPriorityLvl());
    }

    @Test
    void enqueue_MultipleElements(){
        PriorityQueue test = new PriorityQueue();
        Ticket ticket = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        Ticket ticket2 = new Ticket("123", "Test", 3, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        Ticket ticket3 = new Ticket("123", "Test", 2, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        test.enqueue(ticket);
        test.enqueue(ticket2);
        test.enqueue(ticket3);
        assertEquals(3, test.size());
        System.out.println(test.peek().getPriorityLvl());
    }

    @Test
    void enqueue_SamePriority(){
        PriorityQueue test = new PriorityQueue();
        Ticket ticket = new Ticket("123", "Test", 3, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        Ticket ticket2 = new Ticket("123", "Test", 3, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        Ticket ticket3 = new Ticket("123", "Test", 3, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        test.enqueue(ticket);
        test.enqueue(ticket2);
        test.enqueue(ticket3);
        assertEquals(3, test.size());
        System.out.println(test.peek().getPriorityLvl());
    }

    @Test
    void peek_Empty(){
        PriorityQueue test = new PriorityQueue();
        assertThrows(IllegalArgumentException.class, () -> test.peek(), "Should throw an exception");
    }

    @Test
    void peek_Populated() {
        PriorityQueue test = new PriorityQueue();
        Ticket ticket = new Ticket("123", "Test", 3, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        Ticket ticket2 = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        Ticket ticket3 = new Ticket("123", "Test", 3, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        test.enqueue(ticket);
        test.enqueue(ticket2);
        test.enqueue(ticket3);
        assertEquals(ticket2, test.peek(), "Highest rated should be first");
    }

    @Test
    void dequeue_Empty() {
        PriorityQueue test = new PriorityQueue();
        assertEquals(null, test.dequeue());
    }

    @Test
    void dequeue_OneElement(){
        PriorityQueue test = new PriorityQueue();
        Ticket ticket = new Ticket("123", "Test", 3, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        test.enqueue(ticket);
        Ticket dequeued = test.dequeue();
        assertEquals(ticket, dequeued, "Deleted object should be the same");
    }

    @Test
    void dequeue_MultipleElements(){
        PriorityQueue test = new PriorityQueue();
        Ticket ticket1 = new Ticket("123", "Test", 3, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        Ticket ticket2 = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        Ticket ticket3 = new Ticket("123", "Test", 2, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        test.enqueue(ticket1);
        test.enqueue(ticket2);
        test.enqueue(ticket3);

        Ticket dequeued = test.dequeue();
        assertEquals(ticket2, dequeued, "Deleted object should be the same and the highest priority");
    }

}