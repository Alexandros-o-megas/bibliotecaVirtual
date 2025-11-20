package bd2.projecto.bibliotecaVirtual.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "reserva")
@Getter
@Setter
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_reserva;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_exemplar")
    private Exemplar exemplar;

    private LocalDate data_reserva;
    private LocalDate data_expiracao;
    private Integer posicao_fila;
    private String estado; // ATIVA, CANCELADA, CONCLUIDA
}