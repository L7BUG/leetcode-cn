package com.l.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HeapTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Heap heap = new Heap(new int[]{1, 4, 5, 9, 12, 3});
		System.err.println(heap);
	}
}