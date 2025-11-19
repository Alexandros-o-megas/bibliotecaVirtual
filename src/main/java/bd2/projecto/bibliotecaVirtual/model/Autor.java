package bd2.projecto.bibliotecaVirtual.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "autor")
@Getter
@Setter
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_autor;
    
    private String nome;
    private String email;
    private String biografia;
    private String rua;
    private String bairro;
    private String avenida;
    
    @OneToMany(mappedBy = "autor")
    private Set<TelefoneAutor> telefones = new HashSet<>();
    
    @ManyToMany(mappedBy = "autores")
    private Set<Livro> livros = new HashSet<>();
}