package com.l.dualpointers.linked;

import com.l.entity.ListNode;
import com.l.util.ListNodeUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.cn/problems/linked-list-cycle-ii/">142. 环形链表2</a>
 *
 * @auth or luliangyu
 * @since 2023-05-06 15:56
 */
public class Demo142 {
	private ListNode listNode1, listNode2, listNode3, listNode4;

	@Before
	public void before() {
		listNode1 = ListNodeUtils.arrayToListNode(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
		ListNodeUtils.getLastNode(listNode1).next = listNode1.next.next.next.next;
		listNode2 = ListNodeUtils.arrayToListNode(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
		listNode3 = ListNodeUtils.arrayToListNode(new int[]{1});
		listNode4 = ListNodeUtils.arrayToListNode(new int[]{3, 2, 0, -4});
		ListNodeUtils.getLastNode(listNode4).next = listNode4.next;
	}

	@Test
	public void test1() {
		System.err.println(detectCycle1(listNode1));
		System.err.println(detectCycle1(listNode2));
		System.err.println(detectCycle1(listNode3));
	}

	@Test
	public void test2() {
		System.err.println(detectCycle2(listNode1));
		System.err.println(detectCycle2(listNode2));
		System.err.println(detectCycle2(listNode3));
		System.err.println(detectCycle2(listNode4).val);
	}

	/**
	 * 用set集合标记出现的节点解题
	 */
	public ListNode detectCycle1(ListNode head) {
		Set<ListNode> mark = new HashSet<>(10000);
		ListNode p = head;
		while (p != null) {
			if (mark.contains(p)) {
				return p;
			}
			mark.add(p);
			p = p.next;
		}
		return null;
	}

	/**
	 * 快慢指针
	 * 思路：看注释
	 */
	public ListNode detectCycle2(ListNode head) {
		ListNode p1, p2;
		p1 = p2 = head;
		while (p2 != null && p2.next != null) {
			p1 = p1.next;
			p2 = p2.next.next;
			if (p1 == p2) {
				// 他们相遇，快指针一定是慢指针步数的两倍
				break;
			}
		}
		if (p2 == null || p2.next == null) {
			// 快指针到了头，直接退出
			return null;
		}
		p1 = head;
		while (p1 != p2) {
			p1 = p1.next;
			p2 = p2.next;
		}
		// 再次相遇，节点在环首
		return p1.next;
	}
}
