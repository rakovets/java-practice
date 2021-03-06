package com.rakovets.course.java.core.example.concurrency.thread_synchronization.model;

import com.rakovets.course.java.core.util.AnsiColorCode;
import com.rakovets.course.java.core.util.StandardOutputUtil;

public class Store {
    private int product = 0;

    public synchronized void get() {
        while (product < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        product--;
        StandardOutputUtil.printlnWithTimeAndThread("Consumer got 1 product from the Store.", AnsiColorCode.FG_RED_BOLD);
        StandardOutputUtil.printlnWithTimeAndThread("Total products in the Store: " + product, AnsiColorCode.FG_BLUE_BOLD);
        notify();
    }

    public synchronized void put() {
        while (product >= 10) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        product++;
        StandardOutputUtil.printlnWithTimeAndThread("Producer added 1 product to the Store.", AnsiColorCode.FG_GREEN_BOLD);
        StandardOutputUtil.printlnWithTimeAndThread("Total products in the Store: " + product, AnsiColorCode.FG_BLUE_BOLD);
        notify();
    }
}
