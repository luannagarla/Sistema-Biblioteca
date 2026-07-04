package com.example.sistemabiblioteca.controller;

import com.example.sistemabiblioteca.model.Devolucao;
import com.example.sistemabiblioteca.model.Emprestimo;
import com.example.sistemabiblioteca.model.Livro;
import com.example.sistemabiblioteca.repository.DevolucaoDAO;
import com.example.sistemabiblioteca.repository.EmprestimoDAO;
import com.example.sistemabiblioteca.repository.LivroDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/devolucoes")
public class DevolucaoController {

    @Autowired
    private DevolucaoDAO devoluDAO;

    @Autowired
    private EmprestimoDAO emprestimoDAO;

    @Autowired
    private LivroDAO livroDAO;

    @GetMapping("/novo")
    public String abrirFormulario() {
        return "devolucoes/form";
    }

    // Recebendo código do livro, procurando o empréstimo e calculando a multa
    @PostMapping("/buscar")
    public String buscarEmprestimo(@RequestParam("codigoLivro") int codigoLivro, Model model) {
        Emprestimo emp = emprestimoDAO.encontrarPorLivroEmprestado(codigoLivro);

        if (emp == null) {
            model.addAttribute("erro", "Nenhum empréstimo ativo foi localizado para este código de livro.");
            return "devolucoes/form";
        }

        Date hoje = new Date();
        Date dataPrevista = emp.getDataPrevista();
        boolean emAtraso = hoje.after(dataPrevista);
        float valorMulta = 0.0f;

        if (emAtraso) {
            long diferencaEmMilissegundos = Math.abs(hoje.getTime() - dataPrevista.getTime());
            long diasAtraso = TimeUnit.DAYS.convert(diferencaEmMilissegundos, TimeUnit.MILLISECONDS);

            valorMulta = diasAtraso * 2.0f;
        }

        model.addAttribute("alunoNome", emp.getAluno().getNome());
        model.addAttribute("alunoMatricula", emp.getAluno().getMatricula());
        model.addAttribute("codigoLivro", codigoLivro);
        model.addAttribute("dataPrevista", dataPrevista);
        model.addAttribute("atraso", emAtraso);
        model.addAttribute("multa", valorMulta);

        return "devolucoes/confirmacao";
    }

    @PostMapping("/confirmar")
    public String confirmarDevolucao(@RequestParam("codigoLivro") int codigoLivro,
                                     @RequestParam("atraso") boolean atraso,
                                     @RequestParam("multa") float multa,
                                     RedirectAttributes redirect) {

        Livro livro = livroDAO.find(codigoLivro);

        if (livro != null) {
            // Regra de negócio: O livro volta a ficar disponível para empréstimo
            livro.setDisponivel(true);
            livroDAO.save(livro);

            // Registra a devolução concluída no histórico do banco
            Devolucao dev = new Devolucao(livro, atraso, multa);
            devoluDAO.save(dev);
        }

        redirect.addFlashAttribute("sucesso", "Devolução registrada e livro liberado com sucesso!");
        return "redirect:/devolucoes/lista";
    }

    @GetMapping("/lista")
    public String listarDevolucoes(Model model) {
        model.addAttribute("devolucoes", devoluDAO.findAll());
        return "devolucoes/lista";
    }
}
