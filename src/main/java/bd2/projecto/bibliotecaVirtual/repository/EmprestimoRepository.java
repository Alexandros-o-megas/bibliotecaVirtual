package bd2.projecto.bibliotecaVirtual.repository;

import bd2.projecto.bibliotecaVirtual.model.Emprestimo;
import bd2.projecto.bibliotecaVirtual.model.EmprestimoId;
import bd2.projecto.bibliotecaVirtual.model.dto.AnalyticsData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, EmprestimoId> {
    // CrudRepository agora usa EmprestimoId como chave

    @Query("SELECT e FROM Emprestimo e WHERE e.usuario.id_usuario = :usuarioId AND e.estado = :estado")
    List<Emprestimo> findByUsuarioIdAndEstado(@Param("usuarioId") Integer usuarioId, @Param("estado") String estado);

    @Query("SELECT e FROM Emprestimo e WHERE e.dataDevolucaoPrevista < :data AND e.estado = :estado")
    List<Emprestimo> findEmprestimosAtrasados(LocalDate data, String estado);

    List<Emprestimo> findByDataDevolucaoPrevistaBeforeAndEstado(LocalDate data, String estado);

    @Query("SELECT new bd2.projecto.bibliotecaVirtual.model.dto.AnalyticsData(EXTRACT(MONTH FROM e.data_requisicao), COUNT(e)) " +
            "FROM Emprestimo e " +
            "WHERE EXTRACT(YEAR FROM e.data_requisicao) = EXTRACT(YEAR FROM CURRENT_DATE) " +
            "GROUP BY EXTRACT(MONTH FROM e.data_requisicao) " +
            "ORDER BY EXTRACT(MONTH FROM e.data_requisicao)")
    List<AnalyticsData> countEmprestimosPorMes();
}
