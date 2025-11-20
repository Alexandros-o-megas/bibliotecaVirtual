package bd2.projecto.bibliotecaVirtual.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "exemplar")
@Getter
@Setter
public class Exemplar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_exemplar;
    
    private String codigo;
    private String tipo; // FISICO, DIGITAL
    private String formato; // IMPRESSO, PDF, EPUB, AUDIOBOOK
    private String localizacao;
    private String estado; // DISPONIVEL, EMPRESTADO, RESERVADO, PERDIDO, MANUTENCAO
    private String regrasAcesso; // ABERTO, RESTRITO, SO_LEITURA
    
    @ManyToOne
    @JoinColumn(name = "id_livro")
    private Livro livro;
    
    @OneToMany(mappedBy = "exemplar")
    private Set<Emprestimo> emprestimos = new HashSet<>();
    
    @OneToMany(mappedBy = "exemplar")
    private Set<Reserva> reservas = new HashSet<>();
}