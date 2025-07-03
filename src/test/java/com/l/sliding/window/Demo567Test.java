package com.l.sliding.window;

import com.l.util.MockUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * <a href='https://leetcode.cn/problems/permutation-in-string/description/'>[567]-字符串的排列</a>
 *
 * @author Administrator
 * @since 2025/7/3 16:10
 */
public class Demo567Test {

	private List<String> data;

	@BeforeEach
	public void setUp() {
		data = new ArrayList<>(100);
		for (int i = 0; i < 100; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < 10; j++) {
				sb.append(UUID.randomUUID().toString().replace("-", "").replaceAll("\\d", "").toLowerCase());
			}
			data.add(sb.toString());
		}
	}

	@Test
	public void showData() {
		for (String item : data) {
			System.out.println("item = " + item);
			System.out.println("temp(item) = " + temp(item));
		}
	}

	private String temp(String s) {
		int a = MockUtil.random(s.length());
		int b = MockUtil.random(s.length());
		while (a == b) {
			b = MockUtil.random(s.length());
		}
		return s.substring(Math.min(a, b), Math.max(a, b) + 1);
	}

	public boolean checkInclusion(String s1, String s2) {
		int n = s1.length(), m = s2.length();
		if (n > m) {
			return false;
		}
		int[] cnt = new int[26];
		for (int i = 0; i < n; ++i) {
			--cnt[s1.charAt(i) - 'a'];
		}
		int left = 0;
		for (int right = 0; right < m; ++right) {
			int x = s2.charAt(right) - 'a';
			++cnt[x];
			while (cnt[x] > 0) {
				--cnt[s2.charAt(left) - 'a'];
				++left;
			}
			if (right - left + 1 == n) {
				return true;
			}
		}
		return false;
	}

	// 作者：力扣官方题解
	// 链接：https://leetcode.cn/problems/permutation-in-string/solutions/599202/zi-fu-chuan-de-pai-lie-by-leetcode-solut-7k7u/
	// 来源：力扣（LeetCode）
	// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
	@Test
	public void leetcodeTest() {
		for (String item : data) {
			System.out.println("checkInclusion(temp(item), item) = " + checkInclusion(temp(item), item));
		}
	}
}
