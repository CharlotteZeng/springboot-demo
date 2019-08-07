package com.springbootdemo.test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3, false);
        for (int i = 0; i < 7; i++) {

            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + "\t停车3秒");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                    System.out.println("释放车位");
                }
            }, String.valueOf(i)).start();
        }
        ReentrantLock lock = new ReentrantLock();

    }
}
