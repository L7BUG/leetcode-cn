package com.l.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicLong;

class TestUtilsTest {

	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void consumesTime() {
		final AtomicLong temp = new AtomicLong(0L);
		TestUtils.consumesTime(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

			}
			return 100L;
		}, time -> {
			System.out.println(time);
			temp.set(time);
		});
		System.out.println(temp.get());
	}
}