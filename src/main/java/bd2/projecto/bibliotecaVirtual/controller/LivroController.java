package bd2.projecto.bibliotecaVirtual.controller;

import bd2.projecto.bibliotecaVirtual.model.Livro;
import bd2.projecto.bibliotecaVirtual.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping
    public Page<Livro> listarTodos(Pageable pageable) {
        // Usar paginação é uma boa prática para lidar com grandes volumes de dados
        return livroRepository.findAll(pageable);
    }
}
