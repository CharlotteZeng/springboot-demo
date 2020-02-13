package com.springbootdemo.test;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class CollectionsTest {
    public String name;

    public CollectionsTest(String name) {

        this.name = name;

    }
    public static void main(String[] args){
        List<String> l = new ArrayList<>();
        l.add(0,"1");
        System.out.println(JSON.toJSONString(l));
        SList<String> sl = new SList<>();
        SListIterator<String> iterator = sl.iterator();
        iterator.insert("1");
        iterator.insert("2");
        sl.toString();
    }

    static class SList<T>{
        Link<T> link = new Link<>(null);
        SListIterator<T> iterator(){
            return new SListIterator<T>(link);
        }

        public String toString() {
            if(link.next == null) return "SList: []";
            System.out.println("SList: [");
            SListIterator<T> it = this.iterator();
            StringBuilder s = new StringBuilder();
            while(it.hasNext()) {
                s.append(it.next() + (it.hasNext() ? ", " : ""));
            }
            return s + "]";
        }

    }
    static class SListIterator<T>{
        Link<T> link;
        SListIterator(Link<T> link){
            this.link=link;
        }
        public boolean hasNext(){
            return link.next!=null;
        }
        public Link<T> next(){
            return link.next;
        }
        public void insert(T t){
            link.next=new Link<T>(t,link.next);
            link=link.next;
        }
    }
    static class Link<T>{
        T t;
        Link<T> next;
        Link(T t,Link<T> next){
          this.t=t;
          this.next=next;
        }
        Link(T t){
            this(t,null);
        }
    }
}
