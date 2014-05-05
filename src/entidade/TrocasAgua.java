package entidade;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author ATILLA
 */
public class TrocasAgua implements Serializable {

    private Integer id;
    private Date dataTroca;
    private Voluntario voluntario;

    public TrocasAgua(Integer id, Date dataTroca, Voluntario voluntario) {
        this.id = id;
        this.dataTroca = dataTroca;
        this.voluntario = voluntario;
    }

    public TrocasAgua() {
        id = 0;
        dataTroca = new Date();
        voluntario = new Voluntario();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataTroca() {
        return dataTroca;
    }

    public void setDataTroca(Date dataTroca) {
        this.dataTroca = dataTroca;
    }

    public Voluntario getVoluntario() {
        return voluntario;
    }

    public void setVoluntario(Voluntario voluntario) {
        this.voluntario = voluntario;
    }
    
    public boolean isTransient(){
        return (id == null || id == 0);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TrocasAgua other = (TrocasAgua) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
