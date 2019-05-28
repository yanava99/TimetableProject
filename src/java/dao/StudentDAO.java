package dao;

import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Collections;

import entity.Students;
import entity.Groups;

/**
 *
 * @author yanava99
 */

@Stateless
@LocalBean
public class StudentDAO {
    
    @PersistenceContext(unitName="TryingPU")
    private EntityManager em;
    
    public boolean verify(String idSTUDENTS, String password)
    {
        List<Students> list = em.createNamedQuery("Students.findByIdSTUDENTS", Students.class).setParameter("idSTUDENTS", idSTUDENTS).getResultList();
        
        if(list.isEmpty())
            return false;
        Students student = (Students)list.get(0);
        return (student != null || password.equals(student.getPassword()));
    }
    
    public Students createStudentsObject(String id)
    {
        return em.find(Students.class, id);
    }
    
    public List<Students> getStudentsByGroup(Groups group)
    {
        List<Students> list = em.createNamedQuery("Students.findByGroup", Students.class).setParameter("groupInStudents", group).getResultList();
        Collections.sort(list);
        return list;
    }
    
    public boolean changePassword(String newPassword, String idSTUDENTS)
    {
        em.createNamedQuery("Students.changePassword", Students.class).setParameter("password", newPassword).setParameter("idSTUDENTS", idSTUDENTS).executeUpdate();
        return verify(idSTUDENTS, newPassword);
    }
}
