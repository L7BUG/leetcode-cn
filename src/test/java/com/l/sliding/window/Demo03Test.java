package com.l.sliding.window;


import org.junit.jupiter.api.Assertions;
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

	@Test
	public void test02() {
		data.forEach(i -> {
			System.err.printf("lengthOfLongestSubstring(%s) = %s%n", i, lengthOfLongestSubstring2(i));
		});
	}

	@Test
	public void valid() {
		for (String item : data) {
			System.err.printf("item = [%s]%n", item);
			int a = lengthOfLongestSubstring(item);
			int b = lengthOfLongestSubstring2(item);
			int c = lengthOfLongestSubstring3(item);
			Assertions.assertEquals(a, b);
			Assertions.assertEquals(c, b);
		}
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

	public int lengthOfLongestSubstring2(String s) {
		Set<Character> set = new HashSet<>(s.length());
		char[] charArray = s.toCharArray();
		int max = 1;
		int left = 0;
		int right = 0;
		while (right < charArray.length) {
			if (!set.contains(charArray[right])) {
				set.add(charArray[right]);
				right++;
				if (max < set.size()) {
					max = set.size();
				}
				max = Math.max(max, set.size());
				continue;
			}
			while (left <= right && set.contains(charArray[right])) {
				set.remove(charArray[left++]);
			}
		}
		return max;
	}

	public int lengthOfLongestSubstring3(String s) {
		// 哈希集合，记录每个字符是否出现过
		Set<Character> occ = new HashSet<>(s.length());
		int n = s.length();
		// 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
		int rk = -1, ans = 0;
		for (int i = 0; i < n; ++i) {
			if (i != 0) {
				// 左指针向右移动一格，移除一个字符
				occ.remove(s.charAt(i - 1));
			}
			while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
				// 不断地移动右指针
				occ.add(s.charAt(rk + 1));
				++rk;
			}
			// 第 i 到 rk 个字符是一个极长的无重复字符子串
			ans = Math.max(ans, rk - i + 1);
		}
		return ans;
	}
	// 作者：力扣官方题解
	// 链接：https://leetcode.cn/problems/longest-substring-without-repeating-characters/solutions/227999/wu-zhong-fu-zi-fu-de-zui-chang-zi-chuan-by-leetc-2/
	// 来源：力扣（LeetCode）
	// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
