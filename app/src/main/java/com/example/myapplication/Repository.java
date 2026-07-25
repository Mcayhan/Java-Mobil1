package com.example.myapplication;

import java.util.ArrayList;

public class Repository <T>{
    private ArrayList<T> items = new ArrayList<>();

    public void add(T item){
        items.add(item);
        System.out.println("Item added.");
    }

    public T get(int index) {
        if (index < 0 || index >= items.size()){
            throw new RecordNotFoundException("Item not found at index: " + index);
        }
        return items.get(index);
    }

    public void delete(int index) {
        if(index < 0 || index >= items.size()){
            throw new RecordNotFoundException("Cannot delete. Item not found at index: " + index);
        }
        items.remove(index);
        System.out.println("Item deleted.");
    }
}
