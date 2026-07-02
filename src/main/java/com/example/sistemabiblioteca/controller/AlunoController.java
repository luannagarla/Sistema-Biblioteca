package com.example.sistemabiblioteca.controller;

import com.example.sistemabiblioteca.model.Aluno;
import com.example.sistemabiblioteca.repository.AlunoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoDAO alunoDAO;

    // Renderiza a página com o formulário de cadastro de novos alunos
    @GetMapping("/novo")
    public String exibirFormularioCadastro() {
        return "cadastro-aluno";
    }

    // Processa o envio do formulário e persiste o aluno no banco de dados
    @PostMapping("/salvar")
    public String salvarAluno(Aluno aluno) {
        alunoDAO.save(aluno);
        // Retorna para o formulário limpando os campos e enviando parâmetro de sucesso para a view
        return "redirect:/alunos/novo?sucesso";
    }
}