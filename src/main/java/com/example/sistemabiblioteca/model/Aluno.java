package com.example.sistemabiblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "alunos")
public class Aluno {

    @Id
    private int matricula;
    private String nome;
    private String cpf;
    private String endereco;

    public Aluno() {
    }

    public Aluno(int matricula) {
        this.matricula = matricula;
    }

    public boolean verificaAluno() {
        // Retorna verdadeiro se os dados básicos estiverem preenchidos
        return this.nome != null && !this.nome.isEmpty() && this.cpf != null;
    }

    public boolean verificaDebito() {
        // No diagrama de sequência, o Aluno valida se tem débitos pendentes.
        // Por enquanto, retorna false até o implementarmos a classe Debito.
        return false;
    }

    public boolean obtemDebito(int alunoID) {
        // Lógica para verificar o histórico de débitos deste ID
        return false;
    }

    // Getters e Setters
    public int getMatricula() { return matricula; }
    public void setMatricula(int matricula) { this.matricula = matricula; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
}