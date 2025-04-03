package com.l.dualpointers.array;

/**
 * <a href='https://leetcode.cn/problems/reverse-string/description/'>[344]-反转字符串</a>
 *
 * @author Administrator
 * @since 2025/3/31 10:21
 */
public class Demo344 {

	public void reverseString(char[] s) {
		int slow = 0, fast = s.length - 1;
		while (slow < fast) {
			char temp = s[slow];
			s[slow] = s[fast];
			s[fast] = temp;
			slow++;
			fast--;
		}
	}
}
