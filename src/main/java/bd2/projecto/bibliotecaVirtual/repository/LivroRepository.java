package bd2.projecto.bibliotecaVirtual.repository;

import bd2.projecto.bibliotecaVirtual.model.Livro;
import bd2.projecto.bibliotecaVirtual.model.dto.AnalyticsData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {
    
    // Queries de análise
    @Query("SELECT new bd2.projecto.bibliotecaVirtual.model.dto.AnalyticsData(l.idioma, COUNT(l)) FROM Livro l WHERE l.idioma IS NOT NULL GROUP BY l.idioma ORDER BY COUNT(l) DESC")
    List<AnalyticsData> countLivrosPorIdioma();

    @Query("SELECT new bd2.projecto.bibliotecaVirtual.model.dto.AnalyticsData(l.classificacao, COUNT(l)) FROM Livro l WHERE l.classificacao IS NOT NULL GROUP BY l.classificacao ORDER BY COUNT(l) DESC")
    List<AnalyticsData> countLivrosPorClassificacao();

    @Query("SELECT new bd2.projecto.bibliotecaVirtual.model.dto.AnalyticsData(c.nome, COUNT(l)) FROM Livro l JOIN l.categorias c GROUP BY c.nome ORDER BY COUNT(l) DESC")
    List<AnalyticsData> countLivrosPorCategoria();

    // Pesquisa avançada
    @Query("SELECT l FROM Livro l WHERE " +
           "LOWER(l.nome) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "LOWER(l.descricao) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "LOWER(l.palavra_chave) LIKE LOWER(CONCAT('%', :termo, '%')) OR " +
           "LOWER(l.isbn) LIKE LOWER(CONCAT('%', :termo, '%'))")
    List<Livro> pesquisarLivros(@Param("termo") String termo);

    @Query("SELECT l FROM Livro l JOIN l.autores a WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', :autor, '%'))")
    List<Livro> pesquisarPorAutor(@Param("autor") String autor);
}