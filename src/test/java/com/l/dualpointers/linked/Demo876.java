package com.l.dualpointers.linked;

import com.l.entity.ListNode;
import com.l.util.ListNodeUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

/**
 * <a href="https://leetcode.cn/problems/middle-of-the-linked-list/">876. 链表的中间结点</a>
 *
 * @author luliangyu
 * @since 2023-05-06 14:45
 */
public class Demo876 {
	private ListNode listNode1, listNode2, listNode3, listNode4;

	@Before
	public void before() {
		listNode1 = ListNodeUtils.arrayToListNode(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
		listNode2 = ListNodeUtils.arrayToListNode(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
		listNode3 = ListNodeUtils.arrayToListNode(new int[]{1});
		listNode4 = ListNodeUtils.arrayToListNode(new int[]{});
	}

	@Test
	public void test() {
		ListNodeUtils.printListNode(middleNode(listNode1));
	}

	public ListNode middleNode(ListNode head) {
		ListNode node = new ListNode(0, head);
		ListNode p1, p2;
		p1 = p2 = node;
		while (p2 != null) {
			p1 = p1.next;
			p2 = Optional.ofNullable(p2.next).map(item -> item.next).orElse(null);
		}
		return p1;
	}
}
