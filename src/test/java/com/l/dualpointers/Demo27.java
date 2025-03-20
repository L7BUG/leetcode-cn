package com.l.dualpointers;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * [27]移除元素
 *
 * @author l
 * @since 2025/3/1 16:48
 */
public class Demo27 {

	public int removeElement(int[] nums, int val) {
		int sum = 0;
		for (int fast = 0, slow = 0; fast < nums.length; fast++) {
			if (nums[fast] != val) {
				nums[slow++] = nums[fast];
				sum++;
			}
		}
		return sum;
	}

	@Test
	public void test() {
		int[] data1 = {0, 1, 2, 2, 3, 0, 4, 2};
		System.err.println(removeElement(data1, 2));
		System.err.println(Arrays.stream(data1).mapToObj(String::valueOf).collect(Collectors.joining(", ")));
	}
}
