package bd2.projecto.bibliotecaVirtual.controller;

import bd2.projecto.bibliotecaVirtual.model.Livro;
import bd2.projecto.bibliotecaVirtual.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping
    public Page<Livro> listarTodos(Pageable pageable) {
        return livroRepository.findAll(pageable);
    }

    @GetMapping("/pesquisar")
    public List<Livro> pesquisarLivros(@RequestParam String termo) {
        return livroRepository.pesquisarLivros(termo);
    }

    @GetMapping("/pesquisar/autor")
    public List<Livro> pesquisarPorAutor(@RequestParam String autor) {
        return livroRepository.pesquisarPorAutor(autor);
    }

    @GetMapping("/{id}")
    public Livro buscarPorId(@PathVariable Integer id) {
        return livroRepository.findById(id).orElse(null);
    }
}