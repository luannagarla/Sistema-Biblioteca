package com.example.sistemabiblioteca.repository;

import com.example.sistemabiblioteca.model.Aluno;
import org.springframework.stereotype.Repository;

@Repository
public class AlunoDAO extends GenericDAOImpl<Aluno> {
    public AlunoDAO() {
        super(Aluno.class);
    }
}