package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.UserDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public User create(User user) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Error while creating user", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public User get(Long userId) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String hqlQuery = "FROM User us join fetch us.roles WHERE us.id = :id";
            Query<User> query = session.createQuery(hqlQuery, User.class);
            query.setParameter("id", userId);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Error while getting user from db", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public User findByEmail(String userEmail) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String hqlQuery = "FROM User us join fetch us.roles WHERE us.email = :email";
            Query<User> query = session.createQuery(hqlQuery, User.class);
            query.setParameter("email", userEmail);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Error while getting user from db", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
