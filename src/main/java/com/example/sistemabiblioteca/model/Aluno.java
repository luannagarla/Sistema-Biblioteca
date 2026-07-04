package com.example.sistemabiblioteca.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL)
    private List<Debito> debitos = new ArrayList<>();

    // Regra do caso de uso Emprestar Livro para bloquear empréstimos se houver pendências
    public boolean verificaDebito() {
        if (this.debitos == null) return false;

        // Varre a lista de débitos do aluno. Se achar algum que NÃO está pago, retorna true
        for (Debito d : debitos) {
            if (!d.isPago()) {
                return true;
            }
        }
        return false;
    }

    // Consulta histórico de débitos vinculados à matrícula do aluno
    public boolean obtemDebito(int alunoID) {
        // Se a matrícula passada não for a deste aluno, ou se ele não tiver débitos, retorna falso
        if (this.matricula != alunoID || this.debitos == null) {
            return false;
        }
        // Se ele tiver qualquer débito na lista, retorna verdadeiro informando que há histórico
        return !this.debitos.isEmpty();
    }
    public List<Debito> getDebitos() { return debitos; }
    public void setDebitos(List<Debito> debitos) { this.debitos = debitos; }
    public int getMatricula() { return matricula; }
    public void setMatricula(int matricula) { this.matricula = matricula; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
}