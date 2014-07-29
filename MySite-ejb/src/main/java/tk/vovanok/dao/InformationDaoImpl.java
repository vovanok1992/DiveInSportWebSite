
package tk.vovanok.dao;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import tk.vovanok.entities.Information;
import tk.vovanok.entities.User;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Stateless
public class InformationDaoImpl extends GenericJpaDao<Information, Long>
                                implements InformationDao{


    
    @Override
    public List<Information> getLast(int amount) {
        Query q = getEntityManager().createQuery("select i from Information i order by i.created desc");
        q.setFirstResult(0);
        q.setMaxResults(amount);
        return q.getResultList();
    }

    @Override
    public List<Information> get(int starstRow, int pageSize, String orderField, boolean asc) {
        List<Information> result = null;
        
        Session session = getEntityManager().unwrap(Session.class);
        
        Criteria c = session.createCriteria(Information.class);
        
        c.setFirstResult(starstRow);
        c.setMaxResults(pageSize);
        
        if(orderField!=null){
            if(asc) c.addOrder(Order.asc(orderField));
            else c.addOrder(Order.desc(orderField));
        }
        
        result = (List<Information>) c.list();
        return result;
    }
    
}
