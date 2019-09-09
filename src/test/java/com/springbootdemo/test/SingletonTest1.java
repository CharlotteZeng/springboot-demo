package com.springbootdemo.test;

public class SingletonTest1 {
    private static SingletonTest1 singletonTest1;
    private SingletonTest1(){}
    public static SingletonTest1 getInstatnce(){
        if (singletonTest1==null){
            return new SingletonTest1();
        }
        return singletonTest1;
    }
    public synchronized  static void main(String[] args){

        SingletonTest1 s1 = getInstatnce();
        SingletonTest1 s2 = getInstatnce();
        System.out.println(s1==s2);
    }
}
