package com.sz.bewater.practice.interview.cg;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class MyPrintABCOrder {
    private static Thread printAThread;
    private static Thread printBThread;
    private static Thread printCThread;

    public static void main(String[] args) {
        printAThread = new Thread(() -> {
            while (true){
                System.out.println("A");
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                LockSupport.unpark(printBThread);
                LockSupport.park();
            }
        });
        printBThread = new Thread(() -> {
            while (true){
                LockSupport.park();
                System.out.println("B");
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                LockSupport.unpark(printCThread);
            }


        });
        printCThread = new Thread(() -> {
            while (true){
                LockSupport.park();
                System.out.println("C");
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                LockSupport.unpark(printAThread);
            }

        });
        printAThread.start();
        printBThread.start();
        printCThread.start();



    }
}
