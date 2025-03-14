package com.l.util;

import com.l.entity.ListNode;

/**
 * ListNodeUtils
 *
 * @author luliangyu
 * @since 2023-04-25 11:13
 */
public class ListNodeUtils {


	public static ListNode arrayToListNode(int[] array) {
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

	public static ListNode getLastNode(ListNode head) {
		ListNode i = head;
		while (i.next != null) {
			i = i.next;
		}
		return i;
	}
}
