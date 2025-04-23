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
}
