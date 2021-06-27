package org.pratap.concurrent.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedCollectionApp {

    public static void main(String[] args) {

        // add and remove methods are synchronized
        List<Integer> integers = Collections.synchronizedList(new ArrayList<>());

        Thread t1 = new Thread(() -> {
           for (int i = 0; i < 1000; ++i){
               integers.add(i);
           }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; ++i){
                integers.add(i);
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("integers size : "+integers.size());

    }
}
