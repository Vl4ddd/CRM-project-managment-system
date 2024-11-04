package com.crmpoject.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.crmpoject.crm.entities.Comment.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long>{

/**
    * Находит все комментарии пользователя с заданным логином
    * @param login логин пользователя
*/
@Query("FROM Comment c WHERE c.user.login = :login")
List<Comment> findByLogin(String login);


}
