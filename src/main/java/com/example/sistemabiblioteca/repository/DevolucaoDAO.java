package com.example.sistemabiblioteca.repository;

import com.example.sistemabiblioteca.model.Devolucao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevolucaoDAO extends JpaRepository<Devolucao, Long> {
    // Spring cria automaticamente o salvar, deletar e listar.
}
