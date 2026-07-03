package com.example.sistemabiblioteca.repository;

import com.example.sistemabiblioteca.model.Livro;
import java.util.List;

public interface LivroDAO extends GenericDAO<Livro> {
    List<Livro> findAll();
    List<Livro> buscarPorCodigoTitulo(int codigoTitulo);
}