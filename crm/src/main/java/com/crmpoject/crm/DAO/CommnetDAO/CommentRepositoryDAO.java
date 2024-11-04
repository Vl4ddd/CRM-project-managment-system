package com.crmpoject.crm.DAO.CommnetDAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.crmpoject.crm.entities.Comment.Comment;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class CommentRepositoryDAO implements CommentRepositoryCustom{

    private final EntityManager entityManager;

    @Autowired
    public CommentRepositoryDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Comment> findByLogin(String login) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Comment> criteriaQuery = criteriaBuilder.createQuery(Comment.class);

        Root<Comment> commentRoot = criteriaQuery.from(Comment.class);

        Predicate loginPredicate = criteriaBuilder.equal(commentRoot.get("user").get("login"), login);

        criteriaQuery.select(commentRoot).where(loginPredicate);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }


}
