package com.example.sistemabiblioteca.controller;

import com.example.sistemabiblioteca.model.Livro;
import com.example.sistemabiblioteca.model.Titulo;
import com.example.sistemabiblioteca.repository.LivroDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroDAO livroDAO;

    // Consulta todos os livros registrados para exibição na tabela de listagem
    @GetMapping
    public String listarLivros(Model model) {
        List<Livro> lista = livroDAO.findAll();
        model.addAttribute("livros", lista);
        return "livros/lista";
    }

    // Inicializa os objetos no model para vincular aos campos do formulário Thymeleaf
    @GetMapping("/novo")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("livro", new Livro());
        model.addAttribute("titulo", new Titulo());
        return "livros/form";
    }

    // Associa os dados do título ao livro recebido e salva a composição no banco
    @PostMapping("/salvar")
    public String salvarLivro(
            @ModelAttribute Livro livro,
            @ModelAttribute Titulo titulo,
            RedirectAttributes redirectAttributes) {
        try {
            livro.setTitulo(titulo);
            livroDAO.save(livro);

            redirectAttributes.addFlashAttribute("sucesso", "Livro cadastrado com sucesso!");
            return "redirect:/livros";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Não foi possível cadastrar: O código " + livro.getCodigo() + " já pertence a outro livro no acervo.");
            redirectAttributes.addFlashAttribute("livro", livro);
            redirectAttributes.addFlashAttribute("titulo", titulo);

            return "redirect:/livros/novo";
        }
    }

    // Remove o registro do livro utilizando o método de busca genérico antes da exclusão
    @GetMapping("/excluir/{codigo}")
    public String excluirLivro(@PathVariable int codigo) {
        Livro livro = livroDAO.find(codigo);
        if (livro != null) {
            livroDAO.delete(livro);
        }
        return "redirect:/livros";
    }
}