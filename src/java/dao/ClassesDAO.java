package dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Collections;

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
}
