package com.example.sistemabiblioteca.repository;

import com.example.sistemabiblioteca.model.Debito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DebitoDAO extends JpaRepository<Debito, Long> {
    List<Debito> findByAlunoMatricula(int matricula);
}