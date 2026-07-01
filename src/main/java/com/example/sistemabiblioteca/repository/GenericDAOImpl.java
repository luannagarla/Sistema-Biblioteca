package com.example.sistemabiblioteca.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

public abstract class GenericDAOImpl<T> implements GenericDAO<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    private final Class<T> clazz;

    protected GenericDAOImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    @Transactional
    public void save(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public T find(Object id) {
        return entityManager.find(clazz, id);
    }

    @Override
    @Transactional
    public void update(T entity) {
        entityManager.merge(entity);
    }

    @Override
    @Transactional
    public void delete(Object id) {
        T entity = find(id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }
}