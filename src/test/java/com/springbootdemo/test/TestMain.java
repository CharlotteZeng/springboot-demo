package com.springbootdemo.test;

import java.util.HashMap;
import java.util.Map;

public class TestMain {
    public static void main(String[] args){


    }
    /**
     * 运算规则是：两个数转为二进制，然后从高位开始比较，如果相同则为0，不相同则为1。
     */
    private static void test(){
        System.out.println(2^1);
        System.out.println(2^2);
        System.out.println(2^3);
        System.out.println(2^4);
        System.out.println(2^5);
    }
    /**
     * 练习hash碰撞
     */
    private static void testHashCode(){
        String s = "Aa";
        String s1 = "BB";
        int i = s.hashCode();
        int i1 = s1.hashCode();
        //true 说明两个字符串的hashcode一致
        System.out.println(i==i1);

        Map map = new HashMap();
        MyBean4Map myBean4Map = new MyBean4Map("11");
        MyBean4Map myBean4Map1 = new MyBean4Map("22");

        System.out.println(myBean4Map.hashCode()==myBean4Map1.hashCode());

        map.put(myBean4Map,"s");
        map.put(myBean4Map1,"s1");
        map.put(null,"111");
        map.get(myBean4Map);
        map.get(myBean4Map1);
    }

    /**
     * 此类可以造出hash冲突的两个对象 因为重新了hashcode方法并且固定为BB的hashcode
     */
    public static class MyBean4Map{
        private String s ;

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }

        public MyBean4Map(){

        }
        public MyBean4Map(String s){
            this.s = s;
        }
        @Override
        public int hashCode() {
            return "BB".hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }
}
