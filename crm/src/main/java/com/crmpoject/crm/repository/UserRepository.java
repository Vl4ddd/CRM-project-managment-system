package com.crmpoject.crm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.crmpoject.crm.entities.User.User;

@RepositoryRestResource(path = "user")
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Находит всех пользователей с заданным именем
     * 
     * @param login имя пользователя
     * @param email почта
     */
    List<User> findByLoginOrEmail(String login, String email);

    List<User> findByName(String name);

    List<User> findByLogin(String login);

    boolean existsByLogin(String login);

}
