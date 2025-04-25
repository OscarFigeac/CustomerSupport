package utils;

import business.Ticket;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    @Test
    void testSize_EmptyList() {
        // SET UP:
        LinkedList testList = new LinkedList();
        int expResult = 0;

        // LOGIC:
        int result = testList.size();

        // ASSERTIONS:
        assertEquals(expResult, result, "Size of empty list was not 0");
    }

    @Test
    void testSize_OneElement() {
        // SET UP:
        LinkedList testList = new LinkedList();
        Ticket ticket = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        testList.add(ticket);
        int expResult = 1;

        // LOGIC:
        int result = testList.size();

        // ASSERTIONS:
        assertEquals(expResult, result, "Size of list with one element was not 1");
    }

    @Test
    void testSize_MultipleElements() {
        // SET UP:
        LinkedList testList = new LinkedList();
        Ticket ticket = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        Ticket ticket1 = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        testList.add(ticket1);
        testList.add(ticket);
        int expResult = 2;

        // LOGIC:
        int result = testList.size();

        // ASSERTIONS:
        assertEquals(expResult, result, "Size of list with "+ 2 + " elements was not " + 2);
    }

    @Test
    void testIsEmpty_EmptyList() {
        // SET UP:
        LinkedList testList = new LinkedList();
        boolean expResult = true;

        // LOGIC:
        boolean result = testList.isEmpty();

        // ASSERTIONS:
        assertEquals(expResult, result, "isEmpty() on empty list did not return true");
    }

    @Test
    void testIsEmpty_PopulatedList() {
        // SET UP:
        LinkedList testList = new LinkedList();
        Ticket ticket = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        testList.add(ticket);
        boolean expResult = false;

        // LOGIC:
        boolean result = testList.isEmpty();

        // ASSERTIONS:
        assertEquals(expResult, result, "isEmpty() on populated list did not return false");
    }

    @Test
    public void testAdd_EmptyList(){
        // SET UP:
        LinkedList testList = new LinkedList();
        int expSize = 1;
        Ticket expValue = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");

        // LOGIC:
        testList.add(expValue);

        // ASSERTIONS:
        assertEquals(expSize, testList.size(), "Incorrect size after add to empty list");
        assertEquals(expValue, testList.get(0), "Value added to empty list could not be retrieved from position 0");
    }

    @Test
    public void testGet_NegativePosition() {
        LinkedList myList = new LinkedList();
        Ticket ticket = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        myList.add(ticket);
        assertThrows(IndexOutOfBoundsException.class, () -> myList.get(-1), "IndexOutOfBoundsException not thrown when attempting to get from negative position in list");
    }

    @Test
    public void testGet_AboveBoundary_EqualToSize() {
        LinkedList myList = new LinkedList();
        Ticket ticket = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        Ticket ticket3 = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        Ticket ticket2 = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        myList.add(ticket3);
        myList.add(ticket);
        myList.add(ticket2);
        assertThrows(IndexOutOfBoundsException.class, () -> myList.get(3), "IndexOutOfBoundsException not thrown when attempting to get from position=length of list");
    }

    @Test
    public void testGet_AboveBoundary_GreaterThanSize() {
        // "Manual" list population
        LinkedList myList = new LinkedList();
        Ticket ticket3 = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        Ticket ticket2 = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        myList.add(ticket2);
        myList.add(ticket3);

        assertThrows(IndexOutOfBoundsException.class, () -> myList.get(4), "IndexOutOfBoundsException not thrown when attempting to get from position > length of list");
    }

    @Test
    public void testGet_ZeroIndex_PopulatedList() {
        // "Automated" list population
        LinkedList myList = new LinkedList();
        Ticket ticket1 = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        Ticket ticket2 = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        Ticket ticket3 = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");

        myList.add(ticket1);
        myList.add(ticket2);
        myList.add(ticket3);

        int pos = 0;
        Ticket result = myList.get(pos);
        assertEquals(ticket1, result, "Value not found at position "+pos + ".");
    }

    @Test
    public void testGet_LastElement_PopulatedList() {
        LinkedList myList = new LinkedList();
        Ticket ticket1 = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        Ticket ticket2 = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        Ticket ticket3 = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");

        myList.add(ticket1);
        myList.add(ticket2);
        myList.add(ticket3);

        int pos = myList.size()-1;
        Ticket result = myList.get(pos);
        assertEquals(ticket1, result, "Value not found at position "+pos + ".");
    }

    @Test
    public void testGet_ElementInPopulatedList() {
        LinkedList myList = new LinkedList();
        Ticket ticket1 = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        Ticket ticket2 = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        Ticket ticket3 = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");
        Ticket ticket4 = new Ticket("123", "Test", 1, LocalDateTime.now(), "Oscar", "o123", "Stalled");

        myList.add(ticket1);
        myList.add(ticket2);
        myList.add(ticket3);
        myList.add(ticket4);

        int pos = myList.size()/2;
        Ticket result = myList.get(pos);
        assertEquals(ticket2, result, "Value not found at position " + pos + " (position within bounds of list).");
    }

    /*
    TODO: Tests for add(value, pos):
         INVALID cases:
                - Negative position
                - Position greater than number of elements
                - Null value supplied
    TODO: Tests for add(value, pos):
           VALID cases:
                - Adding to first position (pos = 0)
                - Adding to last position (pos = size)
                - Adding in actual middle (pos between 0 and size exclusive)
     */

//    @Test
//    public void testAddAtPosition_AddingToStart(){
//        String [] sourceData = {"Hi", "Hello", "Hey there"};
//        LinkBasedList myList = new LinkBasedList();
//        for(int i = 0; i < sourceData.length; i++){
//            myList.add(sourceData[i]);
//        }
//
//        int sizeBeforeAdd = myList.size();
//
//        String toBeAdded = "New element";
//        int posToBeAdded = 0;
//        myList.add(toBeAdded, posToBeAdded);
//
//        assertEquals(sizeBeforeAdd+1, myList.size());
//
//        String addedResult = myList.get(posToBeAdded);
//        assertEquals(toBeAdded, addedResult);
//
//        int sourceDataPos = posToBeAdded;
//        for (int i = posToBeAdded+1; i < myList.size(); i++) {
//            assertEquals(sourceData[sourceDataPos], myList.get(i));
//            sourceDataPos++;
//        }
//    }
}