package utils;
import business.User;

public class UserHashMap {
    private static final int INITIAL_SIZE = 103;
    private DynamicArray[] map;
    private int count;
    private int agentNum;

    public UserHashMap(){
        map = new DynamicArray[INITIAL_SIZE];
        count = 0;
        agentNum = 1;
    }

    //Key is the agent username (Created automatically)
    //Value is the Agent object
    public static class Entry{
        private String key;
        private User value;

        public Entry(User value){
            this.key = value.getUsername();
            this.value = value;
        }

        public String getKey(){
            return key;
        }

        public User getValue(){
            return value;
        }

        public User updateValue(User newValue){
            User oldValue = value;
            this.value = newValue;
            return oldValue;
        }
    }

    /**
     * Enters a value into the hashmap
     * @param key The username of the agent
     * @param value An agent object
     * @return The value if the key was present or null if nothing was found matching the key
     * @throws IllegalArgumentException if the key is null or empty
     * @author Tomasz Januszkiewicz
     */
    public User put(String key, User value){
        validateKey(key);

        int destinationSlot = calculateSlot(key);

        if(map[destinationSlot] == null){
            map[destinationSlot] = new DynamicArray();
        }

        DynamicArray slotList = map[destinationSlot];
        for (int i = 0; i < slotList.size(); i++) {
            Entry currentEntry = slotList.get(i);
            if(currentEntry.key.equals(key)){
                User oldValue = currentEntry.value;
                currentEntry.value = value;
                return oldValue;
            }
        }

        Entry newEntry = new Entry(value);
        slotList.add(newEntry);
        count++;

        return null;
    }

    /**
     * Removes an entry from the map
     * @param key The username of the agent to be deleted
     * @return null if nothing was removed or the agent if they were removed
     * @throws IllegalArgumentException if the key is null or empty
     * @author Tomasz Januszkiewcz
     */
    public User remove(String key){
        validateKey(key);

        int destinationSlot = calculateSlot(key);

        DynamicArray slotList = map[destinationSlot];
        if(slotList == null){
            return null;
        }

        for (int i = 0; i < slotList.size(); i++) {
            Entry currentEntry = slotList.get(i);
            if(currentEntry.key.equals(key)){
                User oldValue = currentEntry.value;
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
     * @throws IllegalArgumentException if the key is null or empty
     * @author Tomasz Januszkiewicz
     */
    public User get(String key){
        validateKey(key);

        int destinationSlot = calculateSlot(key);

        if(map[destinationSlot] == null || map[destinationSlot].isEmpty()){
            return null;
        }

        DynamicArray slotList = map[destinationSlot];
        for (int i = 0; i < slotList.size(); i++) {
            Entry currentEntry = slotList.get(i);
            if(currentEntry.key.equals(key)){
                return currentEntry.value;
            }
        }
        return null;
    }

    /**
     * Checks if the key is present in the map
     * @param key The username to be found
     * @return true if the key exists and false if not
     * @throws IllegalArgumentException if the key is null or empty
     * @author Tomasz Januszkiewicz
     */
    public boolean containsKey(String key){
        validateKey(key);

        return get(key) != null;
    }

    /**
     * Gives the amount of entries in the map
     * @return returns the count
     * @author Tomasz Januszkiewicz
     */
    public int size(){
        return count;
    }

    /**
     * Finds all the keys in the hashmap
     * @return Returns an array of strings containing the keys
     * @author Tomasz Januszkiewicz
     */
    public String[] getKeys(){
        if(count == 0){
            return new String[0];
        }

        String[] keys = new String[count];
        int tracker = 0;

        for (int i = 0; i < map.length; i++) {
            if(map[i] != null) {
                DynamicArray slotList = map[i];
                for(int j = 0; j < slotList.size(); j++) {
                    Entry currentEntry = slotList.get(j);
                    keys[tracker] = currentEntry.key;
                    tracker++;
                }
            }
            if(tracker == count){
                return keys;
            }
        }
        return keys;
    }

    /**
     * Finds all the values in the hashmap
     * @return Returns an array of Agents containing the values
     * @author Tomasz Januszkiewicz
     */
    public User[] getValues(){
        if(count == 0){
            return new User[0];
        }

        User[] values = new User[count];
        int tracker = 0;
        for (int i = 0; i < map.length; i++) {
            if(map[i] != null) {
                DynamicArray slotList = map[i];
                for(int j = 0; j < slotList.size(); j++) {
                    Entry currentEntry = slotList.get(j);
                    values[tracker] = currentEntry.value;
                    tracker++;
                }
            }
            if(tracker == count){
                return values;
            }
        }
        return values;
    }

    /**
     * Generates a new number for the agent's ID
     * @return The agentNum variable
     */
    public int generateAgentNum(){
        int output = agentNum;
        agentNum++;
        return output;
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
            throw new IllegalArgumentException("Username cannot be null");
        }
        if(key.isEmpty()){
            throw new IllegalArgumentException("Username cannot be empty");
        }
    }
}
