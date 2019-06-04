package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author yanava99
 */
@Entity
@Table(name = "CLASSES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Classes.findAll", query = "SELECT c FROM Classes c")
    , @NamedQuery(name = "Classes.findByIdCLASSES", query = "SELECT c FROM Classes c WHERE c.idCLASSES = :idCLASSES")
    , @NamedQuery(name = "Classes.findBySubject", query = "SELECT c FROM Classes c WHERE c.subject = :subject")
    , @NamedQuery(name = "Classes.findByTeacher", query = "SELECT c FROM Classes c WHERE c.teacher = :teacher")
    , @NamedQuery(name = "Classes.findByWeekday", query = "SELECT c FROM Classes c WHERE c.weekday = :weekday")
    , @NamedQuery(name = "Classes.findByTimeOfClass", query = "SELECT c FROM Classes c WHERE c.timeOfClass = :timeOfClass")
    , @NamedQuery(name = "Classes.findByRoom", query = "SELECT c FROM Classes c WHERE c.room = :room")
    , @NamedQuery(name = "Classes.findByGroupAndWeekday", query = "SELECT c FROM Classes c WHERE c.groupInClasses = :groupInClasses AND c.weekday = :weekday")
    , @NamedQuery(name = "Classes.findByAllButID", query = "SELECT c FROM Classes c WHERE c.subject = :subject AND c.teacher = :teacher AND c.weekday = :weekday AND c.timeOfClass = :timeOfClass AND c.room = :room AND c.groupInClasses = :groupInClasses")
    , @NamedQuery(name = "Classes.findByGroupWeekdayAndTimeOfClass", query = "SELECT c FROM Classes c WHERE c.weekday = :weekday AND c.timeOfClass = :timeOfClass AND c.groupInClasses = :groupInClasses")})
public class Classes implements Serializable, Comparable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCLASSES")
    private Integer idCLASSES;
    @Size(max = 100)
    @Column(name = "subject")
    private String subject;
    @Size(max = 200)
    @Column(name = "teacher")
    private String teacher;
    @Size(max = 45)
    @Column(name = "weekday")
    private String weekday;
    @Size(max = 10)
    @Column(name = "time_of_class")
    private String timeOfClass;
    @Column(name = "room")
    private Integer room;
    @JoinColumn(name = "group_in_classes", referencedColumnName = "idGROUPS")
    @ManyToOne
    private Groups groupInClasses;

    public Classes() {
    }

    public Classes(Integer idCLASSES) {
        this.idCLASSES = idCLASSES;
    }

    public Integer getIdCLASSES() {
        return idCLASSES;
    }

    public void setIdCLASSES(Integer idCLASSES) {
        this.idCLASSES = idCLASSES;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getTimeOfClass() {
        return timeOfClass;
    }

    public void setTimeOfClass(String timeOfClass) {
        this.timeOfClass = timeOfClass;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public Groups getGroupInClasses() {
        return groupInClasses;
    }

    public void setGroupInClasses(Groups groupInClasses) {
        this.groupInClasses = groupInClasses;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCLASSES != null ? idCLASSES.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Classes)) {
            return false;
        }
        Classes other = (Classes) object;
        if ((this.idCLASSES == null && other.idCLASSES != null) || (this.idCLASSES != null && !this.idCLASSES.equals(other.idCLASSES))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Classes[ idCLASSES=" + idCLASSES + " ]";
    }
    
    @Override
    public int compareTo(Object other)
    {
        return timeOfClass.compareTo(((Classes)other).getTimeOfClass());
    }
}
