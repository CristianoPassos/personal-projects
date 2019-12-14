package com.ps.repo;

import com.ps.base.UserType;
import com.ps.config.AppConfig;
import com.ps.config.TestDataConfig;
import com.ps.ents.User;
import com.ps.repos.UserRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.junit.Assert.*;

@ActiveProfiles("dev")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDataConfig.class, AppConfig.class})
public class TransactionalJdbcRepoTest {

    @Autowired
    @Qualifier("userTemplateRepo")
    UserRepo userRepo;

    @Before
    public void setUp() {
        assertNotNull(userRepo);
    }

    @Test
    @Transactional
    public void testFindById() {
        User user = userRepo.findById(1L);
        assertEquals("John", user.getUsername());
    }

    @Test
    @Transactional
    public void updatePassword() {
        int res = userRepo.updatePassword(1L, "newPass");
        assertEquals(1, res);
    }

    @Test
    @Rollback
    @Transactional
    public void testCreate() {
        int result = userRepo.createUser(9L, "Diana", "mypass", "diana@opympus.com", UserType.BOTH);
        assertEquals(1, result);

        final Set<User> dianas = userRepo.findAllByUserName("Diana", true);
        assertTrue(dianas.size() == 1);
    }
}
