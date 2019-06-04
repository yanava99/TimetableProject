package bean;

import javax.inject.Named;
import javax.faces.context.FacesContext;
import java.util.List;
import javax.ejb.EJB;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import org.primefaces.context.RequestContext;

import entity.Students;
import entity.Classes;
import dao.ClassesDAO;
import dao.StudentDAO;


/**
 *
 * @author yanava99
 */
@Named(value = "homeBean")
@SessionScoped
public class HomeBean implements Serializable {
    
    private Students currStudent;
    
    private List<Classes> mondayList;
    private List<Classes> tuesdayList;
    private List<Classes> wednesdayList;
    private List<Classes> thursdayList;
    private List<Classes> fridayList;
    private List<Classes> saturdayList;
    
    private List<Students> studentsInGroup;
    
    private String currPassword;
    private String newPassword;

    public String getCurrPassword() {
        return currPassword;
    }

    public void setCurrPassword(String currPassword) {
        this.currPassword = currPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
    
    @EJB
    private ClassesDAO classesDAO;
    @EJB
    private StudentDAO studentDAO;
    
    
    @PostConstruct
    public void init() {
        currStudent=(Students)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currStudent");
        
        mondayList=classesDAO.getClassesByGroupAndWeekday(currStudent.getGroupInStudents(), "monday");
        tuesdayList=classesDAO.getClassesByGroupAndWeekday(currStudent.getGroupInStudents(), "tuesday");
        wednesdayList=classesDAO.getClassesByGroupAndWeekday(currStudent.getGroupInStudents(), "wednesday");
        thursdayList=classesDAO.getClassesByGroupAndWeekday(currStudent.getGroupInStudents(), "thursday");
        fridayList=classesDAO.getClassesByGroupAndWeekday(currStudent.getGroupInStudents(), "friday");
        saturdayList=classesDAO.getClassesByGroupAndWeekday(currStudent.getGroupInStudents(), "saturday");
        
        studentsInGroup=studentDAO.getStudentsByGroup(currStudent.getGroupInStudents());
    }

    public String getCurrentStudentInitials() {
        return currStudent.getInitials();
    }
    
    public String getCurrentStudentID() {
        return currStudent.getIdSTUDENTS();
    }
    
    public String getCurrentStudentEmail() {
        return currStudent.getEmail();
    }
    
    public int getCurrentStudentGroupNumber() {
        return currStudent.getGroupInStudents().getNumber();
    }
    
    public int getCurrentStudentGroupYear() {
        return currStudent.getGroupInStudents().getYearInGroups();
    }
    
    public List<Students> getStudentsInTheSameGroup() {
        return studentsInGroup;
    }
    
    public void changePassword() {
        if(studentDAO.verify(currStudent.getIdSTUDENTS(), currPassword))
        {
            studentDAO.changePassword(newPassword, currStudent.getIdSTUDENTS());
            RequestContext.getCurrentInstance().update("growl");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success!","Password was successfully changed."));
            currStudent.setPassword(newPassword);
    }
        else
        {
            RequestContext.getCurrentInstance().update("growl");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error!","Previous password isn't correct."));
        }
    }

    public List<Classes> getMondayList() {
        return mondayList;
    }

    public List<Classes> getTuesdayList() {
        return tuesdayList;
    }

    public List<Classes> getWednesdayList() {
        return wednesdayList;
    }

    public List<Classes> getThursdayList() {
        return thursdayList;
    }

    public List<Classes> getFridayList() {
       return fridayList;
    }

    public List<Classes> getSaturdayList() {
        return saturdayList;
    }

    public HomeBean() {}
}
