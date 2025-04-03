package com.l.dualpointers.linked;


public class Demo160 {
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		ListNode p1 = headA;
		ListNode p2 = headB;
		while (p1 != p2) {
			if (p1 != null) {
				p1 = p1.next;
			} else {
				p1 = headB;
			}
			if (p2 != null) {
				p2 = p2.next;
			} else {
				p2 = headA;
			}
		}
		return p1;
	}

	public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
		ListNode p1 = headA;
		ListNode p2 = headB;
		while (p1 != p2) {
			p1 = p1 != null ? p1.next : headB;
			p2 = p2 != null ? p2.next : headA;
		}
		return p1;
	}

	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}
}
