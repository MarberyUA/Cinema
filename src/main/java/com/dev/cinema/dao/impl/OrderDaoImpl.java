package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.OrderDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Order addOrder(Order order) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Error while creating an order", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String hqlQuery = "FROM Order ord join fetch ord.tickets WHERE ord.user.id = :id";
            Query<Order> query = session.createQuery(hqlQuery, Order.class);
            query.setParameter("id", user.getId());
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error while getting all user orders", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
