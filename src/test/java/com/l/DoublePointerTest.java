package com.l;

import org.junit.Test;

/**
 * <h1>Leetcode 双指针 问题</h1>
 * <p>主要用于遍历数组，两个指针指向不同的元素，从而协同完成任务。</p>
 *
 * @author ByaoH
 * @date 2021-04-01 18:33
 **/
public class DoublePointerTest {
	/**
	 * <a href="https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/">167. 两数之和 II - 输入有序数组</p></a>
	 */
	@Test
	public void twoSumTest() {
		int[] numbers = {2, 7, 11, 15};
		int target = 9;
		int[] ints = twoSum(numbers, target);
		System.out.println(ints[0] + "---" + ints[1]);
	}

	private int[] twoSum(int[] numbers, int target, int h, int f) {
		int sum = numbers[h] + numbers[f];
		if (sum == target) return new int[]{h + 1, f + 1};
		if (sum > target) return twoSum(numbers, target, h, f - 1);
		else return twoSum(numbers, target, h + 1, f);
	}

	private int[] twoSum(int[] numbers, int target) {
		int h = 0;
		int f = numbers.length - 1;
		return twoSum(numbers, target, h, f);
	}

	//    private int[] twoSum(int[] numbers, int target) {
//        int sum = 0;
////        两个指针
//        int h = 0;
//        int f = numbers.length - 1;
//        while (true) {
//            if (h > f) return new int[2];
//            sum = numbers[h] + numbers[f];
//            if (sum == target) break;
//            if (sum < target) h++;
//            else f--;
//        }
//        return new int[]{numbers[h], numbers[f]};
//    }

	/**
	 * <a href="https://leetcode-cn.com/problems/sum-of-square-numbers/">633. 平方数之和</a>
	 */
	@Test
	public void judgeSquareSumTest() {
		System.out.println("judgeSquareSum(4) = " + judgeSquareSum(4));
	}

	private boolean judgeSquareSum(int c) {
		if (c < 0) return false;
		int h = 0;
		int f = (int) Math.sqrt(c);
		while (h <= f) {
			int t = h * h + f * f;
			if (t == c) return true;
			else if (t > c) f--;
			else h++;
		}
		return false;
	}

	/**
	 * <a href="https://leetcode-cn.com/problems/valid-palindrome-ii/">680. 验证回文字符串 Ⅱ</a>
	 */
	@Test
	public void validPalindromeTest() {
		System.out.println(validPalindrome("abca"));
	}

	private boolean validPalindrome(String s) {
		char[] cs = s.toCharArray();
		for (int i = 0, j = cs.length - 1; i <= j; i++, j--) {
			if (cs[i] != cs[j]) {
				return isPalindrome(cs, i + 1, j) || isPalindrome(cs, i, j - 1);
			}
		}
		return true;
	}

	private boolean isPalindrome(char[] cs, int h, int f) {
		while (h <= f) {
			if (cs[h++] != cs[f--]) {
				return false;
			}
		}
		return true;
	}
}
