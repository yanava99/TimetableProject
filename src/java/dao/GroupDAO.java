package dao;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Groups;

/**
 *
 * @author yanava99
 */
@Stateless
@LocalBean
public class GroupDAO {
    
    @PersistenceContext(unitName="TryingPU")
    private EntityManager em;
    
    public Groups getGroupByYearAndNumber(int yearInGroups, int number)
    {
        List<Groups> list = em.createNamedQuery("Groups.findByYearAndNumber", Groups.class).setParameter("yearInGroups", yearInGroups).setParameter("number", number).getResultList();
        if(list.isEmpty())
            return null;
        return list.get(0);
    }
    
}
