package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "STUDENTS")
@XmlRootElement
@NamedQueries({
      @NamedQuery(name = "Students.findAll", query = "SELECT s FROM Students s")
    , @NamedQuery(name = "Students.findByIdSTUDENTS", query = "SELECT s FROM Students s WHERE s.idSTUDENTS = :idSTUDENTS")
    , @NamedQuery(name = "Students.findByPassword", query = "SELECT s FROM Students s WHERE s.password = :password")
    , @NamedQuery(name = "Students.findByInitials", query = "SELECT s FROM Students s WHERE s.initials = :initials")
    , @NamedQuery(name = "Students.findByEmail", query = "SELECT s FROM Students s WHERE s.email = :email")
    , @NamedQuery(name = "Students.findByGroup", query = "SELECT s FROM Students s WHERE s.groupInStudents = :groupInStudents")
    , @NamedQuery(name = "Students.changePassword", query = "UPDATE Students s SET s.password = :password WHERE s.idSTUDENTS = :idSTUDENTS")})
public class Students implements Serializable, Comparable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "idSTUDENTS")
    private String idSTUDENTS;
    @Size(max = 45)
    @Column(name = "password")
    private String password;
    @Size(max = 200)
    @Column(name = "initials")
    private String initials;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 75)
    @Column(name = "email")
    private String email;
    @JoinColumn(name = "group_in_students", referencedColumnName = "idGROUPS")
    @ManyToOne
    private Groups groupInStudents;

    public Students() {
    }

    public Students(String idSTUDENTS) {
        this.idSTUDENTS = idSTUDENTS;
    }

    public String getIdSTUDENTS() {
        return idSTUDENTS;
    }

    public void setIdSTUDENTS(String idSTUDENTS) {
        this.idSTUDENTS = idSTUDENTS;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Groups getGroupInStudents() {
        return groupInStudents;
    }

    public void setGroupInStudents(Groups groupInStudents) {
        this.groupInStudents = groupInStudents;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSTUDENTS != null ? idSTUDENTS.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Students)) {
            return false;
        }
        Students other = (Students) object;
        if ((this.idSTUDENTS == null && other.idSTUDENTS != null) || (this.idSTUDENTS != null && !this.idSTUDENTS.equals(other.idSTUDENTS))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Students[ idSTUDENTS=" + idSTUDENTS + " ]";
    }
    
    @Override
    public int compareTo(Object other)
    {
        return initials.compareTo(((Students)other).getInitials());
    }
}
