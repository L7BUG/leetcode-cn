package com.l.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class HeapTest {

	@AfterEach
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Heap heap = new Heap(new int[]{1, 4, 5, 9, 12, 3});
		System.err.println(heap);
	}
}