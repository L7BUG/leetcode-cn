package com.l.dualpointers.array;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <a href='https://leetcode.cn/problems/longest-palindromic-substring/description/'>[5]-最长回文子串</a>
 *
 * @author Administrator
 * @since 2025/4/1 18:04
 */
public class Demo5 {
	List<String> mockData = new ArrayList<>(100);

	@Before
	public void setUp() throws Exception {
		for (int i = 0; i < 100; i++) {
			mockData.add(UUID.randomUUID().toString().replace("-", ""));
		}
	}

	@After
	public void tearDown() throws Exception {
		mockData.clear();
	}

	@Test
	public void test() {
		for (String item : mockData) {
			System.err.println(MessageFormat.format("{0} => {1}", item, longestPalindrome(item)));
		}
	}

	@Test
	public void test2() {
		System.err.println(MessageFormat.format("{0} => {1}", "ccc", longestPalindrome("ccc")));
	}

	public String longestPalindrome(String s) {
		int length = s.length();
		String max = "";
		for (int i = 0; i < length; i++) {
			String s1 = findPalindrome(s, i, i);
			String s2 = findPalindrome(s, i, i + 1);
			max = max.length() > s1.length() ? max : s1;
			max = max.length() > s2.length() ? max : s2;
		}
		return max;
	}

	public String findPalindrome(String s, int l, int r) {
		int length = s.length();
		while (l >= 0 && r < length && s.charAt(l) == s.charAt(r)) {
			l--;
			r++;
		}
		return s.substring(l + 1, r);
	}
}
