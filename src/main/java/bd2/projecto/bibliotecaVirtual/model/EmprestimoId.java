package bd2.projecto.bibliotecaVirtual.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class EmprestimoId implements Serializable {

    private Integer exemplar; // id do Exemplar
    private Integer usuario;  // id do Usuario
    private LocalDate data_requisicao;

    public EmprestimoId() {}

    public EmprestimoId(Integer exemplar, Integer usuario, LocalDate data_requisicao) {
        this.exemplar = exemplar;
        this.usuario = usuario;
        this.data_requisicao = data_requisicao;
    }

    // equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmprestimoId)) return false;
        EmprestimoId that = (EmprestimoId) o;
        return Objects.equals(exemplar, that.exemplar) &&
                Objects.equals(usuario, that.usuario) &&
                Objects.equals(data_requisicao, that.data_requisicao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exemplar, usuario, data_requisicao);
    }

    // Getters e Setters
    public Integer getExemplar() { return exemplar; }
    public void setExemplar(Integer exemplar) { this.exemplar = exemplar; }
    public Integer getUsuario() { return usuario; }
    public void setUsuario(Integer usuario) { this.usuario = usuario; }
    public LocalDate getData_requisicao() { return data_requisicao; }
    public void setData_requisicao(LocalDate data_requisicao) { this.data_requisicao = data_requisicao; }
}
