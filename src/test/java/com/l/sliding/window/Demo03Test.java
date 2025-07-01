package com.l.sliding.window;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * <a href='https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/'>[3]-无重复字符的最长子串</a>
 *
 * @author Administrator
 * @since 2025/7/1 15:32
 */
public class Demo03Test {
	private List<String> data;

	@BeforeEach
	public void setUp() {
		data = new ArrayList<>(100);
		for (int i = 0; i < 100; i++) {
			data.add(UUID.randomUUID().toString().replace("-", ""));
		}
	}

	@Test
	public void showData() {
		data.forEach(System.err::println);
	}

	@Test
	public void test01() {
		data.forEach(i -> {
			System.err.printf("lengthOfLongestSubstring(%s) = %s%n", i, lengthOfLongestSubstring(i));
		});
	}

	public int lengthOfLongestSubstring(String s) {
		int max = 1;

		Set<Character> maxSet = new LinkedHashSet<>();
		char[] charArray = s.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			Set<Character> set = new HashSet<>(charArray.length - i);
			for (int j = i; j < charArray.length; j++) {
				if (set.contains(charArray[j])) {
					break;
				}
				set.add(charArray[j]);
			}
			if (set.size() > max) {
				max = set.size();
				maxSet = set;
			}
		}
		System.err.println("maxSet = " + maxSet);
		return max;
	}
}
