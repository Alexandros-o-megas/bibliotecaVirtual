package bd2.projecto.bibliotecaVirtual.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuario")
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario;

    private String nome;
    private String email;
    private Integer idade;
    private String genero;
    private String profissao;
    private String rua;
    private String bairro;
    private String quarteirao;
    private String role; // USUARIO, BIBLIOTECARIO, ADMIN

    @OneToMany(mappedBy = "usuario")
    private Set<TelefoneUsuario> telefones = new HashSet<>();

    @OneToMany(mappedBy = "usuario")
    private Set<Emprestimo> emprestimos = new HashSet<>();

    @OneToMany(mappedBy = "usuario")
    private Set<Reserva> reservas = new HashSet<>();
}