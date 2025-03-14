package com.l;

import com.l.entity.ListNode;
import org.junit.Before;
import org.junit.Test;

/**
 * <a href='https://leetcode.cn/problems/partition-list/'>86. 分隔链表</a>
 *
 * @author luliangyu
 * @since 2023-04-25 10:57
 */
public class Demo86 {
	private ListNode listNode;

	private static ListNode arrayToListNode(int[] array) {
		ListNode head = new ListNode();
		ListNode p = head;
		for (int i : array) {
			p.next = new ListNode();
			p = p.next;
			p.val = i;
		}
		return head.next;
	}

	public static void printListNode(ListNode listNode) {
		while (listNode != null) {
			System.out.print(listNode.val + " ");
			listNode = listNode.next;
		}
		System.out.println();
		System.out.println("---");
	}

	@Before
	public void before() {
		listNode = arrayToListNode(new int[]{1, 4, 3, 2, 5, 2});
	}

	@Test
	public void test() {
		int x = 3;
		ListNode partition = partition(listNode, x);
		printListNode(partition);
	}

	public ListNode partition(ListNode head, int x) {
		// 思路是先分割成两个链表，最后拼接
		ListNode listNodeH = new ListNode();
		ListNode listNodeHp = listNodeH;
		ListNode listNodeF = new ListNode();
		ListNode listNodeFp = listNodeF;
		ListNode item = head;
		while (item != null) {
			if (item.val >= x) {
				// 比x大或等于，放入后面的链表
				listNodeFp.next = new ListNode(item.val);
				listNodeFp = listNodeFp.next;
			} else {
				// 比x小，放入前面的链表
				listNodeHp.next = new ListNode(item.val);
				listNodeHp = listNodeHp.next;
			}
			item = item.next;
		}
		// 拼接
		listNodeHp.next = listNodeF.next;
		return listNodeH.next;
	}

}
