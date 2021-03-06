package com.springbootdemo.test;

import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class TestMain {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
//        testLock();
//testCyclicBarrier();
        System.out.println(Runtime.getRuntime().availableProcessors());
        JSONArray list = new JSONArray();
        list.add("abc");
        list.add("abc1");
        list.add("abc2");
        list.add("abc3");
        String symbol = StringUtils.join(list.toArray(), ",");
        System.out.println(symbol);
    }

    private static void testCyclicBarrier() throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier =new CyclicBarrier(3);
//        cyclicBarrier..await();

    }
    /**
     * 一个加1一个减1，来5轮
     */

    private static void testLock(){
        ShareData shareData = new ShareData();
        new Thread(()->{
            for (int i = 0; i < 6; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        },"T1").start();
        new Thread(()->{
            for (int i = 0; i < 6; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        },"T2").start();
        new Thread(()->{
            for (int i = 0; i < 6; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        },"T3").start();
        new Thread(()->{
            for (int i = 0; i < 6; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        },"T4").start();

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
