package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author yanava99
 */
@Entity
@Table(name = "GROUPS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Groups.findAll", query = "SELECT g FROM Groups g")
    , @NamedQuery(name = "Groups.findByIdGROUPS", query = "SELECT g FROM Groups g WHERE g.idGROUPS = :idGROUPS")
    , @NamedQuery(name = "Groups.findByYearInGroups", query = "SELECT g FROM Groups g WHERE g.yearInGroups = :yearInGroups")
    , @NamedQuery(name = "Groups.findByNumber", query = "SELECT g FROM Groups g WHERE g.number = :number")
    , @NamedQuery(name = "Groups.findBySpeciality", query = "SELECT g FROM Groups g WHERE g.speciality = :speciality")})
public class Groups implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idGROUPS")
    private Integer idGROUPS;
    @Column(name = "year_in_groups")
    private Integer yearInGroups;
    @Column(name = "number")
    private Integer number;
    @Size(max = 200)
    @Column(name = "speciality")
    private String speciality;
    @OneToMany(mappedBy = "groupInClasses")
    private Collection<Classes> classesCollection;
    @OneToMany(mappedBy = "groupInStudents")
    private Collection<Students> studentsCollection;

    public Groups() {
    }

    public Groups(Integer idGROUPS) {
        this.idGROUPS = idGROUPS;
    }

    public Integer getIdGROUPS() {
        return idGROUPS;
    }

    public void setIdGROUPS(Integer idGROUPS) {
        this.idGROUPS = idGROUPS;
    }

    public Integer getYearInGroups() {
        return yearInGroups;
    }

    public void setYearInGroups(Integer yearInGroups) {
        this.yearInGroups = yearInGroups;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @XmlTransient
    public Collection<Classes> getClassesCollection() {
        return classesCollection;
    }

    public void setClassesCollection(Collection<Classes> classesCollection) {
        this.classesCollection = classesCollection;
    }

    @XmlTransient
    public Collection<Students> getStudentsCollection() {
        return studentsCollection;
    }

    public void setStudentsCollection(Collection<Students> studentsCollection) {
        this.studentsCollection = studentsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGROUPS != null ? idGROUPS.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groups)) {
            return false;
        }
        Groups other = (Groups) object;
        if ((this.idGROUPS == null && other.idGROUPS != null) || (this.idGROUPS != null && !this.idGROUPS.equals(other.idGROUPS))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Groups[ idGROUPS=" + idGROUPS + " ]";
    }
    
}
