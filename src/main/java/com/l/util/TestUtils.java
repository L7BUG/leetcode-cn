package com.l.util;

import java.util.function.LongConsumer;
import java.util.function.Supplier;

/**
 * TestUtils
 *
 * @author Administrator
 * @since 2025/7/2 11:28
 */
public class TestUtils {
	public static <T> T consumesTime(Supplier<T> supplier, LongConsumer consumer) {
		long start = System.currentTimeMillis();
		T t = supplier.get();
		long end = System.currentTimeMillis();
		consumer.accept(end - start);
		System.out.println("end - start = " + (end - start));
		return t;
	}
}
