package com.l;

import com.l.entity.ListNode;
import com.l.util.ListNodeUtils;
import org.junit.Before;
import org.junit.Test;

/**
 * <a href="https://leetcode.cn/problems/linked-list-cycle/">141. 环形链表</a>
 *
 * @author luliangyu
 * @date 2022-12-08 14:02
 */
public class Demo141 {
	private ListNode listNode1, listNode2, listNode3;

	@Before
	public void before() {
		listNode1 = ListNodeUtils.arrayToListNode(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
		ListNodeUtils.getLastNode(listNode1).next = listNode1.next.next.next.next;
		listNode2 = ListNodeUtils.arrayToListNode(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
		listNode3 = ListNodeUtils.arrayToListNode(new int[]{1});
	}

	@Test
	public void test() {
		System.err.println(hasCycle(listNode1));
		System.err.println(hasCycle(listNode2));
		System.err.println(hasCycle(listNode3));
	}

	public boolean hasCycle(ListNode head) {
		ListNode p1, p2;
		p1 = p2 = head;
		while (p2 != null && p2.next != null) {
			p1 = p1.next;
			p2 = p2.next.next;
			if (p1 == p2) {
				return true;
			}
		}
		return false;
	}
}
