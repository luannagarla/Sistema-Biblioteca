package com.example.sistemabiblioteca.repository;

import com.example.sistemabiblioteca.model.Emprestimo;
import org.springframework.stereotype.Repository;
import jakarta.persistence.NoResultException; // <-- Importante adicionar isso
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

    @Override
    public Emprestimo encontrarPorLivroEmprestado(int codigoLivro) {
        try {
            return entityManager.createQuery(
                            "SELECT e FROM Emprestimo e JOIN e.i item WHERE item.livro.codigo = :codigoLivro AND item.livro.disponivel = false", Emprestimo.class)
                    .setParameter("codigoLivro", codigoLivro)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}