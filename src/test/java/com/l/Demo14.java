package com.l;

import org.junit.Test;

/**
 * <a href='https://leetcode-cn.com/problems/longest-common-prefix/'>14. 最长公共前缀</a>
 * <p>编写一个函数来查找字符串数组中的最长公共前缀。</p>
 * <p>如果不存在公共前缀，返回空字符串 ""。</p>
 *
 * @author luliangyu
 * @date 2021-09-16 17:18
 */
public class Demo14 {
	public String longestCommonPrefix(String[] strs) {
		if (strs.length <= 0) return "";
		StringBuilder sb = new StringBuilder();
		int index = 0;
		while (true) {
			if (strs[0].length() <= index) return sb.toString();
			char c = strs[0].charAt(index);
			for (String str : strs) {
				if (str.length() > index) {
					char temp = str.charAt(index);
					if (temp != c) {
						return sb.toString();
					}
				} else {
					return sb.toString();
				}
			}
			sb.append(c);
			index++;
		}
	}

	public String longestCommonPrefixTJ(String[] strs) {
		if (strs.length == 0)
			return "";
		// 假设第一个是最长公共前缀
		String ans = strs[0];
		for (int i = 1; i < strs.length; i++) {
			int j = 0;
			// 依次匹配
			for (; j < ans.length() && j < strs[i].length(); j++) {
				if (ans.charAt(j) != strs[i].charAt(j))
					break;
			}
			//将匹配的长度进行截取
			ans = ans.substring(0, j);
			if (ans.equals(""))
				return ans;
		}
		return ans;
	}
/*
    作者：guanpengchn
    链接：https://leetcode-cn.com/problems/longest-common-prefix/solution/hua-jie-suan-fa-14-zui-chang-gong-gong-qian-zhui-b/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
*/

	@Test
	public void main() {
		String s = longestCommonPrefix(new String[]{"flower", "flow", "flight"});
		System.out.println("s = " + s);
	}
}
