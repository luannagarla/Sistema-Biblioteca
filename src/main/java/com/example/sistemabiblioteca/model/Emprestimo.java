package com.example.sistemabiblioteca.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "emprestimos")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_matricula", nullable = false)
    private Aluno aluno; // Substitui o antigo 'String nome' por associação real

    @Temporal(TemporalType.DATE)
    private Date dataEmprestimo = new Date();

    @Temporal(TemporalType.DATE)
    private Date dataPrevista = new Date();

    // Cascading faz com que, ao salvar o Empréstimo, todos os itens sejam salvos juntos
    @OneToMany(mappedBy = "emprestimo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemEmprestimo> i = new ArrayList<>();

    // --- LÓGICA DO EXERCÍCIO REAPROVEITADA ---
    public Date CalculaDataDevolucao() {
        Date date = this.dataEmprestimo;
        Date maiorData = null;

        // Loop pelos itens do empréstimo
        for (int j = 0; j < i.size(); j++) {
            Date dataItem = i.get(j).calculaDataDevolucao(date);
            if (maiorData == null || dataItem.after(maiorData)) {
                maiorData = dataItem;
            }
        }

        // Regra de Negócio: Fluxo Alternativo para 3 ou mais livros
        if (i.size() >= 3) {
            int livrosExtras = i.size() - 2;
            int diasAdicionais = livrosExtras * 2;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(maiorData);
            calendar.add(Calendar.DATE, diasAdicionais);
            maiorData = calendar.getTime();
        }

        // Atribui o prazo final calculado a todos os itens
        for (int j = 0; j < i.size(); j++) {
            i.get(j).setDataDevolucao(maiorData);
        }

        this.dataPrevista = maiorData;
        return dataPrevista;
    }

    // --- GETTERS E SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }

    public Date getDataEmprestimo() { return dataEmprestimo; }
    public void setDataEmprestimo(Date dataEmprestimo) { this.dataEmprestimo = dataEmprestimo; }

    public Date getDataPrevista() { return dataPrevista; }
    public void setDataPrevista(Date dataPrevista) { this.dataPrevista = dataPrevista; }

    public List<ItemEmprestimo> getI() { return i; }
    public void setI(List<ItemEmprestimo> i) { this.i = i; }
}