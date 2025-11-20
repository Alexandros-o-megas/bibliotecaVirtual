package bd2.projecto.bibliotecaVirtual.repository;

import bd2.projecto.bibliotecaVirtual.model.Usuario;
import bd2.projecto.bibliotecaVirtual.model.dto.AnalyticsData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    @Query("SELECT new bd2.projecto.bibliotecaVirtual.model.dto.AnalyticsData(u.profissao, COUNT(u)) FROM Usuario u WHERE u.profissao IS NOT NULL GROUP BY u.profissao")
    List<AnalyticsData> countUsuariosPorProfissao();
    
    @Query("SELECT new bd2.projecto.bibliotecaVirtual.model.dto.AnalyticsData(u.genero, COUNT(u)) FROM Usuario u WHERE u.genero IS NOT NULL GROUP BY u.genero")
    List<AnalyticsData> countUsuariosPorGenero();
}