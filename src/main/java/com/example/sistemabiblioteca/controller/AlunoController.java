package com.example.sistemabiblioteca.controller;

import com.example.sistemabiblioteca.model.Aluno;
import com.example.sistemabiblioteca.repository.AlunoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoDAO alunoDAO;

    // Consulta todos os alunos registrados para exibição na tabela de listagem
    @GetMapping
    public String listarAlunos(org.springframework.ui.Model model) {
        java.util.List<Aluno> lista = alunoDAO.findAll();
        model.addAttribute("alunos", lista);
        return "alunos/lista";
    }

    // Renderiza a página com o formulário de cadastro de novos alunos
    @GetMapping("/novo")
    public String exibirFormularioCadastro(org.springframework.ui.Model model) {
        model.addAttribute("aluno", new Aluno());
        return "alunos/form";
    }

    // Processa o envio do formulário e persiste o aluno no banco de dados
    @PostMapping("/salvar")
    public String salvarAluno(
            @RequestParam("matricula") int matricula,
            @RequestParam("nome") String nome,
            @RequestParam("cpf") String cpf,
            @RequestParam("endereco") String endereco,
            org.springframework.web.servlet.mvc.support.RedirectAttributes redirectAttributes) {
        try {
            Aluno aluno = new Aluno();
            aluno.setMatricula(matricula);
            aluno.setNome(nome);
            aluno.setCpf(cpf);
            aluno.setEndereco(endereco);
            alunoDAO.save(aluno);
            redirectAttributes.addFlashAttribute("sucesso", "Aluno cadastrado com sucesso!");
            return "redirect:/alunos/novo";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Não foi possível cadastrar: Matrícula ou CPF já existem no sistema.");
            return "redirect:/alunos/novo";
        }
    }
}