package com.l.sliding.window;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.l.util.MockUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * <a href='https://leetcode.cn/problems/minimum-window-substring/description/'>[76]-最小覆盖子串</a>
 *
 * @author Administrator
 * @since 2025/5/15 10:41
 */
public class Demo76Test {
	private List<String> data;

	@BeforeEach
	public void setUp() {
		data = new ArrayList<>(100);
		for (int i = 0; i < 100; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < 1000; j++) {
				sb.append(UUID.randomUUID().toString().replace("-", ""));
			}
			data.add(sb.toString());
		}
	}

	private String temp() {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		int length = uuid.length();
		int random = MockUtil.random(length - 3);
		return uuid.substring(random, random + 3);
	}

	private String temp(String s) {
		int a = MockUtil.random(s.length());
		int b = MockUtil.random(s.length());
		while (a == b) {
			b = MockUtil.random(s.length());
		}
		return s.substring(Math.min(a, b), Math.max(a, b) + 1);
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
	public void leetcodeTest() {
		for (String item : data) {
			String temp = temp();
			System.out.printf("minWindow(%s,%s) = %s %n", item, temp, minWindow(item, temp));
		}
	}

	@Test
	public void test() {
		for (String item : data) {
			String temp = temp(item);
			String value01 = minWindow(item, temp);
			String value02 = minWindow2(item, temp);
			System.out.printf("minWindow(%s,%s) = %s%n", item, temp, value01);
			System.out.printf("minWindow2(%s,%s) = %s%n", item, temp, value02);
			System.out.println("#################################");
			Assertions.assertEquals(value01, value02);
		}
	}

	@Test
	public void test2() {
		JSONObject jsonObject = JSON.parseObject(this.getClass().getClassLoader().getResourceAsStream("leetcode/76.json"));
		Assertions.assertNotNull(jsonObject);
		String item = jsonObject.getString("s");
		String temp = jsonObject.getString("t");
		String value01 = minWindow(item, temp);
		String value02 = minWindow2(item, temp);
		Assertions.assertEquals(value01, value02);
	}

	public String minWindow(String s, String t) {
		Map<Character, Integer> ori = new HashMap<>();
		Map<Character, Integer> cnt = new HashMap<>();
		int tLen = t.length();
		for (int i = 0; i < tLen; i++) {
			char c = t.charAt(i);
			ori.put(c, ori.getOrDefault(c, 0) + 1);
		}
		int l = 0, r = -1;
		int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
		int sLen = s.length();
		while (r < sLen) {
			++r;
			if (r < sLen && ori.containsKey(s.charAt(r))) {
				cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
			}
			while (check(ori, cnt) && l <= r) {
				if (r - l + 1 < len) {
					len = r - l + 1;
					ansL = l;
					ansR = l + len;
				}
				if (ori.containsKey(s.charAt(l))) {
					cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
				}
				++l;
			}
		}
		return ansL == -1 ? "" : s.substring(ansL, ansR);
	}

	public boolean check(Map<Character, Integer> ori, Map<Character, Integer> cnt) {
		for (Map.Entry<Character, Integer> entry : ori.entrySet()) {
			Character key = entry.getKey();
			Integer val = entry.getValue();
			if (cnt.getOrDefault(key, 0) < val) {
				return false;
			}
		}
		return true;
	}

	// 作者：力扣官方题解
	// 链接：https://leetcode.cn/problems/minimum-window-substring/solutions/257359/zui-xiao-fu-gai-zi-chuan-by-leetcode-solution/
	// 来源：力扣（LeetCode）
	// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

	public String minWindow2(String s, String t) {
		int minSize = Integer.MAX_VALUE;
		char[] charArray = s.toCharArray();
		char[] tCharArray = t.toCharArray();
		int right = 0, validLeft = 0, validRight = 0;
		Map<Character, Integer> markMap = new HashMap<>();
		for (char c : tCharArray) {
			markMap.put(c, markMap.getOrDefault(c, 0) + 1);
		}
		for (int left = 0; left < charArray.length; left++) {
			for (; right < charArray.length; right++) {
				if (validMap(markMap)) {
					if (right - left < minSize) {
						validLeft = left;
						validRight = right;
						minSize = right - left;
					}
					break;
				}
				if (markMap.containsKey(charArray[right])) {
					markMap.put(charArray[right], markMap.get(charArray[right]) - 1);
				}
			}
			if (markMap.containsKey(charArray[left])) {
				markMap.put(charArray[left], markMap.get(charArray[left]) + 1);
			}
		}
		return s.substring(validLeft, validRight);
	}

	private boolean validMap(Map<Character, Integer> map) {
		for (Integer value : map.values()) {
			if (value > 0) {
				return false;
			}
		}
		return true;
	}
}
