
package tk.vovanok.dao.implementation;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import tk.vovanok.dao.DiscountDao;
import tk.vovanok.dao.GenericJpaDao;
import tk.vovanok.entities.Discount;
import tk.vovanok.entities.User;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Stateless
public class DiscountDaoImpl extends GenericJpaDao<Discount, Long>
                                implements DiscountDao{

    @Override
    public Discount getForShipment(Long shipId) {
        Session s = getEntityManager().unwrap(Session.class);
        
        Criteria c = s.createCriteria(Discount.class);
        c.add(Restrictions.eq("shipmentId", shipId));
        c.add(Restrictions.gt("finish", new Date()));
        
        return (Discount) c.uniqueResult();
    }
    
    @Override
    public Map<Long, Discount> getIdIn(List<Long> list){
        Session s = getEntityManager().unwrap(Session.class);
        
        Criteria c = s.createCriteria(Discount.class);
        c.add(Restrictions.gt("finish", new Date()));
        c.add(Restrictions.in("shipmentId", list));
        
        List<Discount> discs = c.list();
        Map<Long,Discount> res =new HashMap<>();
        for(Discount d : discs){
            res.put(d.getShipmentId(), d);
        }
        
        return res;        
    }

    @Override
    public List<Discount> getActive(int limit, int page, boolean asc) {
        Session s = getEntityManager().unwrap(Session.class);
        
        Criteria c = s.createCriteria(Discount.class);
        c.add(Restrictions.gt("finish", new Date()));
        c.setMaxResults(limit);
        c.setFirstResult(page*limit);
        
        return c.list();
    }
    
    @Override
    public List<Discount> getDiscounts(int starstRow, int pageSize, String orderField, boolean asc) {
        List<Discount> result = null;
        
        Session session = getEntityManager().unwrap(Session.class);
        
        Criteria c = session.createCriteria(Discount.class);
        
        c.setFirstResult(starstRow);
        c.setMaxResults(pageSize);
        
        if(orderField!=null){
            if(asc) c.addOrder(Order.asc(orderField));
            else c.addOrder(Order.desc(orderField));
        }
        
        result = (List<Discount>) c.list();
        return result;
    }
    
}
