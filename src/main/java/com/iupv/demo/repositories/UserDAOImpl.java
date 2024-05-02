package com.iupv.demo.repositories;

import com.iupv.demo.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class UserDAOImpl implements UserDAO{
    private final EntityManager em;

    @Autowired
    public UserDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Transactional
    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public User findById(UUID id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> query = em.createQuery("FROM User", User.class);
        return query.getResultList();
    }


}
