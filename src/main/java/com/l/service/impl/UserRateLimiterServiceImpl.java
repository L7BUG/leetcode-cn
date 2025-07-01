package com.l.service.impl;

import com.l.entity.User;
import com.l.service.UserRateLimiterService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * UserRateLimiterServiceImpl
 *
 * @author Administrator
 * @since 2025/7/1 11:35
 */
public class UserRateLimiterServiceImpl implements UserRateLimiterService {
	private final static int MAX_COUNT = 50;
	private final Queue<User> users = new ConcurrentLinkedQueue<>();

	public UserRateLimiterServiceImpl() {
		init();
	}

	private void init() {
		List<User> temp = new ArrayList<>(10);
		for (int i = 0; i < 200; i++) {
			User e = new User();
			e.setCount((int) (System.currentTimeMillis() % MAX_COUNT));
			e.setUsername("username{%d}".formatted(i));
			if (e.getCount() < MAX_COUNT) {
				temp.add(e);
			}
		}
		temp.sort(Comparator.comparingInt(User::getCount));
		users.addAll(temp);
	}

	@Override
	public User getUser() {
		User poll = users.poll();
		new Thread(() -> {
			if (poll == null) {
				return;
			}
			poll.setCount(poll.getCount() + 1);
			if (poll.getCount() < MAX_COUNT) {
				users.offer(poll);
			}
		}).start();
		return poll;
	}

}
