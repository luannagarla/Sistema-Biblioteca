package com.example.sistemabiblioteca.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AutorTest {

    @Test
    void deveCriarAutorComDadosCompletos() {
        String nome = "José";
        String sobrenome = "Saramago";
        String titulacao = "Doutor";

        Autor autor = new Autor(nome, sobrenome, titulacao);

        assertEquals(nome, autor.getNome());
        assertEquals(sobrenome, autor.getSobrenome());
        assertEquals(titulacao, autor.getTitulacao());
    }

    @Test
    void deveDefinirSobrenomeDoAutor() {
        Autor autor = new Autor();
        String sobrenome = "de Assis";

        autor.setSobrenome(sobrenome);

        assertEquals(sobrenome, autor.getSobrenome());
    }

    @Test
    void deveAlterarNomeExistente() {
        Autor autor = new Autor("José", "Saramago", "Doutor");
        String novoNome = "José de";

        autor.setNome(novoNome);

        assertEquals(novoNome, autor.getNome());
        assertEquals("Saramago", autor.getSobrenome());
    }

}
