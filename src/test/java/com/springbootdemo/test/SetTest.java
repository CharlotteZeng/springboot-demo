package com.springbootdemo.test;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Set集合练习
 */
public class SetTest {

}
class SetType{
    int i;

    public SetType(int i) {
        this.i = i;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof SetType&&i==((SetType) o).i;
    }

    @Override
    public String   toString() {
        final StringBuffer sb = new StringBuffer("SetType{");
        sb.append("i=").append(i);
        sb.append('}');
        return sb.toString();
    }
}
class HashType extends SetType{

    public HashType(int i) {
        super(i);
    }

    @Override
    public int hashCode() {
        return i;
    }
}
class TreeType extends SetType implements Comparable<TreeType>{

    public TreeType(int i) {
        super(i);
    }

    @Override
    public int compareTo(TreeType o) {
        return 0;
    }
}
class TypesForSets{
    static <T> Set<T> fill(Set<T> set, Class<T> type) {
        try {
            for (int i=0;i<10;i++){
                set.add(type.getConstructor(int.class).newInstance(i));
            }
        } catch (Exception e){
            throw new RuntimeException();
        }
        return set;
    }
    static <T> void test(Set<T> set,Class<T> type){
        fill(set,type);
        fill(set,type);
        fill(set,type);
        System.out.println(set);
    }
    public static void main(String[] args){
        test(new HashSet<HashType>(),HashType.class);
        test(new LinkedHashSet<HashType>(),HashType.class);
        test(new TreeSet<TreeType>(),TreeType.class);
        System.out.println(-1);

        System.out.println();
        PriorityQueue priorityQueue = new PriorityQueue();
        for (int i =0;i<=20;i++) {
            priorityQueue.add(new TestClass());
        }
        int size = priorityQueue.size();
        for (int i = 0;i<size;i++){

            Object poll = priorityQueue.poll();
            System.out.println(poll+"");
        }

    }
}
class TestClass implements Comparable<TestClass>{
    Integer i = new Random().nextInt(100);

    @Override
    public int compareTo(TestClass o) {
        if (null == i||null==o)
            return -1;
        int d = i-o.i;
        return d<0?-1:d==0?0:1;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TestClass{");
        sb.append("i=").append(i);
        sb.append('}');
        return sb.toString();
    }

}
class QueueBehavior{
    private static int count = 10;
    static <T> void test(Queue<T> queue,Generator<T> gen){
        for (int i=0;i<count;i++){
            queue.offer(gen.next());
        }
        while (queue.peek()!=null){
            System.out.print(queue.remove()+" ");
        }
        System.out.println();
    }
    static class Gen implements Generator{
        String[] s = ("one two three four five six seven eight nine ten").split(" ");
        int i;
        @Override
        public Object next() {
            return s[i++];
        }
    }
    public static void main(String[] args){
        /*test(new LinkedList<String>(),new Gen());
        test(new PriorityQueue<String>(),new Gen());
        test(new ArrayBlockingQueue<String>(count),new Gen());
        test(new ConcurrentLinkedDeque<String>(),new Gen());
        test(new LinkedBlockingDeque<String>(),new Gen());
        test(new PriorityBlockingQueue<String>(),new Gen());
        */
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i=0;i<20;i++){
            queue.add(new MyGenerator().next());
        }
        System.out.println("java编程思想容器深入研究习题11：");
        while (!queue.isEmpty())
        System.out.print(queue.poll()+" ");
    }
    static class MyGenerator implements Generator,Comparable{
        private Integer i = new Random().nextInt(100);
        @Override
        public Integer next() {
            return i;
        }

        @Override
        public int compareTo(Object o) {
            if (null==o||!(o instanceof Integer))
                return -1;

            return i-(Integer)o;
        }
    }

}
