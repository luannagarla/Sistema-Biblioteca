package com.example.sistemabiblioteca.repository;

import com.example.sistemabiblioteca.model.Aluno;
import java.util.List;

public interface AlunoDAO extends GenericDAO<Aluno> {
    List<Aluno> findAll();
}