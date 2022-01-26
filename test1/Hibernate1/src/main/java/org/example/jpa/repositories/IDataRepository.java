package org.example.jpa.repositories;

import java.util.List;

public interface IDataRepository<T> {

  public T getById(Long id) ;
  public T getByLogin(String login);

  public Long save(T entity);
  public void update(T entity);
  public void delete(T entity);
  public List<T> getAll();
  public Long getNewId();

}
