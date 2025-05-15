package com.l.dualpointers.array;


import cn.hutool.core.util.RandomUtil;
import com.l.util.MockUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <a href='https://leetcode.cn/problems/he-wei-sde-liang-ge-shu-zi-lcof/'>[LCR179]-查找总价格为目标值的两个商品</a>
 *
 * @author Administrator
 * @since 2025/4/24 14:55
 */
public class Lcr179 {
	private List<int[]> list;

	@Before
	public void setUp() throws Exception {
		list = new ArrayList<>(100);
		for (int i = 0; i < 100; i++) {
			int[] temp = MockUtil.randomArr(10, 100);
			Arrays.sort(temp);
			list.add(temp);
		}
	}

	@Test
	public void lcr179() {
		for (int[] item : list) {
			System.err.println("temp.strea = " + Arrays.stream(item).mapToObj(String::valueOf).collect(Collectors.joining(",")));
			System.err.println(Arrays.stream(twoSum(item, RandomUtil.randomInt(100))).mapToObj(String::valueOf).collect(Collectors.joining(",")));
		}
	}

	public int[] twoSum(int[] price, int target) {
		int fast = price.length - 1;
		int slow = 0;
		while (fast >= 0 && slow < fast) {
			int sum = price[fast] + price[slow];
			if (sum == target) {
				return new int[]{price[slow], price[fast]};
			}
			if (sum < target) {
				slow++;
			}
			if (sum > target) {
				fast--;
			}
		}
		return new int[]{};
	}
}
