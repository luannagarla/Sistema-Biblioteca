package com.example.sistemabiblioteca.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_debitos")
public class Debito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_matricula", nullable = false)
    private Aluno aluno; // Vincula o débito ao aluno específico

    private double valor;
    private boolean pago = false; //

    public Debito() {}

    public Debito(Aluno aluno, double valor) {
        this.aluno = aluno;
        this.valor = valor;
        this.pago = false;
    }

    // --- GETTERS E SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
    public boolean isPago() { return pago; }
    public void setPago(boolean pago) { this.pago = pago; }
}