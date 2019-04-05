package com.ps.services;

import com.ps.config.AppConfig;
import com.ps.config.TestDataConfig;
import com.ps.init.DBInitializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Transactional
@ActiveProfiles("dev")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDataConfig.class, AppConfig.class})
public class UserServiceTest {

    @Autowired
    DBInitializer initializer;

    @Autowired
    UserService userService;

    @BeforeTransaction
    public void setUp() {
        assertNotNull(userService);
        initializer.initDb();
    }

    @Test
    public void checkDbInit() {
        long count = userService.countUsers();
        assertEquals(5, count);
    }
}
