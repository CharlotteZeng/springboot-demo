package com.springbootdemo.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource{
    private Lock lock =new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private int num=1;
    public void print5(){
        lock.lock();
        try {
            //等待 如果此线程不是1线程 就进入等待
            if (num!=1){
                lock.wait();
            }
            //干活
            for (int i = 0; i <5; i++) {
                System.out.println(Thread.currentThread().getName()+"线程开始工作 "+i+"次");
            }
            num=2;
            condition2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void print10(){
        lock.lock();
        try {
            //等待 如果此线程不是1线程 就进入等待
            if (num!=2){
                lock.wait();
            }
            //干活
            for (int i = 0; i <10; i++) {
                System.out.println(Thread.currentThread().getName()+"线程开始工作 "+i+"次");
            }
            num=3;
            condition3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void print15(){
        lock.lock();
        try {
            //等待 如果此线程不是1线程 就进入等待
            if (num!=3){
                lock.wait();
            }
            //干活
            for (int i = 0; i <15; i++) {
                System.out.println(Thread.currentThread().getName()+"线程开始工作 "+i+"次");
            }
            num=1;
            condition1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
public class ReentrantLockDemo {
    public static void main(String[] args){
        ShareResource shareResource=new ShareResource();
        new Thread(()->{
            shareResource.print5();
        },"T1").start();
        new Thread(()->{
            shareResource.print10();
        },"T2").start();
        new Thread(()->{
            shareResource.print15();
        },"T3").start();
    }
}
