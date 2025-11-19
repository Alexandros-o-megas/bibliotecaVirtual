package bd2.projecto.bibliotecaVirtual.controller;

import bd2.projecto.bibliotecaVirtual.model.dto.AnalyticsData;
import bd2.projecto.bibliotecaVirtual.repository.LivroRepository;
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

    @GetMapping("/livros-por-idioma")
    public List<AnalyticsData> getLivrosPorIdioma() {
        return livroRepository.countLivrosPorIdioma();
    }

    @GetMapping("/livros-por-classificacao")
    public List<AnalyticsData> getLivrosPorClassificacao() {
        return livroRepository.countLivrosPorClassificacao();
    }
}
