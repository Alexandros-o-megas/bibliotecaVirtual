package bd2.projecto.bibliotecaVirtual.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "editora")
@Getter
@Setter
public class Editora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_editora;

    private String nome;
    private String email;
    private String rua;
    private String bairro;
    private String quarteirao;
    private String descricao;

    @OneToMany(mappedBy = "editora")
    private Set<TelefoneEditora> telefones = new HashSet<>();

    @ManyToMany(mappedBy = "editoras")
    private Set<Livro> livros = new HashSet<>();
}