package com.springbootdemo.test;

class DeadLock implements Runnable{

    private String lockA;
    private String lockB;
    public DeadLock(String lockA,String lockB){
        this.lockA=lockA;
        this.lockB=lockB;

    }
    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t 得到lockA锁 等待lockB锁");
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t 得到lockB锁 等待lockA锁");

            }
        }
    }
}
public class DeadLockDemo {
    public static void main(String[] args){
        new Thread(new DeadLock("lockA","lockB"),"T1").start();
        new Thread(new DeadLock("lockB","lockA"),"T2").start();
        /**
         * 死锁后 使用jps -l 命令得到死锁进程数 然后使用jstack 进程数 命令得到栈日志
         *
         *
         Java stack information for the threads listed above:
         ===================================================
         "T2":
         at com.springbootdemo.test.DeadLock.run(DeadLockDemo.java:17)
         - waiting to lock <0x000000076abfe9c0> (a java.lang.String)
         - locked <0x000000076abfe9f8> (a java.lang.String)
         at java.lang.Thread.run(Thread.java:748)
         "T1":
         at com.springbootdemo.test.DeadLock.run(DeadLockDemo.java:17)
         - waiting to lock <0x000000076abfe9f8> (a java.lang.String)
         - locked <0x000000076abfe9c0> (a java.lang.String)
         at java.lang.Thread.run(Thread.java:748)

         Found 1 deadlock.

         *
         *
         */
    }
}
