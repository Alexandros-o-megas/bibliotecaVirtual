package bd2.projecto.bibliotecaVirtual;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan; // Importe esta linha

@SpringBootApplication
// Adicione esta anotação para dizer ao Spring para procurar componentes neste pacote e subpacotes.
@ComponentScan({"bd2.projecto.bibliotecaVirtual"})
public class BibliotecaVirtualApplication {

    public static void main(String[] args) {
        SpringApplication.run(BibliotecaVirtualApplication.class, args);
    }
}
