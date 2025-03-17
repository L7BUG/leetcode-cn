package com.l.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 最小堆
 *
 * @author luliangyu
 */
public class MinHeap {
	List<Integer> list;

	public MinHeap(List<Integer> list) {
		this.list = list;
	}

	public MinHeap(int[] arr) {
		list = new ArrayList<>();
		list.add(null);
		for (int i : arr)
			list.add(i);
		// 从叶节点-1的那一层开始向下调整
		for (int i = (list.size() - 1) / 2; i >= 1; i--) {
			siftdown(i);
		}
	}

	private void swap(int a, int b) {
		Integer aV = list.get(a);
		Integer bV = list.get(b);
		list.set(a, bV);
		list.set(b, aV);
	}

	/**
	 * 向下调整
	 *
	 * @param i 传入要调整的节点
	 */
	private void siftdown(int i) {
		boolean flag = true;
		int t;
		while (i * 2 < list.size() && flag) {
			// 先判断左儿子
			if (list.get(i) > list.get(i * 2)) {
				t = i * 2;
			} else {
				t = i;
			}
			// 先判断是否右右儿子
			if (i * 2 + 1 < list.size()) {
				if (list.get(t) > list.get(i * 2 + 1)) {
					t = i * 2 + 1;
				}
			}
			if (t == i) {
				flag = false;
			} else {
				swap(i, t);
				i = t;
			}
		}
	}

	/**
	 * 删除顶部元素
	 *
	 * @return 堆顶的值
	 */
	public int deleteTop() {
		int t = list.get(1);
		swap(1, list.size() - 1);
		list.remove(list.size() - 1);
		siftdown(1);
		return t;
	}

	/**
	 * 降序
	 *
	 * @return arr
	 */
	public List<Integer> sort(boolean isUp) {
		List<Integer> arr = new ArrayList<>();
		MinHeap temp = new MinHeap((List<Integer>) ((ArrayList) list).clone());
		while (temp.list.size() > 1) {
			if (isUp)
				arr.add(temp.deleteTop());
			else
				arr.add(0, temp.deleteTop());
		}
		return arr;
	}
}
