package bd2.projecto.bibliotecaVirtual.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "telefone_usuario")
@Getter
@Setter
public class TelefoneUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_telefone;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    private String telefone;
}