package com.example.sistemabiblioteca.repository;

import com.example.sistemabiblioteca.model.Aluno;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AlunoDAOImpl extends GenericDAOImpl<Aluno> implements AlunoDAO {

    public AlunoDAOImpl() {
        super(Aluno.class);
    }

    @Override
    public List<Aluno> findAll() {
        return entityManager.createQuery("SELECT a FROM Aluno a", Aluno.class).getResultList();
    }
}