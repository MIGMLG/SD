package com.company;

import java.util.ArrayList;

public class SynchronizedArrayList {

    private ArrayList list = new ArrayList();

    public synchronized void addObject(Object o) {
        list.add(o);
    }

    public synchronized ArrayList get() {
        return list;
    }
}
