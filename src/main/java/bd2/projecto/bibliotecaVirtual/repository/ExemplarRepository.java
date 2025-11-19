package bd2.projecto.bibliotecaVirtual.repository;

import bd2.projecto.bibliotecaVirtual.model.Exemplar;
import bd2.projecto.bibliotecaVirtual.model.dto.AnalyticsData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ExemplarRepository extends JpaRepository<Exemplar, Integer> {
    
    List<Exemplar> findByLivroIdAndEstado(Integer livroId, String estado);
    
    @Query("SELECT new bd2.projecto.bibliotecaVirtual.model.dto.AnalyticsData(e.estado, COUNT(e)) FROM Exemplar e GROUP BY e.estado")
    List<AnalyticsData> countExemplaresPorEstado();
    
    @Query("SELECT new bd2.projecto.bibliotecaVirtual.model.dto.AnalyticsData(e.tipo, COUNT(e)) FROM Exemplar e GROUP BY e.tipo")
    List<AnalyticsData> countExemplaresPorTipo();
}