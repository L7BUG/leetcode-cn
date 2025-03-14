package com.l.util;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * 堆
 *
 * @author luliangyu
 * @since 2023-04-25 14:42
 */
public class Heap {
	private final ArrayList<Integer> data;

	private final Comparator<Integer> comparator;

	public Heap(int[] array) {
		this(array, Comparator.comparingInt(o -> o));
	}

	public Heap(int[] array, Comparator<Integer> comparator) {
		this.data = new ArrayList<>(array.length + 1);
		for (int i : array) {
			this.data.add(0, i);
		}
		this.comparator = comparator;
		initTree();
		System.err.println();
	}

	private void initTree() {
		for (int i = (data.size() - 1) / 2; i >= 0; i--) {
			down(i);
		}
	}

	/**
	 * 向下调整
	 *
	 * @param index index
	 */
	private void down(int index) {
		int swapIndex;
		while (index * 2 < data.size()) {
			swapIndex = index;
			if (comparator.compare(data.get(swapIndex), data.get(index * 2)) > 0) {
				swapIndex = index * 2;
			}
			if (index * 2 + 1 < data.size()) {
				if (comparator.compare(data.get(swapIndex), data.get(index * 2 + 1)) > 0) {
					swapIndex = index * 2 + 1;
				}
			}
			if (swapIndex == index) {
				break;
			}
			swap(index, swapIndex);
			index = swapIndex;
		}
	}

	private void swap(int index1, int index2) {
		Integer v1 = data.get(index1);
		Integer v2 = data.get(index2);
		data.set(index1, v2);
		data.set(index2, v1);
	}
}
