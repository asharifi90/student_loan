package org.alireza.base.service;

import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.alireza.base.entity.BaseEntity;
import org.alireza.base.exception.NotFoundException;
import org.alireza.base.repository.BaseRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.net.IDN;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class BaseServiceImpl<T extends BaseEntity<ID>,
        ID extends Serializable,
        R extends BaseRepository<T, ID>>
        implements BaseService<T, ID> {

    protected final R repository;
    protected final SessionFactory sessionFactory;

    @Override
    public T saveOrUpdate(T entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            T t = repository.saveOrUpdate(entity);
            transaction.commit();
            return t;
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            return null;
        }
    }

    @Override
    public T findById(ID id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            T foundEntity = repository.findById(id).orElseThrow(
                    () -> new NotFoundException(String.format("Entity with %s not found", id)));
            session.getTransaction().commit();
            return foundEntity;
        } catch (HibernateException e) {
            return null;
        }
    }

    @Override
    public void deleteById(ID id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Optional<T> foundEntity = repository.findById(id);
            foundEntity.orElseThrow(() ->
                    new NotFoundException(String.format("Entity with id : %s not found", id)));
            repository.delete(foundEntity.get());
            session.getTransaction().commit();
        } catch (Exception ignored) {
        }
    }

    @Override
    public List<T> findAll() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<T> listAll = repository.findAll();
            session.getTransaction().commit();
            session.close();
            return listAll;
        } catch (Exception ignored) {
            return null;
        }
    }
}
