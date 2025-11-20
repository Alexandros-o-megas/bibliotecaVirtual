package bd2.projecto.bibliotecaVirtual.controller;

import bd2.projecto.bibliotecaVirtual.model.dto.AnalyticsData;
import bd2.projecto.bibliotecaVirtual.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private ExemplarRepository exemplarRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @GetMapping("/livros-por-idioma")
    public List<AnalyticsData> getLivrosPorIdioma() {
        return livroRepository.countLivrosPorIdioma();
    }

    @GetMapping("/livros-por-classificacao")
    public List<AnalyticsData> getLivrosPorClassificacao() {
        return livroRepository.countLivrosPorClassificacao();
    }

    @GetMapping("/livros-por-categoria")
    public List<AnalyticsData> getLivrosPorCategoria() {
        return livroRepository.countLivrosPorCategoria();
    }

    @GetMapping("/exemplares-por-estado")
    public List<AnalyticsData> getExemplaresPorEstado() {
        return exemplarRepository.countExemplaresPorEstado();
    }

    @GetMapping("/exemplares-por-tipo")
    public List<AnalyticsData> getExemplaresPorTipo() {
        return exemplarRepository.countExemplaresPorTipo();
    }

    @GetMapping("/usuarios-por-profissao")
    public List<AnalyticsData> getUsuariosPorProfissao() {
        return usuarioRepository.countUsuariosPorProfissao();
    }

    @GetMapping("/usuarios-por-genero")
    public List<AnalyticsData> getUsuariosPorGenero() {
        return usuarioRepository.countUsuariosPorGenero();
    }

    @GetMapping("/emprestimos-por-mes")
    public List<AnalyticsData> getEmprestimosPorMes() {
        return emprestimoRepository.countEmprestimosPorMes();
    }
}