package com.example.sistemabiblioteca.controller;

import com.example.sistemabiblioteca.model.Aluno;
import com.example.sistemabiblioteca.repository.AlunoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/alunos") // Todas as rotas de aluno vão começar com /alunos
public class AlunoController {

    @Autowired
    private AlunoDAO alunoDAO;

    // Método para abrir a tela de cadastro
    @GetMapping("/novo")
    public String exibirFormularioCadastro() {
        return "cadastro-aluno"; // Vai abrir o arquivo cadastro-aluno.html
    }

    // Método que recebe os dados da tela e salva usando o DAO
    @PostMapping("/salvar")
    public String salvarAluno(Aluno aluno) {
        alunoDAO.save(aluno); // Chama o método herdado do GenericDAO
        return "redirect:/alunos/novo?sucesso"; // Recarrega a página mostrando que deu certo
    }
}