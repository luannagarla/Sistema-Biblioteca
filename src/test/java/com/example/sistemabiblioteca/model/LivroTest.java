package com.example.sistemabiblioteca.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LivroTest {

    @Test
    void deveCriarLivroComSucesso() {
        Titulo titulo = new Titulo(7, "978-3-16-148410-0", 1, 2020);
        int codigo = 100;

        Livro livro = new Livro(codigo, titulo);

        assertEquals(codigo, livro.getCodigo());
        assertEquals(titulo, livro.getTitulo());
        assertTrue(livro.isDisponivel());
        assertFalse(livro.isExemplarBiblioteca());
    }


    @Test
    void deveRetornarZeroQuandoTituloNulo() {
        Livro livro = new Livro();
        livro.setCodigo(100);
        int prazo = livro.verPrazo();

        assertEquals(0, prazo);
    }

    @Test
    void deveAlterarDisponibilidadeDoLivro() {
        Titulo titulo = new Titulo(7, "978-3-16-148410-0", 1, 2020);
        Livro livro = new Livro(100, titulo);
        livro.setDisponivel(false);

        assertFalse(livro.isDisponivel());
    }

    @Test
    void deveImpedirEmprestimoQuandoLivroIndisponivel() {
        Titulo titulo = new Titulo(7, "978-3-16-148410-0", 1, 2020);
        Livro livro = new Livro(100, titulo);
        livro.setDisponivel(false);
        boolean disponivel = livro.isDisponivel();

        assertFalse(disponivel);
    }
}
