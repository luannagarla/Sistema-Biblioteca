package com.example.sistemabiblioteca.repository;

import com.example.sistemabiblioteca.model.Livro;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class LivroDAOImpl extends GenericDAOImpl<Livro> implements LivroDAO {

    public LivroDAOImpl() {
        super(Livro.class);
    }

    @Override
    public List<Livro> findAll() {
        // Consulta em banco utilizando JPQL para trazer todos os registros da entidade
        return entityManager.createQuery("SELECT l FROM Livro l", Livro.class).getResultList();
    }

    @Override
    public List<Livro> buscarPorCodigoTitulo(int codigoTitulo) {
        String jpql = "SELECT l FROM Livro l WHERE l.titulo.prazo = :codigoTitulo";
        return entityManager.createQuery(jpql, Livro.class)
                .setParameter("codigoTitulo", codigoTitulo)
                .getResultList();
    }
}