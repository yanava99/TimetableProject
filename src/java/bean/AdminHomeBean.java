package bean;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

import entity.Admins;
import dao.ClassesDAO;
import dao.StudentDAO;
import javax.faces.application.FacesMessage;

/**
 *
 * @author yanava99
 */
@Named(value = "adminHomeBean")
@SessionScoped
public class AdminHomeBean implements Serializable {

    private Admins currAdmin;

    public String getCurrentAdminLogin()
    {
        return currAdmin.getIdADMINS();
    }
    
    
    @EJB
    private ClassesDAO classesDAO;
    @EJB
    private StudentDAO studentDAO;
    
    @PostConstruct
    public void init()
    {
        currAdmin=(Admins)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("currAdmin");
    }
    
    private String newClassesSubject;
    private String newClassesTeacher;
    private String newClassesWeekday;
    private String newClassesTimeOfClass;
    private int newClassesRoom;
    private int newClassesGroupYear;
    private int newClassesGroupNumber;
    
    private String newStudentsIdSTUDENTS;
    private String newStudentsPassword;
    private String newStudentsInitials;
    private String newStudentsEmail;
    private int newStudentsGroupYear;
    private int newStudentsGroupNumber;
    
    private String deleteStudentsId;
    
    private int deleteClassYear;
    private int deleteClassNumber;
    private String deleteClassWeekday;
    private String deleteClassTime;
    
    public void createClass()
    {
        if(classesDAO.createClass(newClassesSubject, newClassesTeacher, newClassesWeekday, 
                newClassesTimeOfClass, newClassesRoom, newClassesGroupYear, newClassesGroupNumber))
        {
            RequestContext.getCurrentInstance().update("growlAdmin");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success!","Class was successfully added."));
        }
        else
        {
            RequestContext.getCurrentInstance().update("growlAdmin");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error!","Class wasn't added."));
        }
    }
    
    public void createStudent()
    {
        if(studentDAO.createStudent(newStudentsIdSTUDENTS, newStudentsPassword, newStudentsInitials, 
                newStudentsEmail, newStudentsGroupYear, newStudentsGroupNumber))
        {
            RequestContext.getCurrentInstance().update("growlAdmin");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success!","Student was successfully added."));
        }
        else
        {
            RequestContext.getCurrentInstance().update("growlAdmin");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error!","Student wasn't added."));
        }
    }
    
    public void deleteStudent()
    {
        String msg = studentDAO.deleteStudent(deleteStudentsId);
        
        RequestContext.getCurrentInstance().update("growlAdmin");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Result:",msg));
    }
    
    public void deleteClass()
    {
        String msg = classesDAO.deleteClass(deleteClassYear, deleteClassNumber, deleteClassWeekday, deleteClassTime);
        
        RequestContext.getCurrentInstance().update("growlAdmin");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Result:",msg));
    }

    public String getNewClassesSubject() {
        return newClassesSubject;
    }

    public void setNewClassesSubject(String newClassesSubject) {
        this.newClassesSubject = newClassesSubject;
    }

    public String getNewClassesTeacher() {
        return newClassesTeacher;
    }

    public void setNewClassesTeacher(String newClassesTeacher) {
        this.newClassesTeacher = newClassesTeacher;
    }

    public String getNewClassesWeekday() {
        return newClassesWeekday;
    }

    public void setNewClassesWeekday(String newClassesWeekday) {
        this.newClassesWeekday = newClassesWeekday;
    }

    public String getNewClassesTimeOfClass() {
        return newClassesTimeOfClass;
    }

    public void setNewClassesTimeOfClass(String newClassesTimeOfClass) {
        this.newClassesTimeOfClass = newClassesTimeOfClass;
    }

    public int getNewClassesRoom() {
        return newClassesRoom;
    }

    public void setNewClassesRoom(int newClassesRoom) {
        this.newClassesRoom = newClassesRoom;
    }

    public int getNewClassesGroupYear() {
        return newClassesGroupYear;
    }

    public void setNewClassesGroupYear(int newClassesGroupYear) {
        this.newClassesGroupYear = newClassesGroupYear;
    }

    public int getNewClassesGroupNumber() {
        return newClassesGroupNumber;
    }

    public void setNewClassesGroupNumber(int newClassesGroupNumber) {
        this.newClassesGroupNumber = newClassesGroupNumber;
    }

    public String getNewStudentsIdSTUDENTS() {
        return newStudentsIdSTUDENTS;
    }

    public void setNewStudentsIdSTUDENTS(String newStudentsIdSTUDENTS) {
        this.newStudentsIdSTUDENTS = newStudentsIdSTUDENTS;
    }

    public String getNewStudentsPassword() {
        return newStudentsPassword;
    }

    public void setNewStudentsPassword(String newStuddentsPassword) {
        this.newStudentsPassword = newStuddentsPassword;
    }

    public String getNewStudentsInitials() {
        return newStudentsInitials;
    }

    public void setNewStudentsInitials(String newStidentsInitials) {
        this.newStudentsInitials = newStidentsInitials;
    }

    public String getNewStudentsEmail() {
        return newStudentsEmail;
    }

    public void setNewStudentsEmail(String newStudentsEmail) {
        this.newStudentsEmail = newStudentsEmail;
    }

    public int getNewStudentsGroupYear() {
        return newStudentsGroupYear;
    }

    public void setNewStudentsGroupYear(int newStudentsGroupYear) {
        this.newStudentsGroupYear = newStudentsGroupYear;
    }

    public int getNewStudentsGroupNumber() {
        return newStudentsGroupNumber;
    }

    public void setNewStudentsGroupNumber(int newStudentsGroupNumber) {
        this.newStudentsGroupNumber = newStudentsGroupNumber;
    }

    public String getDeleteStudentsId() {
        return deleteStudentsId;
    }

    public void setDeleteStudentsId(String deleteStudentsId) {
        this.deleteStudentsId = deleteStudentsId;
    }    

    public int getDeleteClassYear() {
        return deleteClassYear;
    }

    public void setDeleteClassYear(int deleteClassYear) {
        this.deleteClassYear = deleteClassYear;
    }

    public int getDeleteClassNumber() {
        return deleteClassNumber;
    }

    public void setDeleteClassNumber(int deleteClassNumber) {
        this.deleteClassNumber = deleteClassNumber;
    }

    public String getDeleteClassWeekday() {
        return deleteClassWeekday;
    }

    public void setDeleteClassWeekday(String deleteClassWeekday) {
        this.deleteClassWeekday = deleteClassWeekday;
    }

    public String getDeleteClassTime() {
        return deleteClassTime;
    }

    public void setDeleteClassTime(String deleteClassTime) {
        this.deleteClassTime = deleteClassTime;
    }
    
    
    
    public AdminHomeBean() {}
    
}
