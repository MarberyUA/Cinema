package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.util.HibernateUtil;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {
    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hqlQuery = "FROM MovieSession ms WHERE ms.movie.id = :id "
                    + "AND ms.showTime > :date";
            Query<MovieSession> query = session.createQuery(hqlQuery, MovieSession.class);
            query.setParameter("id", movieId);
            query.setParameter("date", date.atStartOfDay());
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Error while finding available sessions", e);
        }
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(movieSession);
            transaction.commit();
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Error while creating MovieSession object", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
