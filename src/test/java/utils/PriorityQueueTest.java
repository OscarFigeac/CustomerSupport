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
    void compare() {
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