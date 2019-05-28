package bean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import org.primefaces.context.RequestContext;

import dao.StudentDAO;
import entity.Students;

/**
 *
 * @author yanava99
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private String id;
    private String password;
    
    @EJB
    private StudentDAO studentDAO;
    
    
    public String verify()
    {
        if(studentDAO.verify(id, password))
        {
            Students student = studentDAO.createStudentsObject(id);
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().getSessionMap().put("currStudent", student);
            return "home.xhtml";
        }
        RequestContext.getCurrentInstance().update("growl");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error","Invalid input, try again"));
        return "";
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StudentDAO getStudentDAO() {
        return studentDAO;
    }

    public void setStudentDAO(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }
    
    public LoginBean() {
    }
    
}
