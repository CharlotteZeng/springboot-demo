package com.springbootdemo.test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class MyClassLoader {
    public static void main(String[] args) throws IOException {


        HashMap<Integer,String>  hashmap= new HashMap<>();
        for(Object obj : hashmap.entrySet()){}
    }

    private static int mytest(int i) {
        return i+100;
    }

}
