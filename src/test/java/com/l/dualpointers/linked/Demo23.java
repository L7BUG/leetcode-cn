package com.l.dualpointers.linked;

import com.l.model.ListNode;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * <a href='https://leetcode.cn/problems/merge-k-sorted-lists/description/'>[23]-合并 K 个升序链表</a>
 *
 * @author Administrator
 * @since 2025/4/2 10:12
 */
public class Demo23 {
	private static final int SIZE = 3;
	private final ListNode[] data = new ListNode[SIZE];

	@Before
	public void setUp() throws Exception {
		Random random = new Random();
		for (int i = 0; i < SIZE; i++) {
			int[] temp = new int[10];
			for (int j = 0; j < 10; j++) {
				temp[j] = random.nextInt(100);
			}
			Arrays.sort(temp);
			ListNode e = ListNode.buildByArr(temp);
			data[i] = e;
		}
	}

	public ListNode mergeKLists(ListNode[] lists) {
		ListNode head = new ListNode();
		ListNode temp;
		ListNode p = head;
		while ((temp = minNode(lists)) != null) {
			p.next = temp;
			p = p.next;
		}
		return head.next;
	}

	@Test
	public void mergeKListsByHeapTest() {
		ListNode node = mergeKListsByHeap(data);
		node.show();
	}

	public ListNode mergeKListsByHeap(ListNode[] lists) {
		if (lists.length == 0) {
			return null;
		}
		ListNode head = new ListNode();
		ListNode p = head;
		Queue<ListNode> queue = new PriorityQueue<>((Comparator.comparingInt(o -> o.val)));
		for (ListNode item : lists) {
			if (item != null) {
				queue.offer(item);
			}
		}
		while (!queue.isEmpty()) {
			ListNode poll = queue.poll();
			p.next = poll;
			p = p.next;
			if (poll.next != null) {
				queue.offer(poll.next);
			}
		}
		return head.next;
	}

	@Test
	public void mergeKListsTest() {
		ListNode listNode = mergeKLists(data);
		listNode.show();
	}

	public ListNode minNode(ListNode[] lists) {
		int min = Integer.MAX_VALUE;
		int index = -1;
		for (int i = 0; i < lists.length; i++) {
			if (lists[i] != null && min > lists[i].val) {
				min = lists[i].val;
				index = i;
			}
		}
		if (index != -1) {
			ListNode result = lists[index];
			lists[index] = lists[index].next;
			return result;
		}
		return null;
	}

	@Test
	public void minTest() {
		ListNode temp;
		while ((temp = minNode(data)) != null) {
			System.out.println(temp.val);
		}
	}


}
