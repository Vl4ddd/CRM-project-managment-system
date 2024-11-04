package com.crmpoject.DAO.CommnetDAO;

import java.util.List;

import com.crmpoject.crm.entities.Comment.Comment;

public interface CommentRepositoryCustom {

    List<Comment> findByLogin(String login);

}
