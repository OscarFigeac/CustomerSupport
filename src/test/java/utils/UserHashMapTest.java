package utils;

import business.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserHashMapTest {
    //put tests
    @Test
    void put_emptyMap(){
        UserHashMap map = new UserHashMap();
        map.put("testing", new User("testing", "password"));

        assertEquals(map.containsKey("testing"), true, "Failed at adding to an empty map");
        assertEquals(map.get("testing"), new User("testing", "password"), "Failed at adding to an empty map");
        assertEquals(map.size(), 1, "Wrong size after adding to an empty array");
    }

    @Test
    void put_MultipleEntries(){
        UserHashMap map = new UserHashMap();
        //I chose Aa and BB because they should be in the same slot
        String[] users = {"Aa", "BB", "testing", "test"};
        for (int i = 0; i < users.length; i++) {
            map.put(users[i], new User(users[i], "password"));
        }

        for (int i = 0; i < users.length; i++) {
            assertEquals(map.containsKey(users[i]), true, "Failed at adding to an empty map");
            assertEquals(map.get(users[i]), new User(users[i], "password"), "Failed at adding to an empty map");
        }
        assertEquals(map.size(), 4, "Wrong size after adding multiple entries to the map");
    }

    //remove tests
    @Test
    void remove_EmptyMap(){
        UserHashMap map = new UserHashMap();

        assertEquals(map.remove("testing"), null, "Failed at removing from an empty map");
        assertEquals(map.size(), 0, "Wrong size after removing from an empty map");
    }

    @Test
    void remove_KeyNotPresent(){
        UserHashMap map = new UserHashMap();
        //I chose Aa and BB because they should be in the same slot
        String[] users = {"Aa", "BB", "testing", "test"};
        for (int i = 0; i < users.length; i++) {
            map.put(users[i], new User(users[i], "password"));
        }

        assertEquals(map.remove("Tomasz"), null, "Failed at removing a user that is not present");
        assertEquals(map.size(), 4, "Wrong size after trying to remove an entry that is not present");
    }

    @Test
    void remove_OneEntryInMap(){
        UserHashMap map = new UserHashMap();
        //I chose Aa and BB because they should be in the same slot
        String[] users = {"Aa"};
        for (int i = 0; i < users.length; i++) {
            map.put(users[i], new User(users[i], "password"));
        }

        assertEquals(map.remove("Aa"), new User("Aa", "password"), "Failed at removing a user in a map with only one entry");
        assertEquals(map.size(), 0, "Wrong size after trying to remove an entry in a map with only one entry");
    }

    @Test
    void remove_MultipleEntries(){
        UserHashMap map = new UserHashMap();
        //I chose Aa and BB because they should be in the same slot
        String[] users = {"Aa", "BB", "testing", "test"};
        for (int i = 0; i < users.length; i++) {
            map.put(users[i], new User(users[i], "password"));
        }

        assertEquals(map.remove("BB"), new User("BB", "password"), "Failed at removing a user from a populated map");
        assertEquals(map.size(), 3, "Wrong size after trying to remove an entry from a populated map");
    }
}