package com.example.sistemabiblioteca.repository;

import com.example.sistemabiblioteca.model.Emprestimo;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class EmprestimoDAOImpl extends GenericDAOImpl<Emprestimo> implements EmprestimoDAO {

    public EmprestimoDAOImpl() {
        super(Emprestimo.class);
    }

    @Override
    public List<Emprestimo> findAll() {
        return entityManager.createQuery("SELECT e FROM Emprestimo e", Emprestimo.class).getResultList();
    }
}