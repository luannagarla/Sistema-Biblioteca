package com.example.sistemabiblioteca.repository;

import com.example.sistemabiblioteca.model.Emprestimo;
import java.util.List;

public interface EmprestimoDAO extends GenericDAO<Emprestimo> {
    List<Emprestimo> findAll();

    Emprestimo encontrarPorLivroEmprestado(int codigoLivro);
}