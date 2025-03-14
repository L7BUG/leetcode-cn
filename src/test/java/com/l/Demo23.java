package com.l;

import com.l.entity.ListNode;
import com.l.util.ListNodeUtils;
import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <a href='https://leetcode.cn/problems/merge-k-sorted-lists/'>23.合并 k 个有序链表</a>
 *
 * @author luliangyu
 * @since 2023-04-25 11:55
 */
public class Demo23 {
	// [1,4,5],[1,3,4],[2,6]
	private final ListNode[] data = new ListNode[]{
		ListNodeUtils.arrayToListNode(new int[]{1, 4, 5}),
		ListNodeUtils.arrayToListNode(new int[]{1, 3, 4}),
		ListNodeUtils.arrayToListNode(new int[]{2, 6})
	};
	private final ListNode[] data2 = new ListNode[]{
		ListNodeUtils.arrayToListNode(new int[]{}),
	};

	@Test
	public void test() {
		ListNode listNode = mergeKLists(data);
		ListNodeUtils.printListNode(listNode);
	}

	@Test
	public void test2() {
		ListNode listNode = mergeKLists(data2);
		ListNodeUtils.printListNode(listNode);
	}

	public ListNode mergeKLists(ListNode[] lists) {
		if (lists.length == 0) {
			return null;
		}
		PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(lists.length, Comparator.comparingInt(o -> o.val));
		for (ListNode list : lists) {
			if (list == null) {
				continue;
			}
			priorityQueue.offer(list);
		}
		ListNode data = new ListNode();
		ListNode p = data;
		while (!priorityQueue.isEmpty()) {
			ListNode item = priorityQueue.poll();
			if (priorityQueue.isEmpty()) {
				p.next = item;
				break;
			}
			p.next = new ListNode(item.val);
			if (item.next != null) {
				priorityQueue.offer(item.next);
			}
			p = p.next;
		}
		return data.next;
	}
}
