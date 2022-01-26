package org.example.jpa.repositories;

import lombok.Builder;
import lombok.Getter;
import org.example.jpa.entities.FilmEntity;
import org.example.jpa.entities.KinoEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public abstract class DataBaseRepository<T> implements IDataRepository<T> {
  protected EntityManager entityManager;
  protected SessionFactory sessionFactory;
  @Getter
  private Class<T> tClass;


  protected DataBaseRepository(Class<T> tClass, SessionFactory sessionFactory) {
    this.tClass = tClass;
    this.sessionFactory = sessionFactory;
    this.entityManager = sessionFactory.createEntityManager();
  }

  @Override
  public T getById(Long id) {
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();
    T entity = entityManager.find(tClass, id);
    transaction.commit();
    session.close();
    return entity;
  }

  @Override
  public T getByLogin(String login) {
    String hql = "FROM " + tClass.getName() + " E WHERE E.login LIKE '" + login + "'";
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();
    Query query = session.createQuery(hql, tClass);
    T ret = (T) query.getResultList()
        .get(0);
    transaction.commit();
    session.close();
    return ret;
  }


  public T getByName(String name) {
    try {
      String hql = "FROM " + tClass.getName() + " E WHERE E.name LIKE '" + name + "'";
      Session session = sessionFactory.openSession();
      Transaction transaction = session.beginTransaction();
      Query query = session.createQuery(hql, tClass);
      T ret = (T) query.getResultList()
          .get(0);
      transaction.commit();
      session.close();
      return ret;
    } catch (Exception ex) {
      return null;
    }

  }

  @Override
  public Long save(T entity) {
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();
    session.save(entity);

    transaction.commit();
    session.close();
    return null;
  }

  @Override
  public void update(T entity) {
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();
    session.update(entity);
    transaction.commit();
    session.close();
  }

  @Override
  public void delete(T entity) {
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();
    session.delete(entity);
    transaction.commit();
    session.close();
  }

  @Override
  public List<T> getAll() {
    final String maxIdQuery = "FROM " + tClass.getName() + " e";
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();
    Query query = session.createQuery(maxIdQuery, tClass);
    List<T> ret = (List<T>) query.getResultList();
    transaction.commit();
    session.close();
    return ret;
  }

  @Override
  @Deprecated
  public Long getNewId() {
    try {
      final String maxIdQuery = "SELECT max(e.id) as max from " + tClass.getName() + " e";
      Session session = sessionFactory.openSession();
      Transaction transaction = session.beginTransaction();
      Query query = session.createQuery(maxIdQuery, Long.class);
      Long ret = (Long) query.getResultList()
          .get(0) + 1;
      transaction.commit();
      session.close();
      return ret;
    } catch (Exception ex) {
      return 1L;
    }

  }

}
