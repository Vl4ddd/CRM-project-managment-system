package com.crmpoject.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.crmpoject.crm.entities.User.User;

public interface UserRepository extends CrudRepository<User, Long> {

/**
    * Находит всех пользователей с заданным именем
    * @param login имя пользователя
    * @param email почта
*/
List<User> findByLoginOrEmail(String login, String email);


}
