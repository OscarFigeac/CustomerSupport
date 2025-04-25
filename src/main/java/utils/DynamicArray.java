package utils;

public class DynamicArray {
    private final int RESIZE_FACTOR = 2;

    private int numElements;
    private String[] data;

    //Constructors
    public DynamicArray(){
        this(10);
    }
    public DynamicArray(int orgSize){
        if(orgSize <= 0){
            throw new IllegalArgumentException("The array size is too small");
        }

        data = new String[orgSize];
    }

    //Features:

    //Size
    public int size(){
        return data.length;
    }

    //Get
    public String get(int position){
        if(position < 0 || position >= data.length){
            throw new IndexOutOfBoundsException("Position entered is outside the boundaries of the dynamic array");
        }

        return data[position];
    }

    //IndexOf
    public int indexOf(String searchingFor){
        for (int i = 0; i < numElements; i++) {
            if(searchingFor.equalsIgnoreCase(data[i])){
                return i;
            }
        }

        return -1;
    }

    //Add w/o Position
    public void add(String toBeAdded){
        if(numElements == data.length){
            grow();
        }

        data[numElements++] = toBeAdded;
    }

    //Add w/ Position
    public void add(String toBeAdded, int positionToInsert){
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
    public String set(String toBeAdded, int positionToInsert){
        if(positionToInsert >= data.length || positionToInsert < 0){
            throw new IndexOutOfBoundsException("Position entered is outside the boundaries of the dynamic array");
        }

        String stored = data[positionToInsert];

        data[positionToInsert] = toBeAdded;

        return stored;
    }


    //Private Features:
    private void grow(){
        String [] expandedArray = new String [data.length * RESIZE_FACTOR];

        System.arraycopy(data, 0, expandedArray, 0, data.length);

        data=expandedArray;
    }
}
