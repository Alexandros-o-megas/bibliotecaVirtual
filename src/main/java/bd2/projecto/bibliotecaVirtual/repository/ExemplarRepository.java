package bd2.projecto.bibliotecaVirtual.repository;

import bd2.projecto.bibliotecaVirtual.model.Exemplar;
import bd2.projecto.bibliotecaVirtual.model.dto.AnalyticsData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ExemplarRepository extends JpaRepository<Exemplar, Integer> {

    @Query("""
        SELECT e
        FROM Exemplar e
        WHERE e.livro.idlivro = :idlivro
        AND e.estado = :estado
        """)
    List<Exemplar> findByLivroAndEstado(
            @Param("idlivro") Integer idlivro,
            @Param("estado") String estado
    );


    // Método alternativo usando convenção do Spring Data
    List<Exemplar> findByLivroIdlivroAndEstado(Integer idlivro, String estado);

    List<Exemplar> findByEstado(String estado);

    @Query("SELECT new bd2.projecto.bibliotecaVirtual.model.dto.AnalyticsData(e.estado, COUNT(e)) FROM Exemplar e GROUP BY e.estado")
    List<AnalyticsData> countExemplaresPorEstado();

    @Query("SELECT new bd2.projecto.bibliotecaVirtual.model.dto.AnalyticsData(e.tipo, COUNT(e)) FROM Exemplar e GROUP BY e.tipo")
    List<AnalyticsData> countExemplaresPorTipo();

    @Query("SELECT new bd2.projecto.bibliotecaVirtual.model.dto.AnalyticsData(e.formato, COUNT(e)) FROM Exemplar e GROUP BY e.formato")
    List<AnalyticsData> countExemplaresPorFormato();
}