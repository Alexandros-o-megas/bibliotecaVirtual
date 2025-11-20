package bd2.projecto.bibliotecaVirtual.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Objects;

@Entity
@Table(name = "telefone_editora")
@Getter
@Setter
public class TelefoneEditora {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_editora")
    private Editora editora;

    @Id
    private String telefone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TelefoneEditora)) return false;
        TelefoneEditora that = (TelefoneEditora) o;
        return Objects.equals(editora, that.editora) && Objects.equals(telefone, that.telefone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(editora, telefone);
    }
}