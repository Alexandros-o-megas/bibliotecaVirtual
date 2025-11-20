package bd2.projecto.bibliotecaVirtual.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "livro")
@Getter
@Setter
public class Livro {
    @Id
    @Column(name = "id_livro")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idlivro;

    private String nome;
    private String isbn;
    private String idioma;
    private String descricao;
    private String palavra_chave;
    private String classificacao;
    private String edicao;
    private Integer numero_paginas;
    private LocalDate data_publicacao;

    @Version
    private Long version;

    @ManyToMany
    @JoinTable(
        name = "Publicacao",
        joinColumns = @JoinColumn(name = "id_livro"),
        inverseJoinColumns = @JoinColumn(name = "id_autor")
    )
    private Set<Autor> autores = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "livro_categoria",
        joinColumns = @JoinColumn(name = "id_livro"),
        inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private Set<Categoria> categorias = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "LivroEditora",
        joinColumns = @JoinColumn(name = "id_livro"),
        inverseJoinColumns = @JoinColumn(name = "id_editora")
    )
    private Set<Editora> editoras = new HashSet<>();

    @OneToMany(mappedBy = "livro")
    private Set<Exemplar> exemplares = new HashSet<>();

    @OneToMany(mappedBy = "livro")
    private Set<Comentario> comentarios = new HashSet<>();
}