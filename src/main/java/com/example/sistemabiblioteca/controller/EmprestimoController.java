package com.example.sistemabiblioteca.controller;

import com.example.sistemabiblioteca.model.Aluno;
import com.example.sistemabiblioteca.model.Emprestimo;
import com.example.sistemabiblioteca.model.ItemEmprestimo;
import com.example.sistemabiblioteca.model.Livro;
import com.example.sistemabiblioteca.repository.AlunoDAO;
import com.example.sistemabiblioteca.repository.EmprestimoDAO;
import com.example.sistemabiblioteca.repository.LivroDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoDAO emprestimoDAO;

    @Autowired
    private AlunoDAO alunoDAO;

    @Autowired
    private LivroDAO livroDAO;

    // 1. TELA DE LISTAGEM (Mostra todos os empréstimos já feitos)
    @GetMapping
    public String listarEmprestimos(Model model) {
        List<Emprestimo> lista = emprestimoDAO.findAll();
        model.addAttribute("emprestimos", lista);
        return "emprestimos/lista";
    }

    // 2. TELA DO FORMULÁRIO INICIAL (Onde o funcionário digita RA e Código do Livro)
    @GetMapping("/novo")
    public String exibirFormularioNovo() {
        return "emprestimos/form";
    }

    // 3. PASSO INTERMEDIÁRIO: BUSCA, VALIDA REGRAS DO DIAGRAMA E MOSTRA JANELA DE CONFIRMAÇÃO
    @PostMapping("/confirmar")
    public String confirmarEmprestimo(
            @RequestParam("alunoMatricula") int alunoMatricula,
            @RequestParam("livrosIds") int livroCodigo,
            Model model) {

        // Busca usando o método herdado do GenericDAO
        Aluno aluno = alunoDAO.find(alunoMatricula);
        Livro livro = livroDAO.find(livroCodigo);

        // Valida se o aluno existe
        if (aluno == null) {
            return "redirect:/emprestimos/novo?erro=aluno_nao_encontrado";
        }

        // Valida se o aluno tem débitos pendentes
        if (aluno.verificaDebito()) {
            return "redirect:/emprestimos/novo?erro=aluno_com_debito";
        }

        // Valida se o livro existe
        if (livro == null) {
            return "redirect:/emprestimos/novo?erro=livro_nao_encontrado";
        }

        // Valida se o exemplar está disponível
        if (!livro.isDisponivel()) {
            return "redirect:/emprestimos/novo?erro=livro_indisponivel";
        }

        // Cria o Empréstimo temporário na memória para calcular as datas da revisão
        Emprestimo emprestimoTemp = new Emprestimo();
        emprestimoTemp.setAluno(aluno);

        // Cria a relação de Item com o Livro
        ItemEmprestimo item = new ItemEmprestimo(livro, emprestimoTemp);
        emprestimoTemp.getI().add(item);

        // Executa a lógica de negócio para calcular o prazo correto
        emprestimoTemp.CalculaDataDevolucao();

        // Envia as entidades validadas para a página de confirmação
        model.addAttribute("aluno", aluno);
        model.addAttribute("livro", livro);
        model.addAttribute("emprestimo", emprestimoTemp);

        return "emprestimos/confirmacao";
    }

    // 4. PASSO FINAL: SALVAR DEFINITIVAMENTE NO BANCO DE DADOS
    @PostMapping("/salvar")
    public String salvarEmprestimo(
            @RequestParam("alunoMatricula") int alunoMatricula,
            @RequestParam("livrosIds") int livroCodigo) {

        Aluno aluno = alunoDAO.find(alunoMatricula);
        Livro livro = livroDAO.find(livroCodigo);

        // Cria o objeto definitivo
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setAluno(aluno);

        ItemEmprestimo item = new ItemEmprestimo(livro, emprestimo);
        emprestimo.getI().add(item);

        // Calcula as datas oficiais de gravação
        emprestimo.CalculaDataDevolucao();

        // Modifica o status do livro para indisponível (já que foi emprestado)
        livro.setDisponivel(false);
        livroDAO.save(livro);

        // Persiste o empréstimo usando a interface do repositório
        emprestimoDAO.save(emprestimo);

        return "redirect:/emprestimos?sucesso";
    }
}