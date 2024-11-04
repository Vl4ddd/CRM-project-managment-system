package com.crmpoject.crm.DAO.UserDAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.crmpoject.crm.entities.User.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Predicate;

@Repository
public class UserRepositoryDAO implements UserRepositoryCustom{

    private final EntityManager entityManager;

    @Autowired
    public UserRepositoryDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> findByLoginOrEmail(String login, String email) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> userRoot = criteriaQuery.from(User.class);

        Predicate loginPredicate = criteriaBuilder.equal(userRoot.get("login"), login);
        Predicate emailPredicate = criteriaBuilder.equal(userRoot.get("email"), email);

        Predicate finalPredicate = criteriaBuilder.or(loginPredicate, emailPredicate);

        criteriaQuery.select(userRoot).where(finalPredicate);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}
