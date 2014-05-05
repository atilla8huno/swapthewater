package entidade;

import java.io.Serializable;
import java.util.Objects;
import persistencia.VoluntarioDAO;

/**
 *
 * @author ATILLA
 */
public class Voluntario implements Serializable {

    private Integer id;
    private String nome;
    private Integer qtdeDeTrocas;

    public Voluntario(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
        qtdeDeTrocas = 0;
    }

    public Voluntario() {
        id = 0;
        nome = "";
        qtdeDeTrocas = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQtdeDeTrocas() {
        qtdeDeTrocas = new VoluntarioDAO().getQtdeDeTrocas(id);
        return qtdeDeTrocas;
    }

    public boolean isTransient(){
        return (id == null || id == 0);
    }
    
    @Override
    public String toString() {
        return nome.toUpperCase();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Voluntario other = (Voluntario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
