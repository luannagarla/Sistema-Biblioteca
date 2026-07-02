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

    // Valida se as informações cadastrais obrigatórias foram preenchidas
    public boolean verificaAluno() {
        return this.nome != null && !this.nome.isEmpty() && this.cpf != null;
    }

    // Regra do caso de uso Emprestar Livro para bloquear empréstimos se houver pendências
    public boolean verificaDebito() {
        return false;
    }

    // Consulta histórico de débitos vinculados à matrícula do aluno
    public boolean obtemDebito(int alunoID) {
        return false;
    }

    public int getMatricula() { return matricula; }
    public void setMatricula(int matricula) { this.matricula = matricula; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
}