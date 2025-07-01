package com.l.sliding.window;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Demo03
 *
 * @author Administrator
 * @since 2025/7/1 15:32
 */
public class Demo03Test {
	private List<String> data;

	@AfterEach
	public void setUp() {
		data = new ArrayList<>(100);
		for (int i = 0; i < 100; i++) {
			data.add(UUID.randomUUID().toString().replace("-", ""));
		}
	}

	@AfterEach
	void tearDown() {

	}

	@Test
	public void showData() {
		data.forEach(System.err::println);
	}
}
