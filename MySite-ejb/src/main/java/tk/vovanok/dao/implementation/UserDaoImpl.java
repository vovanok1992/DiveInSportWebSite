
package tk.vovanok.dao.implementation;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import tk.vovanok.dao.GenericJpaDao;
import tk.vovanok.dao.UserDao;
import tk.vovanok.entities.User;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Stateless
public class UserDaoImpl extends GenericJpaDao<User, Long> 
                         implements UserDao{

    @Override
    public boolean checkAvailable(String userName) {
        if(userName == null) return false;
        
        Query q = getEntityManager().createQuery("select count(*) from "
                                                    +getPersistantClass().getSimpleName()
                                                    +" u where u.login = :userName")
                                                    .setParameter("userName", userName);
        
        Long count =(Long) q.getSingleResult();
        
        return count > 0;
    }

    @Override
    public User loadByUserName(String userName) {
        if(userName == null || userName.trim().equals("")) return null;
        
        Query q = getEntityManager().createQuery("select u from "+getPersistantClass().getSimpleName()
                                                    + " u where u.login = :userName").setParameter("userName", userName);
        
        User u = null;
      
        try {
            u = (User) q.getSingleResult();
            Hibernate.initialize(u.getAditionalInfo());
        } catch (NoResultException e) {
            //nop
        }

        return u;
    }
    
    @Override
    public User findById(Long id) {
       Session session = getEntityManager().unwrap(Session.class);
       
       User u =(User) session.get(User.class, id);
       Hibernate.initialize(u.getAditionalInfo());
       
       return u; //To change body of generated methods, choose Tools | Templates.
    }

    

    @Override
    public List<User> getUsers(int starstRow, int pageSize, String orderField, boolean asc) {
        List<User> result = null;
        
        Session session = getEntityManager().unwrap(Session.class);
        
        Criteria c = session.createCriteria(User.class);
        
        c.setFirstResult(starstRow);
        c.setMaxResults(pageSize);
        
        if(orderField!=null){
            if(asc) c.addOrder(Order.asc(orderField));
            else c.addOrder(Order.desc(orderField));
        }
        
        result = (List<User>) c.list();
        return result;
    }
    
}
