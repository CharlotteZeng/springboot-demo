package com.springbootdemo.test;

public class JVMParamsTest {

    static String a ;
    public static void main(String[] args){
        /*String b = new String("222222");
        System.out.println(1111111);
        a=b;
        System.out.println(a==b);

        String c = "abc";
        String d ="ab"+"c";
        System.out.println(c==d);*/
        new B();
    }

}
class A{
    String name ="";
    public A(){
        System.out.println("name="+name);
    }
}
class B{
    A a = new A();
    String add = "";
    public B(){
        System.out.println("add="+add);
    }
}