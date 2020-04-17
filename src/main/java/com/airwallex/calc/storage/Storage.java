package com.airwallex.calc.storage;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class Storage {

    protected Deque<Data> data;

    public Storage() {
        data = new ArrayDeque<>();
    }

    public Data getLast() {
        return data.removeLast();
    }

    public void addLast(Data num) {
        data.addLast(num);
    }

    public Data peekLast() {
        return data.getLast();
    }

    public Data getFirst() {
        return data.removeFirst();
    }

    public void addFirst(Data num) {
        data.addFirst(num);
    }

    public Data peekFirst() {
        return data.getFirst();
    }

    public int size() {
        return data.size();
    }

    public void clear() {
        data.clear();
    }

    public Iterator iterator() {
        return data.iterator();
    }

}
