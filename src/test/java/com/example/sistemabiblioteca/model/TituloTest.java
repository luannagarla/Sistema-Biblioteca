package com.example.sistemabiblioteca.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class TituloTest {

    @Test
    void deveCriarTituloComDadosCompletos() {
        int prazo = 14;
        String isbn = "978-3-16-148410-0";
        int edicao = 1;
        int ano = 2020;

        Titulo titulo = new Titulo(prazo, isbn, edicao, ano);

        assertEquals(prazo, titulo.getPrazo());
        assertEquals(isbn, titulo.getIsbn());
        assertEquals(edicao, titulo.getEdicao());
        assertEquals(ano, titulo.getAno());
    }

    @Test
    void deveAdicionarAutorAoTitulo() {
        Titulo titulo = new Titulo(14, "978-3-16-148410-0", 1, 2020);
        Autor autor = new Autor("José", "Saramago", "Doutor");

        titulo.adicionarAutor(autor);

        assertEquals(1, titulo.getAutores().size());
        assertEquals(autor, titulo.getAutores().get(0));
    }

    @Test
    void deveDefinirPrazo() {
        Titulo titulo = new Titulo();
        int prazo = 21;

        titulo.setPrazo(prazo);

        assertEquals(prazo, titulo.getPrazo());
    }

    @Test
    void deveDefinirAno() {
        Titulo titulo = new Titulo();
        int ano = 2021;

        titulo.setAno(ano);

        assertEquals(ano, titulo.getAno());
    }
   
}
