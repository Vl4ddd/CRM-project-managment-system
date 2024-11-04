package com.crmpoject.crm.Repository;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.crmpoject.crm.entities.User.User;
import com.crmpoject.crm.repository.UserRepository;

@SpringBootTest
public class UserRepositoryTest {

    private final UserRepository userRepository;

    @Autowired
    UserRepositoryTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    void testFindUserByName() {
        // генерация имени пользователя
        String userName = UUID.randomUUID().toString();
        User user = new User();
        user.setName(userName);
        userRepository.save(user);
        // поиск пользователя по имени
        User foundUser = userRepository.findByName(userName).getFirst();
        // проверки
        Assertions.assertNotNull(foundUser);
        Assertions.assertEquals(user.getId(), foundUser.getId());
        Assertions.assertEquals(userName, foundUser.getName());
    }
}
