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
}
