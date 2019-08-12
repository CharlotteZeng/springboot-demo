package com.springbootdemo.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyCallable implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("正在执行call方法。。。。。。");
        return 1024;
    }
}
public class CallableDemo {
    public static void main(String[] args){
        FutureTask<Integer> futureTask = new FutureTask<>(new MyCallable());
        Thread t = new Thread(futureTask);
        try {
            t.start();
            Integer integer = futureTask.get();
            System.out.println("call返回值："+integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


}
