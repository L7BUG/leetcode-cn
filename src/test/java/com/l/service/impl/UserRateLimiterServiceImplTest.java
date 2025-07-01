package com.l.service.impl;

import com.l.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserRateLimiterServiceImplTest {
    private final UserRateLimiterServiceImpl userRateLimiterServiceImpl = new UserRateLimiterServiceImpl();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getUser() {
        User user;
        while ((user = userRateLimiterServiceImpl.getUser()) != null) {
            System.out.println("user = " + user);
        }
    }
}