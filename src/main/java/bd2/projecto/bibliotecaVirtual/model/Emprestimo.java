package bd2.projecto.bibliotecaVirtual.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "ExemplarUsuario")
@IdClass(EmprestimoId.class)
@Access(AccessType.FIELD)
@Getter
@Setter
public class Emprestimo {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_exemplar")
    private Exemplar exemplar;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Id
    private LocalDate data_requisicao;

    private LocalDate data_devolucao;
    private LocalDate dataDevolucaoPrevista;
    private Integer renovacoes;
    private String estado; // ATIVO, CONCLUIDO, ATRASADO
    private Double multa;

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista(LocalDate data_devolucao_prevista) {
        this.dataDevolucaoPrevista = data_devolucao_prevista;
    }

}
