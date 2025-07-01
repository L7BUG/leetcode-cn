package com.l;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

/**
 * [234]回文链表
 * https://leetcode.cn/problems/palindrome-linked-list/description/
 * <p>
 *
 * @author l
 * @since 2025/2/23 09:11
 */
public class Demo234 {
	ListNode test1, test2, test3, test4, test5;
	private boolean flag = true;

	private ListNode head;

	@Test
	public void test1() {
		System.err.println(isPalindrome(test1));
		System.err.println(isPalindrome(test2));
		System.err.println(isPalindrome(test3));
		System.err.println(isPalindrome(test4));
		System.err.println(isPalindrome(test5));
	}

	public boolean isPalindrome(ListNode head) {
		flag = true;
		this.head = head;
		execFlag(head);
		return flag;
	}

	private void execFlag(ListNode node) {
		if (node == null) {
			return;
		}
		execFlag(node.next);
		if (!flag) {
			return;
		}
		if (head.val != node.val) {
			flag = false;
			return;
		}
		head = head.next;
	}

	@Test
	public void printTest() {
		test1.print();
		test2.print();
		test3.print();
		test4.print();
		test5.print();
	}

	@AfterEach
	public void init() {
		test1 = new ListNode(1, 2, 1);
		test2 = new ListNode(1, 2, 2, 1);
		test3 = new ListNode(1);
		test4 = new ListNode(1, 2, 3);
		test5 = new ListNode(1, 2, 3, 4);
	}

	public static class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}

		ListNode(int... array) {
			ListNode listNode = buildByArray(array);
			this.val = listNode.val;
			this.next = listNode.next;
		}

		public void print() {
			System.out.print("[");
			print(this);
		}

		private void print(ListNode listNode) {
			if (listNode == null) {
				System.out.println("]");
				return;
			}
			System.out.print(listNode.val + " ");
			this.print(listNode.next);
		}


		private ListNode buildByArray(int[] array) {
			int index = 0;
			ListNode head = new ListNode(array[0]);
			index++;
			ListNode temp = head;
			for (; index < array.length; index++) {
				temp.next = new ListNode(array[index]);
				temp = temp.next;
			}
			return head;
		}
	}

	public static class Palindrome {
		private final boolean flag = true;
		private final boolean canExec = true;

		Palindrome(ListNode head) {

		}

		public boolean isFlag() {
			return flag;
		}
	}
}
