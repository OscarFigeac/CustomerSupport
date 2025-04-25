package utils;

import com.sun.net.httpserver.Authenticator;

public class DynamicArray {
    private final int RESIZE_FACTOR = 2;

    private int numElements;
    private UserHashMap.Entry[] data;

    //Constructors
    public DynamicArray(){
        this(10);
    }
    public DynamicArray(int orgSize){
        if(orgSize <= 0){
            throw new IllegalArgumentException("The array size is too small");
        }

        data = new UserHashMap.Entry[orgSize];
    }

    //Features:

    //Size
    /**
     * Method returns the size of the Dynamic Array for the user
     *
     * @return Dynamic Array size as an Integer
     *
     * @author Eoghan Carroll
     */
    public int size(){
        return data.length;
    }

    //Get
    /**
     * Method to return the element at a position entered by the user in the Dynamic Array
     *
     * @param position Integer entered by the user as a position
     *
     * @return Element at the position entered by the user
     *
     * @throws IndexOutOfBoundsException If the position is less than zero or larger than the array size
     *
     * @author Eoghan Carroll
     */
    public UserHashMap.Entry get(int position){
        if(position < 0 || position >= data.length){
            throw new IndexOutOfBoundsException("Position entered is outside the boundaries of the dynamic array");
        }

        return data[position];
    }

    //IndexOf
    /**
     * Method to return the index of a element entered by the user in the Dynamic Array
     *
     * @param searchingFor Element entered by the user to search for
     *
     * @return The index of the Element entered by the user or -1 if the Element is not found
     *
     * @author Eoghan Carroll
     */
    public int indexOf(UserHashMap.Entry searchingFor){
        for (int i = 0; i < numElements; i++) {
            if(searchingFor.equals(data[i])){
                return i;
            }
        }

        return -1;
    }

    //Add w/o Position
    /**
     *Method to add a new element to the end of the Dynamic Array
     *
     * @param toBeAdded Element entered by the user to add to the array
     *
     * @author Eoghan Carroll
     */
    public void add(UserHashMap.Entry toBeAdded){
        if(numElements == data.length){
            grow();
        }

        data[numElements++] = toBeAdded;
    }

    //Add w/ Position
    /**
     * Method to add a new element to the Array at a specified location
     * <p>
     * Method takes a string and integer from the user, the String is added to the dynamic array and the integer (after validation) is the index at which it's added
     *
     * @param toBeAdded String to be added to the Dynamic Array
     * @param positionToInsert Index at which to add the String
     *
     * @throws IndexOutOfBoundsException If the position entered is less than 0 or greater than the array size
     *
     * @author Eoghan Carroll
     */
    public void add(UserHashMap.Entry toBeAdded, int positionToInsert){
        if(positionToInsert < 0 || positionToInsert >= numElements){
            throw new IndexOutOfBoundsException("String cannot be added to this position as it is outside the boundaries of the array");
        }
        if(numElements == data.length){
            grow();
        }

        for (int i = data.length-1; i > positionToInsert; i--) {
            data[i] = data[i-1];
        }

        data[positionToInsert] = toBeAdded;
        numElements++;
    }

    //Set
    /**
     * Method to replace one element for another
     * <p>
     * Takes an Element and Integer from the user. The Element will be swapped with the Element found at the index specified by the Integer that was entered and the old Element is returned
     *
     * @param toBeAdded Element to be added to the Array
     * @param positionToInsert Index to insert the new Element
     *
     * @return Element that was replaced by toBeAdded
     *
     * @throws IndexOutOfBoundsException If the position is greater than the size of the array or less than 0
     *
     * @author Eoghan Carroll
     */
    public UserHashMap.Entry set(UserHashMap.Entry toBeAdded, int positionToInsert){
        if(positionToInsert >= data.length || positionToInsert < 0){
            throw new IndexOutOfBoundsException("Position entered is outside the boundaries of the dynamic array");
        }

        UserHashMap.Entry stored = data[positionToInsert];

        data[positionToInsert] = toBeAdded;

        return stored;
    }

    //Remove
    /**
     * Method to delete a single element from the Dynamic Array at a specified index
     *
     * @param positionToRemove Index at which will be deleted from the array
     *
     * @return Element that was deleted from the Dynamic Array
     *
     * @throws IndexOutOfBoundsException If the position is less than 0 or greater than the array size
     *
     * @author Eoghan Carroll
     */
    public UserHashMap.Entry remove(int positionToRemove){
        if(positionToRemove < 0 || positionToRemove >= numElements){
            throw new IndexOutOfBoundsException("Position entered is outside the boundaries of the dynamic array");
        }

        UserHashMap.Entry deletedElement = data[positionToRemove];

        System.arraycopy(data, positionToRemove+1, data, positionToRemove, (numElements-(positionToRemove+1)));
        data[numElements-1] = null;
        numElements--;

        return deletedElement;
    }

    //isEmpty
    /**
     *Method that informs the user if the Array is empty or not
     *
     * @return True if the Array is empty, False if not
     *
     * @author Eoghan Carroll
     */
    public boolean isEmpty(){
        return numElements == 0;
    }


    //Private Features:

    /**
     * Method to expand the size of the Array if it's found to be full
     *
     * @author Eoghan Carroll
     */
    private void grow(){
        UserHashMap.Entry[] expandedArray = new UserHashMap.Entry [data.length * RESIZE_FACTOR];

        System.arraycopy(data, 0, expandedArray, 0, data.length);

        data=expandedArray;
    }
}
