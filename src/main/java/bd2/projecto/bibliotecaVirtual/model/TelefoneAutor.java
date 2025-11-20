package bd2.projecto.bibliotecaVirtual.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Objects;

@Entity
@Table(name = "telefone_autor")
@Getter
@Setter
public class TelefoneAutor {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Autor autor;

    @Id
    private String telefone;

    // Implementar equals e hashCode para evitar warnings
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TelefoneAutor)) return false;
        TelefoneAutor that = (TelefoneAutor) o;
        return Objects.equals(autor, that.autor) && Objects.equals(telefone, that.telefone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(autor, telefone);
    }
}