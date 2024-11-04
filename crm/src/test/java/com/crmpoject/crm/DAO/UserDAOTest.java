package com.crmpoject.crm.DAO;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.crmpoject.crm.DAO.UserDAO.UserRepositoryDAO;
import com.crmpoject.crm.entities.User.User;
import com.crmpoject.crm.repository.UserRepository;

@SpringBootTest
public class UserDAOTest {

    private final UserRepository userRepository;
    private final UserRepositoryDAO userRepositoryDAO;

    @Autowired
    UserDAOTest(UserRepository userRepository, UserRepositoryDAO userRepositoryDAO) {
        this.userRepository = userRepository;
        this.userRepositoryDAO = userRepositoryDAO;
    }

    @Test
    void testfindByLoginOrEmail() {
        // генерация имени пользователя
        String userLogin = UUID.randomUUID().toString();
        String userEmail = UUID.randomUUID().toString();
        User user = new User();
        user.setEmail(userEmail);
        user.setLogin(userLogin);

        userRepository.save(user);
        // поиск пользователя по имени
        User foundUser = userRepositoryDAO.findByLoginOrEmail(userLogin, userEmail).getFirst();
        // проверки
        Assertions.assertNotNull(foundUser);
        Assertions.assertEquals(user.getId(), foundUser.getId());
    }




}
