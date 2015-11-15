package tk.vovanok.dao.implementation;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import tk.vovanok.dao.GenericJpaDao;
import tk.vovanok.dao.OrderDao;
import tk.vovanok.entities.Order;
import tk.vovanok.entities.User;

import javax.ejb.Stateless;
import java.util.List;

/**
 * Created by vovan_000 on 15.11.2015.
 */
@Stateless
public class OrderDaoImpl extends GenericJpaDao<Order, Long>   implements OrderDao{

    @Override
    public Order findById(Long aLong) {
        Order o = super.findById(aLong);
        Hibernate.initialize(o.getCartItems());
        return o;
    }

    @Override
    public List<Order> getOrders(int starstRow, int pageSize, String orderField, boolean asc) {
        List<Order> result = null;

        Session session = getEntityManager().unwrap(Session.class);

        Criteria c = session.createCriteria(Order.class);

        c.setFirstResult(starstRow);
        c.setMaxResults(pageSize);

        if(orderField!=null){
            if(asc) c.addOrder(org.hibernate.criterion.Order.asc(orderField));
            else c.addOrder(org.hibernate.criterion.Order.desc(orderField));
        }

        result = (List<Order>) c.list();
        return result;
    }
}
