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
    void enqueue() {
    }

    @Test
    void peek() {
    }

    @Test
    void dequeue() {
    }
}