package com.l.sliding.window;

import com.l.util.MockUtil;
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

	public String minWindow(String s, String t) {
		Map<Character, Integer> ori = new HashMap<Character, Integer>();
		Map<Character, Integer> cnt = new HashMap<Character, Integer>();
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

	public boolean need(Map<Character, Integer> map) {
		for (Map.Entry<Character, Integer> item : map.entrySet()) {
			if (item.getValue() == 0) {
				return false;
			}
		}
		return true;
	}
}
