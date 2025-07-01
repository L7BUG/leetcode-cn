package com.l.dualpointers.array;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * <a href='https://leetcode.cn/problems/move-zeroes/description/'>[283]-移动零</a>
 *
 * @author Administrator
 * @since 2025/3/4 14:56
 */
public class Demo283 {
	private final static int STEP = 1000;
	private final static Random RANDOM = new Random();
	private final List<int[]> testData = new ArrayList<>(STEP);

	public static boolean valid(int[] nums) {
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] == 0 && nums[i + 1] != 0) {
				return false;
			}
		}
		return true;
	}

	@AfterEach
	public void setUp() throws Exception {
		for (int i = 0; i < STEP; i++) {
			int[] data = new int[10];
			for (int j = 0; j < 9; j++) {
				data[j] = RANDOM.nextInt(9) + 1;
			}
			data[RANDOM.nextInt(data.length)] = 0;
			data[RANDOM.nextInt(data.length)] = 0;
			data[RANDOM.nextInt(data.length)] = 0;
			data[RANDOM.nextInt(data.length)] = 0;
			data[data.length - 1] = RANDOM.nextInt(9) + 1;
			testData.add(data);
		}
	}

	public void moveZeroes(int[] nums) {
		int length = nums.length;
		for (int slow = 0, fast = slow + 1; slow < length && fast < length; ) {
			if (nums[slow] != 0 && nums[fast] != 0) {
				slow++;
				fast++;
			}
			if (nums[slow] == 0 && nums[fast] == 0) {

			}
		}
	}

	public void moveZeroes2(int[] nums) {
		int sum = 0;
		for (int fast = 0, slow = 0; fast < nums.length; fast++) {
			if (nums[fast] != 0) {
				nums[slow++] = nums[fast];
				sum++;
			}
		}
		for (; sum < nums.length; sum++) {
			nums[sum] = 0;
		}
	}

	@Test
	public void moveZeroes2Test() {
		Random random = new Random();
		int[] arr = new int[10];
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < arr.length; j++) {
				arr[j] = random.nextInt(9) + 1;
			}
			arr[random.nextInt(arr.length)] = 0;
			arr[random.nextInt(arr.length)] = 0;
			arr[random.nextInt(arr.length)] = 0;
			System.err.println("==================" + i + "==================");
			System.err.println(Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(",")));
			moveZeroes2(arr);
			System.err.println(Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(",")));
			Assertions.assertTrue(valid(arr));
		}
	}

	private boolean validData(int[] nums) {
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] == 0 && nums[i + 1] != 0) {
				return false;
			}
		}
		return true;
	}

	private void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	@Test
	public void test01() {
		for (int[] data : testData) {
			System.err.println("====================");
			System.err.println(Arrays.stream(data).mapToObj(String::valueOf).collect(Collectors.joining(",")));
			moveZeroes(data);
			System.err.println(Arrays.stream(data).mapToObj(String::valueOf).collect(Collectors.joining(",")));
			System.err.println("====================");
		}
	}

	@Test
	public void test02() {
		for (int[] data : testData) {
			System.err.println("====================");
			System.err.println(Arrays.stream(data).mapToObj(String::valueOf).collect(Collectors.joining(",")));
			Assertions.assertFalse(valid(data));
			moveZeroes2(data);
			System.err.println(Arrays.stream(data).mapToObj(String::valueOf).collect(Collectors.joining(",")));
			Assertions.assertTrue(valid(data));
		}
	}

	@Test
	public void init() {
		for (int[] testDatum : testData) {
			System.err.println(Arrays.stream(testDatum).mapToObj(String::valueOf).collect(Collectors.joining(",")));
		}
	}
}
