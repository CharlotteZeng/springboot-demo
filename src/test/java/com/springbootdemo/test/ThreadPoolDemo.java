package com.springbootdemo.test;

import java.util.concurrent.*;

public class ThreadPoolDemo {
    public static void main(String[] args){
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
//        ExecutorService threadPool = Executors.newCachedThreadPool();
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 5,
                1L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 10; i++) {
            threadPool.execute(new Thread(new Runnable() {
                @Override
                public void run() {
                    /*if (Thread.currentThread().getState()== Thread.State.RUNNABLE) {
                        try {
                            TimeUnit.SECONDS.sleep(3);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }*/
                    System.out.println(Thread.currentThread().getName()+"\t begin work");
                }
            },"T"+i));
        }
        System.out.println("获取cpu核心数："+Runtime.getRuntime().availableProcessors());
    }
}
