package com.l;

import org.junit.Test;

import java.util.*;

/**
 * <a href='https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/'>350. 两个数组的交集 II</a>
 * <p>给定两个数组，编写一个函数来计算它们的交集。</p>
 *
 * @author luliangyu
 * @date 2021-07-26 09:42
 */
public class Demo350 {
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list = new LinkedList<>();
        Map<Integer, Integer> map;
        int[] a, b;
        if (nums1.length < nums2.length) {
            a = nums1;
            b = nums2;
        } else {
            a = nums2;
            b = nums1;
        }
        map = new HashMap<>(a.length);
        for (int i : a) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }
        for (int k : b) {
            Integer v = map.get(k);
            if (v != null && v >= 1) {
                list.add(k);
                v -= 1;
                map.put(k, v);
            }
        }
        int[] data = new int[list.size()];
        int i = 0;
        for (Integer item : list) {
            data[i] = item;
            i++;
        }
        return data;
    }

    /**
     * 双指针方式求交集
     *
     * @param nums1 数组1
     * @param nums2 数组2
     * @return 交集
     */
    public int[] intersect2(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list = new LinkedList<>();
        int p1 = 0;
        int p2 = 0;
        while (p1 < nums1.length && p2 < nums2.length) {
            int n1 = nums1[p1];
            int n2 = nums2[p2];
            //相等两个指针网后移
            if (n1 == n2) {
                p1++;
                p2++;
                list.add(n1);
                continue;
            }
            //较小的指针往后移
            if (n1 < n2) {
                p1++;
            } else {
                p2++;
            }
        }
        return list.stream().mapToInt(Integer::valueOf).toArray();
    }

    @Test
    public void test() {
        int[] intersect = intersect2(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4});
        for (int i : intersect) {
            System.err.println(i);
        }
    }
}
