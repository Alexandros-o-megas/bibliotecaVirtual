package bd2.projecto.bibliotecaVirtual.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "livro")
@Getter
@Setter
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_livro;

    private String nome;
    private String idioma;
    private String classificacao;
    private LocalDate data_publicacao;

    // Concorrência: Adiciona uma coluna de versão para bloqueio otimista
    @Version
    private Long version;
}
