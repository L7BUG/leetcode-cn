package com.l.util;

import java.util.Random;

/**
 * MockUtil
 *
 * @author Administrator
 * @since 2025/4/25 14:53
 */
public class MockUtil {
	private static final Random R = new Random();

	public static int[] randomArr(int size, int max) {
		int[] data = new int[size];
		for (int i = 0; i < size; i++) {
			data[i] = R.nextInt(max);
		}
		return data;
	}

	public static int random(int max) {
		return R.nextInt(max);
	}
}
