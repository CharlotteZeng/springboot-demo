package com.springbootdemo.test;

/**
 * 静态内部类的单例模式
 */
public class SingletonTest {
    private static final SingletonTest singletonTest = new SingletonTest();
    private SingletonTest(){
        System.out.println("构造器");
    }
    static class SingleTonInstance{
        private static final SingletonTest SINGLETON_TEST = new SingletonTest();
    }
    public static SingletonTest getInstance(){
        System.out.println("getInstance");
        return SingleTonInstance.SINGLETON_TEST;
    }
    public static void main(String[] args){

//        SingletonTest singletonTest1 =  new SingletonTest();
        SingletonTest singletonTest =  SingletonTest.getInstance();
        SingletonTest singletonTest2 =  SingletonTest.getInstance();
        System.out.println(singletonTest==singletonTest2);

    }
}
