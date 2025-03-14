package com.l;

import com.l.entity.ListNode;
import com.l.util.ListNodeUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * <a href='https://leetcode.cn/problems/remove-nth-node-from-end-of-list/'>19. 删除链表的倒数第 N 个结点</a>
 *
 * @author luliangyu
 * @since 2023-05-06 11:40
 */
public class Demo19 {
	private ListNode testNode1;
	private ListNode testNode2;

	@Before
	public void before() {
		testNode1 = ListNodeUtils.arrayToListNode(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
		testNode2 = ListNodeUtils.arrayToListNode(new int[]{1});
	}

	@Test
	public void test() {
		ListNode listNode = removeNthFromEnd(testNode1, 3);
		ListNodeUtils.printListNode(listNode);
		listNode = removeNthFromEnd(testNode2, 1);
		ListNodeUtils.printListNode(listNode);
	}

	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode node = new ListNode(-1, head);
		ListNode p1, p2;
		p2 = node;
		p2.next = head;
		for (int i = 0; i < n; i++) {
			p2 = p2.next;
		}
		p1 = node;
		while (p2.next != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		p1.next = p1.next.next;
		return node.next;
	}
}
