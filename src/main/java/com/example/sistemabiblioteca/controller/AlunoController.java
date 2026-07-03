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
        return "alunos/lista"; // Caminho da pasta que vamos usar
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
            @RequestParam("matricula") String matriculaStr, // <-- Pegamos como texto primeiro
            @RequestParam("nome") String nome,
            @RequestParam("cpf") String cpf,
            @RequestParam("endereco") String endereco) {
        Aluno aluno = new Aluno();
        try {
            aluno.setMatricula(Integer.parseInt(matriculaStr));
        } catch (NumberFormatException e) {
            String reduzida = matriculaStr.substring(Math.max(0, matriculaStr.length() - 8));
            aluno.setMatricula(Integer.parseInt(reduzida));
        }
        aluno.setNome(nome);
        aluno.setCpf(cpf);
        aluno.setEndereco(endereco);
        alunoDAO.save(aluno);
        // Retorna para o formulário limpando os campos e enviando parâmetro de sucesso para a view
        return "redirect:/alunos/novo?sucesso";
    }
}