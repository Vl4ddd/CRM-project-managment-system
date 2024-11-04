package com.crmpoject.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.crmpoject.crm.entities.Comment.Comment;

@RepositoryRestResource(path = "comment")
public interface CommentRepository extends CrudRepository<Comment, Long> {

    /**
     * Находит все комментарии пользователя с заданным логином
     * 
     * @param login логин пользователя
     */
    @Query("FROM Comment c WHERE c.user.login = :login")
    List<Comment> findByLogin(String login);

    void deleteByTaskId(Long id);



}
