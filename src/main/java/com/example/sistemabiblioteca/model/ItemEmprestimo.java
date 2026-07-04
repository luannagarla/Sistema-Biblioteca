package com.example.sistemabiblioteca.model;

import jakarta.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "item_emprestimo")
public class ItemEmprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;

    @ManyToOne
    @JoinColumn(name = "emprestimo_id", nullable = false)
    private Emprestimo emprestimo;

    @Temporal(TemporalType.DATE)
    private Date dataDevolucao;

    public ItemEmprestimo() {}

    public ItemEmprestimo(Livro livro, Emprestimo emprestimo) {
        this.livro = livro;
        this.emprestimo = emprestimo;
    }

    // --- LÓGICA DO EXERCÍCIO REAPROVEITADA ---
    public Date calculaDataDevolucao(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);

        // verPrazo() busca o prazo configurado no Título do Livro
        int prazo = livro.verPrazo();
        calendar.add(Calendar.DATE, prazo);
        this.dataDevolucao = calendar.getTime();
        return this.dataDevolucao;
    }

    // --- GETTERS E SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Livro getLivro() { return livro; }
    public void setLivro(Livro livro) { this.livro = livro; }

    public Emprestimo getEmprestimo() { return emprestimo; }
    public void setEmprestimo(Emprestimo emprestimo) { this.emprestimo = emprestimo; }

    public Date getDataDevolucao() { return dataDevolucao; }
    public void setDataDevolucao(Date dataDevolucao) { this.dataDevolucao = dataDevolucao; }
}