package bd2.projecto.bibliotecaVirtual.config;

import bd2.projecto.bibliotecaVirtual.model.Livro;
import bd2.projecto.bibliotecaVirtual.model.Exemplar;
import bd2.projecto.bibliotecaVirtual.repository.LivroRepository;
import bd2.projecto.bibliotecaVirtual.repository.ExemplarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private LivroRepository livroRepository;
    
    @Autowired
    private ExemplarRepository exemplarRepository;

    @Override
    public void run(String... args) throws Exception {
        // Criar alguns livros de exemplo apenas se não existirem
        if (livroRepository.count() == 0) {
            criarDadosExemplo();
        }
    }

    private void criarDadosExemplo() {
        try {
            // Livro 1
            Livro livro1 = new Livro();
            livro1.setNome("Dom Casmurro");
            livro1.setIsbn("978-85-7232-144-9");
            livro1.setIdioma("Português");
            livro1.setClassificacao("Literatura Brasileira");
            livro1.setDescricao("Romance clássico de Machado de Assis");
            livro1.setData_publicacao(LocalDate.of(1899, 1, 1));
            livro1 = livroRepository.save(livro1);

            // Livro 2
            Livro livro2 = new Livro();
            livro2.setNome("1984");
            livro2.setIsbn("978-85-359-0277-7");
            livro2.setIdioma("Português");
            livro2.setClassificacao("Ficção Científica");
            livro2.setDescricao("Distopia clássica de George Orwell");
            livro2.setData_publicacao(LocalDate.of(1949, 6, 8));
            livro2 = livroRepository.save(livro2);

            // Livro 3
            Livro livro3 = new Livro();
            livro3.setNome("Clean Code");
            livro3.setIsbn("978-85-513-0285-1");
            livro3.setIdioma("Inglês");
            livro3.setClassificacao("Tecnologia");
            livro3.setDescricao("Guia para escrever código limpo");
            livro3.setData_publicacao(LocalDate.of(2008, 8, 1));
            livro3 = livroRepository.save(livro3);

            // Criar exemplares
            criarExemplar(livro1, "EX001", "FISICO", "IMPRESSO", "Estante A1", "DISPONIVEL");
            criarExemplar(livro2, "EX002", "DIGITAL", "PDF", "/livros/1984.pdf", "DISPONIVEL");
            criarExemplar(livro3, "EX003", "FISICO", "IMPRESSO", "Estante B2", "EMPRESTADO");

            System.out.println("Dados de exemplo criados com sucesso!");
            
        } catch (Exception e) {
            System.err.println("Erro ao criar dados de exemplo: " + e.getMessage());
        }
    }

    private void criarExemplar(Livro livro, String codigo, String tipo, String formato, String localizacao, String estado) {
        Exemplar exemplar = new Exemplar();
        exemplar.setCodigo(codigo);
        exemplar.setTipo(tipo);
        exemplar.setFormato(formato);
        exemplar.setLocalizacao(localizacao);
        exemplar.setEstado(estado);
        exemplar.setRegrasAcesso("ABERTO");
        exemplar.setLivro(livro);
        exemplarRepository.save(exemplar);
    }
}