package com.crmpoject.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crmpoject.crm.entities.User.Role;
import com.crmpoject.crm.entities.User.User;
import com.crmpoject.crm.exception.UserAlreadyExistsException;
import com.crmpoject.crm.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository; // Предполагается, что у вас есть репозиторий для доступа к данным
                                                 // пользователей
    private final PasswordEncoder passwordEncoder; // Для шифрования паролей

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User userDTO) {

        if (isUserExists(userDTO)) {
            // Если пользователь существует, выбрасываем исключение
            throw new UserAlreadyExistsException("Пользователь с таким логином уже существует");
        }

        // Создаем нового пользователя
        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Шифруем пароль
        user.setRole(Role.ROLE_USER); // Устанавливаем роль (можно сделать более гибким)

        // Сохраняем пользователя в базе данных
        return userRepository.save(user);

    }

    public boolean isUserExists(User userDTO) {

        return userRepository.existsByLogin(userDTO.getLogin());

    }
}