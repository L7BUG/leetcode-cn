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
			String value03 = minWindow3(item, temp);
			System.out.printf("minWindow(%s,%s) = %s%n", item, temp, value01);
			System.out.printf("minWindow2(%s,%s) = %s%n", item, temp, value02);
			System.out.printf("minWindow3(%s,%s) = %s%n", item, temp, value03);
			System.out.println("#################################");
			Assertions.assertEquals(value01, value02);
			Assertions.assertEquals(value01, value03);
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

	@Test
	public void test3() {
		String value01 = minWindow("ADOBECODEBANC", "ABC");
		String value02 = minWindow2("ADOBECODEBANC", "ABC");
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
		int length = s.length();
		int right = 0, left = 0, validLeft = 0, validRight = 0;
		int[] markArray = new int['z' + 1];
		Arrays.fill(markArray, Integer.MIN_VALUE / 2);
		for (int i = 0; i < t.length(); i++) {
			char item = t.charAt(i);
			markArray[item] = markArray[item] == Integer.MIN_VALUE / 2 ? 1 : markArray[item] + 1;
		}
		for (; right < length; right++) {
			markArray[s.charAt(right)]--;
			for (; left < length && validArray(markArray); left++) {
				if (right + 1 - left < minSize) {
					validLeft = left;
					validRight = right + 1;
					minSize = right - left + 1;
				}
				markArray[s.charAt(left)]++;
			}
		}

		return s.substring(validLeft, validRight);
	}

	private boolean validArray(int[] array) {
		for (int i : array) {
			if (i > 0) {
				return false;
			}
		}
		return true;
	}

	// ai解法
	public String minWindow3(String s, String t) {
		if (s == null || t == null || s.length() < t.length()) return "";

		int[] map = new int[128]; // 字符频率数组
		for (char c : t.toCharArray()) map[c]++; // 初始化 t 的字符计数[6,9](@ref)

		int left = 0, right = 0; // 窗口边界
		int minLeft = 0, minLen = Integer.MAX_VALUE; // 最小子串记录
		int count = t.length(); // 待匹配字符数

		while (right < s.length()) {
			char c = s.charAt(right);
			// 若当前字符在 t 中出现，则减少待匹配数
			if (map[c]-- > 0) count--; // [9,11](@ref)
			right++; // 右移窗口

			// 当窗口包含所有 t 的字符时，尝试收缩左边界
			while (count == 0) {
				// 更新最小窗口
				if (right - left < minLen) {
					minLen = right - left;
					minLeft = left;
				}
				// 左移窗口并恢复字符计数
				char leftChar = s.charAt(left);
				if (++map[leftChar] > 0) count++; // 若移出关键字符，需重新匹配[6,9](@ref)
				left++;
			}
		}
		return minLen == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLen);
	}
}
