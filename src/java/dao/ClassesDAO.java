package dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Collections;
import javax.ejb.EJB;

import entity.Classes;
import entity.Groups;

/**
 *
 * @author yanava99
 */
@Stateless
@LocalBean
public class ClassesDAO {
    
    @PersistenceContext(unitName="TryingPU")
    private EntityManager em;
    
    public List<Classes> getClassesByGroupAndWeekday(Groups group, String weekday)
    {
        List<Classes> list = em.createNamedQuery("Classes.findByGroupAndWeekday", Classes.class).setParameter("groupInClasses", group).setParameter("weekday", weekday).getResultList();
        Collections.sort(list);
        return list;
    }
    
    public List<Classes> getClassesByAllButID(String subject, String teacher, String weekday, String timeOfClass, 
            int room, Groups groupInClasses)
    {
        return em.createNamedQuery("Classes.findByAllButID", Classes.class).setParameter("subject", subject).setParameter("teacher", teacher).setParameter("weekday", weekday).setParameter("timeOfClass", timeOfClass).setParameter("room", room).setParameter("groupInClasses", groupInClasses).getResultList();
    }
    
    @EJB
    private GroupDAO groupDAO;
    
    public boolean createClass(String subject, String teacher, String weekday, String timeOfClass, 
            int room, int groupInClassesYear, int groupInClassesNumber)
    {
        Classes classes = new Classes();
        classes.setRoom(room);
        classes.setSubject(subject);
        classes.setTeacher(teacher);
        classes.setTimeOfClass(timeOfClass);
        classes.setWeekday(weekday);
        Groups group = groupDAO.getGroupByYearAndNumber(groupInClassesYear, groupInClassesNumber);
        if(group == null)
            return false;
        classes.setGroupInClasses(group);
        em.persist(classes);
        
        List<Classes> list = getClassesByAllButID(subject, teacher, weekday, timeOfClass, room, group);
        return !list.isEmpty();
    }
    
    public String deleteClass(int groupYear, int groupNumber, String weekday, String timeOfClass)
    {
        Groups group = groupDAO.getGroupByYearAndNumber(groupYear, groupNumber);
        if(group == null)
        {
            return "There is no such class.";
        }
        List<Classes> list = em.createNamedQuery("Classes.findByGroupWeekdayAndTimeOfClass", Classes.class).setParameter("weekday", weekday).setParameter("timeOfClass", timeOfClass).setParameter("groupInClasses", group).getResultList();
        if(list.isEmpty())
        {
            return "There is no such class.";
        }
        Classes classes = list.get(0);
        if(!em.contains(classes))
        {
            classes = em.merge(classes);
        }
        em.remove(classes);
        list = em.createNamedQuery("Classes.findByGroupWeekdayAndTimeOfClass", Classes.class).setParameter("weekday", weekday).setParameter("timeOfClass", timeOfClass).setParameter("groupInClasses", group).getResultList();
        if(!list.isEmpty())
        {
            return "Success! Class was deleted.";
        }
        return "Error! Class wasn't deleted.";
    }
}
