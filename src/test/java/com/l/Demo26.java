package com.l;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * <a href='https://leetcode.cn/problems/remove-duplicates-from-sorted-array/description/'>[26]删除有序数组中的重复项</a>
 *
 * @author l
 * @since 2025/2/24 09:10
 */
public class Demo26 {
	public int removeDuplicates(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		int slow = 0, fast = 0;
		while (fast < nums.length) {
			if (nums[fast] != nums[slow]) {
				slow++;
				// 维护 nums[0..slow] 无重复
				nums[slow] = nums[fast];
			}
			fast++;
		}
		// 数组长度为索引 + 1
		return slow + 1;
	}

	@Test
	public void test() {
		List<int[]> arrays = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			int step = 10;
			int[] arr = new int[step];
			for (int j = 0; j < step; j++) {
				int data = random.nextInt() % step;
				arr[j] = data;
			}
			arrays.add(arr);
		}
		for (int[] array : arrays) {
			Arrays.sort(array);
			System.err.println(Arrays.stream(array).mapToObj(String::valueOf).collect(Collectors.joining(",")));
			System.err.println(removeDuplicates(array));
			System.out.println("===");
		}
	}
}
