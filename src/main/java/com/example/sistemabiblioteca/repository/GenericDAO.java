package com.example.sistemabiblioteca.repository;

import java.util.List;

public interface GenericDAO<T> {
    void save(T entity);
    T find(Object id);
    void update(T entity);
    void delete(Object id);
}