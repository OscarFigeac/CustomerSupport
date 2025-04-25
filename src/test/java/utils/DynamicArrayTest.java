package utils;

import business.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicArrayTest {
    //add tests
    @Test
    void add_emptyArray(){
        DynamicArray array = new DynamicArray();
        User user = new User("testing", "password");
        UserHashMap.Entry expected = new UserHashMap.Entry(user);

        array.add(expected);

        assertEquals(expected, array.get(0), "Failed at adding to an empty array");
    }

    @Test
    void add_multipleElements(){
        DynamicArray array = new DynamicArray();
        User user = new User("testing", "password");
        UserHashMap.Entry expected = new UserHashMap.Entry(user);

        array.add(new UserHashMap.Entry(new User("helllo", "world")));
        array.add(expected);
        array.add(new UserHashMap.Entry(new User("ThisIs", "ATest")));

        assertEquals(expected, array.get(1), "Failed at adding to an array with multiple elements");
    }

    @Test
    void add_AddingToTheEnd(){
        DynamicArray array = new DynamicArray(3);
        User user = new User("testing", "password");
        UserHashMap.Entry expected = new UserHashMap.Entry(user);

        array.add(new UserHashMap.Entry(new User("helllo", "world")));
        array.add(new UserHashMap.Entry(new User("ThisIs", "ATest")));
        array.add(expected);

        assertEquals(expected, array.get(2), "Failed at adding to the end of an array");
    }

    @Test
    void add_addingPastArraySize(){
        DynamicArray array = new DynamicArray(2);
        User user = new User("testing", "password");
        UserHashMap.Entry expected = new UserHashMap.Entry(user);

        array.add(new UserHashMap.Entry(new User("helllo", "world")));
        array.add(new UserHashMap.Entry(new User("ThisIs", "ATest")));
        array.add(expected);

        assertEquals(expected, array.get(2), "Failed at adding past the array size");
    }

    //remove tests
    @Test
    void remove_RemovingFromEmptyList(){
        DynamicArray array = new DynamicArray();

        assertThrows(IndexOutOfBoundsException.class, () -> array.remove(0), "IndexoutOfBounds error expected when removing from an empty list");
    }

    @Test
    void remove_RemovingFromArrayWithOneElement(){
        DynamicArray array = new DynamicArray();
        array.add(new UserHashMap.Entry(new User("helllo", "world")));
        DynamicArray expected = new DynamicArray();

        array.remove(0);

        assertEquals(expected.get(0), array.get(0), "Failed at removing the only element in an array");
    }

    @Test
    void remove_RemovingFromArrayWithMultipleElements(){
        DynamicArray array = new DynamicArray();
        array.add(new UserHashMap.Entry(new User("helllo", "world")));
        array.add(new UserHashMap.Entry(new User("ThisIs", "ATest")));
        array.add(new UserHashMap.Entry(new User("testing", "password")));

        DynamicArray expected = new DynamicArray();
        expected.add(new UserHashMap.Entry(new User("helllo", "world")));
        expected.add(new UserHashMap.Entry(new User("testing", "password")));
        expected.add(null);

        int pos = 1;
        array.remove(pos);

        for (int i = 0; i < 2; i++) {
            assertEquals(expected.get(i).getKey(), array.get(i).getKey(), "Failed at removing from an array with multiple elements");
        }
    }
}