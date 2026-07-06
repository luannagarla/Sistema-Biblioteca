package com.example.sistemabiblioteca.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_devolucoes")
public class Devolucao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date dataDevolucao = new Date(); // Salva o dia atual automaticamente

    private boolean atraso;
    private float multa;

    @ManyToOne
    @JoinColumn(name = "livro_codigo", nullable = false)
    private Livro livro;

    // Construtor padrão exigido pelo Hibernate
    public Devolucao() {}

    public Devolucao(Livro livro, boolean atraso, float multa) {
        this.livro = livro;
        this.atraso = atraso;
        this.multa = multa;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Date getDataDevolucao() { return dataDevolucao; }
    public void setDataDevolucao(Date dataDevolucao) { this.dataDevolucao = dataDevolucao; }

    public boolean isAtraso() { return atraso; }
    public void setAtraso(boolean atraso) { this.atraso = atraso; }

    public float getMulta() { return multa; }
    public void setMulta(float multa) { this.multa = multa; }

    public Livro getLivro() { return livro; }
    public void setLivro(Livro livro) { this.livro = livro; }
}