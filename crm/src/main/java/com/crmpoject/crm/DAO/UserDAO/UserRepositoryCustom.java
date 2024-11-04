package com.crmpoject.DAO.UserDAO;

import java.util.List;

import com.crmpoject.crm.entities.User.User;

public interface UserRepositoryCustom {

    List<User> findByLoginOrEmail(String login, String email);

}
