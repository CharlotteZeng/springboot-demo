package com.springbootdemo.test;

import com.alibaba.fastjson.JSON;

import java.util.*;

public class ArithmeticClass {
    public static void main(String[] args){
//        String s = "pwwkew";
//        System.out.println(lengthOfLongestSubstring3(s));
        reverse(456);
    }
//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

    /**
     * 复杂度分析

     时间复杂度：O(n^3)O(n
     3
     ) 。

     要验证索引范围在 [i, j)[i,j) 内的字符是否都是唯一的，我们需要检查该范围中的所有字符。 因此，它将花费 O(j - i)O(j−i) 的时间。

     对于给定的 i，对于所有 j \in [i+1, n]j∈[i+1,n] 所耗费的时间总和为：

     \sum_{i+1}^{n}O(j - i)
     i+1
     ∑
     n
     ​
     O(j−i)

     因此，执行所有步骤耗去的时间总和为：

     O\left(\sum_{i = 0}^{n - 1}\left(\sum_{j = i + 1}^{n}(j - i)\right)\right) = O\left(\sum_{i = 0}^{n - 1}\frac{(1 + n - i)(n - i)}{2}\right) = O(n^3)
     O(
     i=0
     ∑
     n−1
     ​
     (
     j=i+1
     ∑
     n
     ​
     (j−i)))=O(
     i=0
     ∑
     n−1
     ​

     2
     (1+n−i)(n−i)
     ​
     )=O(n
     3
     )

     空间复杂度：O(min(n, m))O(min(n,m))，我们需要 O(k)O(k) 的空间来检查子字符串中是否有重复字符，其中 kk 表示 Set 的大小。而 Set 的大小取决于字符串 nn 的大小以及字符集/字母 mm 的大小。

     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int result = 0;
        for (int i = 0; i < chars.length; i++) {
            for (int j = i+1; j < chars.length; j++) {
//                判断如果不重复就与原值比较并取大的那个值，原值初始化为0
                if (allUnique(s,i,j))result=Math.max(result,j-i);
            }
        }
        return result;
    }
    //判断字符串是否没有重复字符
    public static boolean allUnique(String s,int start,int end){
        //一个临时的容器存储不重复的字符
        Set<Character> set = new HashSet<>();
        //循环给定的开始与结束的角标内的元素并放到临时容器中，如果有重复直接返回false，没有重复字符就返回true
        for (int i = start; i < end; i++) {
            char c = s.charAt(i);
            if (set.contains(c)) return false;
            set.add(c);

        }
        return true;
    }

    /**
    通过使用 HashSet 作为滑动窗口，我们可以用 O(1)O(1) 的时间来完成对字符是否在当前的子字符串中的检查。

    滑动窗口是数组/字符串问题中常用的抽象概念。 窗口通常是在数组/字符串中由开始和结束索引定义的一系列元素的集合，即 [i, j)[i,j)（左闭，右开）。而滑动窗口是可以将两个边界向某一方向“滑动”的窗口。例如，我们将 [i, j)[i,j) 向右滑动 11 个元素，则它将变为 [i+1, j+1)[i+1,j+1)（左闭，右开）。

    回到我们的问题，我们使用 HashSet 将字符存储在当前窗口 [i, j)[i,j)（最初 j = ij=i）中。 然后我们向右侧滑动索引 jj，如果它不在 HashSet 中，我们会继续滑动 jj。直到 s[j] 已经存在于 HashSet 中。此时，我们找到的没有重复字符的最长子字符串将会以索引 ii 开头。如果我们对所有的 ii 这样做，就可以得到答案。

    Java


    */

    public static int lengthOfLongestSubstring1(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                System.out.println("remove before"+set);
                set.remove(s.charAt(i++));
                System.out.println("remove after"+set);
            }
        }
        return ans;
    }

    /**
     * 滑动窗口算法解题
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring3(String s) {
        int ans = 0;
        char[] chars = s.toCharArray();
        Map<Character,Integer> map = new HashMap<>();
        for (int start = 0,end=0; end < chars.length; end++) {
            char c = chars[end];
            if (map.containsKey(c))
                start = Math.max(start,map.get(c));
            ans=Math.max(ans,end-start+1);
            map.put(c,end+1);
        }
        return ans;
    }

    /**
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     * @param x
     * @return
     */
    public static int reverse(int x){
        System.out.println("原数字"+x);
        int rev = 0;
        while (x!=0){
            int pop = x%10;
            x = x/10;
            if (rev>Integer.MAX_VALUE/10||(rev==Integer.MAX_VALUE&&pop>Integer.MAX_VALUE%10)){
                System.out.println("return 0");
                return 0;
            }
            if (rev<Integer.MIN_VALUE/10||(rev==Integer.MIN_VALUE&&pop<Integer.MAX_VALUE%10)){
                System.out.println("return 0");
                return 0;
            }
            rev = rev*10+pop;
        }
        System.out.println("结果数字"+rev);
        return rev;
    }

}
