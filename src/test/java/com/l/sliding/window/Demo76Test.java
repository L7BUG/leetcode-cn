package com.l.sliding.window;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.l.util.MockUtil;
import com.l.util.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

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
		data = new ArrayList<>(4000);
		for (int i = 0; i < 4000; i++) {
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
	public void leetcodeTest() {
		for (String item : data) {
			String temp = temp();
			System.out.printf("minWindow(%s,%s) = %s %n", item, temp, minWindow(item, temp));
		}
	}

	@Test
	public void test() {
		for (String item : data) {
			String temp = temp();
			AtomicLong value01Time = new AtomicLong(0L);
			AtomicLong value02Time = new AtomicLong(0L);
			String value01 = TestUtils.consumesTime(() -> minWindow(item, temp), value01Time::set);
			String value02 = TestUtils.consumesTime(() -> minWindow(item, temp), value02Time::set);
			System.out.printf("minWindow(%s,%s) = %s,耗时=%s %n", item, temp, value01, value01Time.get());
			System.out.printf("minWindow2(%s,%s) = %s,耗时=%s %n", item, temp, value02, value02Time.get());
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
		String min = "";
		char[] charArray = s.toCharArray();
		int right = 0;
		Map<Character, Integer> markMap = new HashMap<>(t.length());
		for (int i = 0; i < t.length(); i++) {
			markMap.put(t.charAt(i), markMap.getOrDefault(t.charAt(i), 0) + 1);
		}
		for (int left = 0; left < charArray.length; left++) {
			for (; right < charArray.length && !validMap(markMap); right++) {
				if (markMap.containsKey(charArray[right])) {
					markMap.put(charArray[right], markMap.get(charArray[right]) - 1);
				}
			}
			if (validMap(markMap)) {
				String substring = s.substring(left, right);
				if (min.isEmpty() || min.length() > substring.length()) {
					min = substring;
				}
			}
			if (markMap.containsKey(charArray[left])) {
				markMap.put(charArray[left], markMap.get(charArray[left]) + 1);
			}
		}
		return min;
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
