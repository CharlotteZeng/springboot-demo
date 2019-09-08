package com.springbootdemo.test;

public class MyClassLoader {
    public static void main(String[] args){
        System.out.println("extclassloader加载的路径:"+System.getProperty("java.ext.dirs"));
        System.out.println("AppClassLoader加载的路径:"+System.getProperty("java.class.path"));
    }
}
