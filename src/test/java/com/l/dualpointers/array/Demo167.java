package com.l.dualpointers.array;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * <a href='https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/description/'>[167]两数之和 II - 输入有序数组</a>
 *
 * @author Administrator
 * @since 2025/3/20 10:01
 */
public class Demo167 {
	private final static int MOCK_DATA_SIZE = 100;

	private final Random random = new Random();

	private List<int[]> data;

	@AfterEach
	public void setUp() {
		data = new ArrayList<>(MOCK_DATA_SIZE);
		for (int i = 0; i < MOCK_DATA_SIZE; i++) {
			int[] data = new int[20];
			for (int j = 0; j < 20; j++) {
				data[j] = random.nextInt(100);
			}
			Arrays.sort(data);
			this.data.add(data);
		}
	}

	@Test
	public void test01() {
		for (int[] item : data) {
			int target = random.nextInt(100);
			System.err.println(Arrays.toString(item));
			System.err.println(target);
			System.err.println(Arrays.toString(twoSum(item, target)));
			System.err.println("=======================================");
		}
	}

	public int[] twoSum(int[] numbers, int target) {
		// 由于数组已排序,这里使用快慢指针,一个在前,一个在后
		int slow = 0;
		int fast = numbers.length - 1;
		while (fast > slow) {
			int sum = numbers[fast] + numbers[slow];
			if (sum == target) {
				return new int[]{slow + 1, fast + 1};
			} else if (sum > target) {
				// 大一点让快指针后退
				fast--;
			} else {
				// 小一点让慢指针前进
				slow++;
			}
		}
		return new int[]{-1, -1};
	}
}
