package bd2.projecto.bibliotecaVirtual.repository;

import bd2.projecto.bibliotecaVirtual.model.Livro;
import bd2.projecto.bibliotecaVirtual.model.dto.AnalyticsData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {

    // Query para análise de volume: Conta livros por idioma
    @Query("SELECT new bd2.projecto.bibliotecaVirtual.model.dto.AnalyticsData(l.idioma, COUNT(l)) FROM Livro l WHERE l.idioma IS NOT NULL GROUP BY l.idioma ORDER BY COUNT(l) DESC")
    List<AnalyticsData> countLivrosPorIdioma();

    // Query para análise de volume: Conta livros por classificação
    @Query("SELECT new bd2.projecto.bibliotecaVirtual.model.dto.AnalyticsData(l.classificacao, COUNT(l)) FROM Livro l WHERE l.classificacao IS NOT NULL GROUP BY l.classificacao ORDER BY COUNT(l) DESC")
    List<AnalyticsData> countLivrosPorClassificacao();
}
