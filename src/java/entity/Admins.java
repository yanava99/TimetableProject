package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author yanava99
 */
@Entity
@Table(name = "ADMINS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Admins.findAll", query = "SELECT a FROM Admins a")
    , @NamedQuery(name = "Admins.findByIdADMINS", query = "SELECT a FROM Admins a WHERE a.idADMINS = :idADMINS")
    , @NamedQuery(name = "Admins.findByPassword", query = "SELECT a FROM Admins a WHERE a.password = :password")})
public class Admins implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "idADMINS")
    private String idADMINS;
    @Size(max = 45)
    @Column(name = "password")
    private String password;

    public Admins() {
    }

    public Admins(String idADMINS) {
        this.idADMINS = idADMINS;
    }

    public String getIdADMINS() {
        return idADMINS;
    }

    public void setIdADMINS(String idADMINS) {
        this.idADMINS = idADMINS;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idADMINS != null ? idADMINS.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Admins)) {
            return false;
        }
        Admins other = (Admins) object;
        if ((this.idADMINS == null && other.idADMINS != null) || (this.idADMINS != null && !this.idADMINS.equals(other.idADMINS))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Admins[ idADMINS=" + idADMINS + " ]";
    }
    
}
