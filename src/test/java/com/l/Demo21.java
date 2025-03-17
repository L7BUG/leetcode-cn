package com.l;

import com.l.entity.ListNode;
import org.junit.Test;

/**
 * 合并两个有序链表
 * <a href='https://leetcode.cn/problems/merge-two-sorted-lists/'>21. 合并两个有序链表</a>
 *
 * @author luliangyu
 * @date 2022-12-08 14:35
 */
public class Demo21 {

	@Test
	public void test() {
		ListNode list1 = new ListNode(1, new ListNode(3, new ListNode(8, new ListNode(9))));
		ListNode list2 = new ListNode(1, new ListNode(4, new ListNode(5)));


		long millis = System.currentTimeMillis();
		System.err.println(mergeTwoLists1(list1, list2));
		System.err.println("耗时" + (System.currentTimeMillis() - millis));


		millis = System.currentTimeMillis();
		System.err.println(mergeTwoLists2(list1, list2));
		System.err.println("耗时" + (System.currentTimeMillis() - millis));
	}

	public ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
		if (list1 == null || list2 == null) {
			if (list1 != null) {
				return list1;
			} else {
				return list2;
			}
		}
		if (list1.val < list2.val) {
			return new ListNode(list1.val, mergeTwoLists1(list1.next, list2));
		} else {
			return new ListNode(list2.val, mergeTwoLists1(list1, list2.next));
		}
	}

	public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
		ListNode head = new ListNode(-1);
		ListNode p = head;
		for (; list1 != null || list2 != null; p = p.next) {
			if (list1 == null) {
				p.next = list2;
				break;
			}
			if (list2 == null) {
				p.next = list1;
				break;
			}
			p.next = new ListNode();
			if (list1.val < list2.val) {
				p.next.val = list1.val;
				list1 = list1.next;
			} else {
				p.next.val = list2.val;
				list2 = list2.next;
			}
		}
		return head.next;
	}

}
