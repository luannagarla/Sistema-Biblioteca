package com.example.sistemabiblioteca.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_livro")
public class Livro {

    @Id
    private int codigo;

    private boolean disponivel;
    private boolean exemplarBiblioteca;

    // Relacionamento muitos para um: vários exemplares físicos pertencem a um título abstrato
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "titulo_id")
    private Titulo titulo;

    public Livro() {}

    public Livro(int codigo, Titulo titulo) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.disponivel = true;
        this.exemplarBiblioteca = false;
    }

    // Delegação de responsabilidade para obter o prazo de devolução definido no título
    public int verPrazo() {
        if (this.titulo != null) {
            return this.titulo.getPrazo();
        }
        return 0;
    }

    // Verifica a correspondência do código do exemplar para controle do fluxo de empréstimo
    public boolean verificaLivro(int codigo) {
        return this.codigo == codigo;
    }

    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }
    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
    public boolean isExemplarBiblioteca() { return exemplarBiblioteca; }
    public void setExemplarBiblioteca(boolean exemplarBiblioteca) { this.exemplarBiblioteca = exemplarBiblioteca; }
    public Titulo getTitulo() { return titulo; }
    public void setTitulo(Titulo titulo) { this.titulo = titulo; }
}