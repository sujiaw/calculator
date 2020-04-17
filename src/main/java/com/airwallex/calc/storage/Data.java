package com.airwallex.calc.storage;

import java.util.ArrayList;
import java.util.List;

public class Data<T> {

    private List<T> values;

    public Data() {
        values = new ArrayList<T>();
    }

    public List<T> get() {
        return values;
    }

    public void append(T val) {
        values.add(val);
    }

    public void clear() {
        values.clear();
    }

    public int size() {
        return values.size();
    }

}
