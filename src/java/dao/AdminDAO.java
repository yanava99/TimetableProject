package dao;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Admins;

/**
 *
 * @author yanava99
 */
@Stateless
@LocalBean
public class AdminDAO {
    
    @PersistenceContext(unitName="TryingPU")
    private EntityManager em;
    
    public boolean verify(String idADMINS, String password)
    {
        List<Admins> list = em.createNamedQuery("Admins.findByIdADMINS", Admins.class).setParameter("idADMINS", idADMINS).getResultList();
        
        if(list.isEmpty())
            return false;
        Admins admin = (Admins)list.get(0);
        return (admin != null || password.equals(admin.getPassword()));
    }
    
    public Admins createAdminsObject(String id)
    {
        return em.find(Admins.class, id);
    }
}
