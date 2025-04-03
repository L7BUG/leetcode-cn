package com.l.model;

/**
 * leetcode-链表
 *
 * @author Administrator
 * @since 2025/4/3 11:06
 */
public class ListNode {
	public int val;
	public ListNode next;

	public ListNode() {
	}

	public ListNode(int val) {
		this.val = val;
	}

	public ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}

	public static ListNode buildByArr(int[] arr) {
		if (arr.length < 1) {
			return null;
		}
		ListNode head = new ListNode();
		ListNode temp = head;
		for (int i : arr) {
			temp.next = new ListNode(i);
			temp = temp.next;
			temp.val = i;
		}
		return head.next;
	}

	public void show() {
		ListNode p = this;
		while (p != null) {
			System.out.print(p.val + " ");
			p = p.next;
		}
	}
}