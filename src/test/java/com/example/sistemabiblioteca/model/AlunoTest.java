package com.example.sistemabiblioteca.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AlunoTest {

    @Test
    void deveCriarAlunoComMatricula() {
        int matricula = 12345;

        Aluno aluno = new Aluno(matricula);
        assertEquals(matricula, aluno.getMatricula());
    }

    @Test
    void deveRetornarFalsoQuandoNomeNulo() {
        Aluno aluno = new Aluno();
        aluno.setMatricula(12345);
        aluno.setNome(null);
        aluno.setCpf("123.456.789-00");

        boolean valido = aluno.verificaAluno();

        assertFalse(valido);
    }

    @Test
    void deveVerificarDebito() {
        Aluno aluno = new Aluno(12345);
        boolean temDebito = aluno.verificaDebito();
        assertFalse(temDebito);
    }

    @Test
    void deveObterDebitoPorMatricula() {
        Aluno aluno = new Aluno(12345);
        int alunoID = 12345;

        boolean temDebito = aluno.obtemDebito(alunoID);

        assertFalse(temDebito);
    }
}
