package com.example.sistemabiblioteca.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_titulo")
public class Titulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int prazo;
    private String isbn;
    private int edicao;
    private int ano;

    // Relacionamento muitos para muitos: um título pode ter vários autores e vice-versa
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "titulo_autor",
            joinColumns = @JoinColumn(name = "titulo_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores = new ArrayList<>();

    public Titulo() {}

    public Titulo(int prazo, String isbn, int edicao, int ano) {
        this.prazo = prazo;
        this.isbn = isbn;
        this.edicao = edicao;
        this.ano = ano;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getPrazo() { return prazo; }
    public void setPrazo(int prazo) { this.prazo = prazo; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public int getEdicao() { return edicao; }
    public void setEdicao(int edicao) { this.edicao = edicao; }
    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }
    public List<Autor> getAutores() { return autores; }
    public void setAutores(List<Autor> autores) { this.autores = autores; }

    // Método utilitário para adicionar novos autores mantendo a integridade da lista
    public void adicionarAutor(Autor autor) { this.autores.add(autor); }
}