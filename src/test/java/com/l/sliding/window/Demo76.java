package com.l.sliding.window;

import com.l.util.MockUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * <a href='https://leetcode.cn/problems/minimum-window-substring/description/'>[76]-最小覆盖子串</a>
 *
 * @author Administrator
 * @since 2025/5/15 10:41
 */
public class Demo76 {
	private List<String> data;

	@AfterEach
	public void setUp() {
		data = new ArrayList<>(100);
		for (int i = 0; i < 100; i++) {
			data.add(UUID.randomUUID().toString().replace("-", ""));
		}
	}

	private String temp() {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		int length = uuid.length();
		int random = MockUtil.random(length - 3);
		return uuid.substring(random, random + 3);
	}

	@Test
	public void showData() {
		data.forEach(System.err::println);
	}

	@Test
	public void testTemp() {
		for (int i = 0; i < 10000; i++) {
			System.out.println("temp() = " + temp());
		}
	}

	@Test
	public void test01() {

	}

	public String minWindow(String s, String t) {
		String result = "";
		int left = 0;
		int right = 0;
		Map<Character, Integer> map = new HashMap<>(t.length());
		char[] charArray = t.toCharArray();
		for (char item : charArray) {
			map.put(item, 0);
		}
		while (left < s.length()) {
			while (right < s.length() && !need(map)) {
				if (map.containsKey(charArray[right])) {
					map.put(charArray[right], map.get(charArray[right]) + 1);
				}
				right++;
			}
			if (need(map)) {

			}
			left++;
		}
		return result;
	}

	public String minWindow2(String s, String t) {
		char[] sCharArray = s.toCharArray();
		char[] tCharArray = t.toCharArray();
		String min = "";
		for (int i = 0; i < sCharArray.length; i++) {

			Set<Character> set = new HashSet<>(tCharArray.length);
		}
		return min;
	}

	public boolean need(Map<Character, Integer> map) {
		for (Map.Entry<Character, Integer> item : map.entrySet()) {
			if (item.getValue() == 0) {
				return false;
			}
		}
		return true;
	}
}
