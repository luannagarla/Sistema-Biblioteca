package com.example.sistemabiblioteca.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class EmprestimoTest {
  
    @Test
    void deveDefinirAluno() {
        Emprestimo emprestimo = new Emprestimo();
        Aluno aluno = new Aluno(12345);
        emprestimo.setAluno(aluno);

        assertEquals(aluno, emprestimo.getAluno());
    }

    @Test
    void deveDefinirDataPrevista() {
        Emprestimo emprestimo = new Emprestimo();
        Date data = new Date();

        emprestimo.setDataPrevista(data);

        assertEquals(data, emprestimo.getDataPrevista());
    }

    @Test
    void deveCalcularDataDevolucaoComUmLivro() {
        Emprestimo emprestimo = new Emprestimo();
        Titulo titulo = new Titulo(7, "978-3-16-148410-0", 1, 2020);
        Livro livro = new Livro(100, titulo);
        ItemEmprestimo item = new ItemEmprestimo(livro, emprestimo);
        emprestimo.getI().add(item);

        Date dataPrevista = emprestimo.CalculaDataDevolucao();

        assertNotNull(dataPrevista);
        assertEquals(dataPrevista, item.getDataDevolucao());
    }

    @Test
    void deveCalcularDataDevolucaoComMultiplosLivros() {
        Emprestimo emprestimo = new Emprestimo();
        Titulo titulo1 = new Titulo(7, "978-3-16-148410-0", 1, 2020);
        Titulo titulo2 = new Titulo(14, "978-3-16-148410-1", 1, 2020);
        Livro livro1 = new Livro(100, titulo1);
        Livro livro2 = new Livro(200, titulo2);
        ItemEmprestimo item1 = new ItemEmprestimo(livro1, emprestimo);
        ItemEmprestimo item2 = new ItemEmprestimo(livro2, emprestimo);
        emprestimo.getI().add(item1);
        emprestimo.getI().add(item2);

        Date dataPrevista = emprestimo.CalculaDataDevolucao();
        assertNotNull(dataPrevista);
        assertEquals(dataPrevista, item1.getDataDevolucao());
        assertEquals(dataPrevista, item2.getDataDevolucao());
    }

}
