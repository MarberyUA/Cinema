package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.RoleDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.model.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Role add(Role role) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
            return role;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Error while add role to db!", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Role getRoleByName(String roleName) {
        Role.RoleName enumRoleName;
        try {
            enumRoleName = Role.RoleName.valueOf(roleName);
        } catch (IllegalArgumentException e) {
            throw new DataProcessingException("There is not such role", e);
        }

        try (Session session = sessionFactory.openSession()) {
            String hqlQuery = "FROM roles rl WHERE rl.roleName = :role";
            Query<Role> query = session.createQuery(hqlQuery, Role.class);
            query.setParameter("role", enumRoleName);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Error while getting role from db", e);
        }
    }
}
