package utils;

import java.util.ArrayList;

public class HashMap {
    private static final int INITIAL_SIZE = 103;
    private ArrayList<Entry>[] map;
    private int count;

    public HashMap(){
        map = new ArrayList[INITIAL_SIZE];
        count = 0;
    }

    //Key is the username
    //Value is the
    private static class Entry{
        private String key;
        private Integer value;

        public Entry(String key, Integer value){
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Enters a value into the hashmap
     * @param key The username of the user/agent
     * @param value
     * @return The key if it was present or null if nothing was found matching the key
     * @throws IllegalArgumentException if the key is null
     */
    public Integer put(String key, Integer value){
        validateKey(key);

        int destinationSlot = calculateSlot(key);

        if(map[destinationSlot] == null){
            map[destinationSlot] = new ArrayList<Entry>();
        }

        ArrayList<Entry> slotList = map[destinationSlot];
        for (int i = 0; i < slotList.size(); i++) {
            Entry currentEntry = slotList.get(i);
            if(currentEntry.key.equals(key)){
                Integer oldValue = currentEntry.value;
                currentEntry.value = value;
                return oldValue;
            }
        }

        Entry newEntry = new Entry(key, value);
        slotList.add(newEntry);
        count++;

        return null;
    }

    /**
     * Removes an entry from the map
     * @param key The username of the user/agent to be deleted
     * @return null if nothing was removed or the username of the user/agent removed
     * @throws IllegalArgumentException if the key is null
     */
    public Integer remove(String key){
        validateKey(key);

        int destinationSlot = calculateSlot(key);

        ArrayList<Entry> slotList = map[destinationSlot];
        if(slotList == null){
            return null;
        }

        for (int i = 0; i < slotList.size(); i++) {
            Entry currentEntry = slotList.get(i);
            if(currentEntry.key.equals(key)){
                Integer oldValue = currentEntry.value;
                slotList.remove(i);
                count--;
                return oldValue;
            }
        }
        return null;
    }

    /**
     * finds the entry in the map
     * @param key The username to be found
     * @return null if the entry was not found and the value of the entry if it was
     * @throws IllegalArgumentException if the key is null
     */
    public Integer get(String key){
        validateKey(key);

        int destinationSlot = calculateSlot(key);

        if(map[destinationSlot] == null || map[destinationSlot].isEmpty()){
            return null;
        }

        ArrayList<Entry> slotList = map[destinationSlot];
        for (int i = 0; i < slotList.size(); i++) {
            Entry currentEntry = slotList.get(i);
            if(currentEntry.key.equals(key)){
                return currentEntry.value;
            }
        }
        return null;
    }

    private int calculateSlot(String key) {
        // Convert key's data into number
        int hashCode = key.hashCode();
        // Get positive version of hashCode (in case it was a negative value)
        hashCode = Math.abs(hashCode);
        // Convert hashCode into a value within the range of slots for this map
        int destinationSlot = hashCode % map.length;
        return destinationSlot;
    }

    private static void validateKey(String key) {
        if(key == null){
            throw new IllegalArgumentException("Key cannot be null");
        }
    }
}
