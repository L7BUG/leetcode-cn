package com.l.dualpointers.array;

import com.l.util.MockUtil;
import org.junit.Before;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <a href='https://leetcode.cn/problems/kLl5u1/description/'>[LCR 006]-两数之和 II - 输入有序数组</a>
 *
 * @author Administrator
 * @since 2025/5/15 10:13
 */
public class Lcr006Test {
	private List<int[]> list;

	@Before
	public void setUp() throws Exception {
		list = new ArrayList<>(100);
		for (int i = 0; i < 100; i++) {
			int[] temp = MockUtil.randomArr(10, 100);
			Arrays.sort(temp);
			list.add(temp);
		}
	}

	@Test
	public void test() {

		for (int[] item : list) {
			int random = MockUtil.random(100);
			int[] ints = twoSum(item, random);
			System.out.println(MessageFormat.format("数组=>{0},index[{1}]+index[{2}]={3}", Arrays.toString(item), ints[0], ints[1], random));
		}
	}

	public int[] twoSum(int[] numbers, int target) {
		int slow = 0, fast = numbers.length - 1;
		while (slow < fast) {
			int sum = numbers[slow] + numbers[fast];
			if (sum == target) {
				return new int[]{slow, fast};
			}
			if (sum < target) {
				slow++;
			}
			if (sum > target) {
				fast--;
			}
		}
		return new int[]{0, 0};
	}
}
