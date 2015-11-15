package tk.vovanok.dao.implementation;

import tk.vovanok.dao.CartItemDao;
import tk.vovanok.dao.GenericJpaDao;
import tk.vovanok.entities.BasicCartItem;

import javax.ejb.Stateless;
import java.util.Collection;
import java.util.Date;

/**
 * Created by vovan_000 on 15.11.2015.
 */
@Stateless
public class CartItemDaoImpl extends GenericJpaDao<BasicCartItem,Long> implements CartItemDao {

    @Override
    public void saveAll(Collection<BasicCartItem> items) {
        for (BasicCartItem cartItem: items){
            if(cartItem.getCreated()==null){
                cartItem.setCreated(new Date());
            }
            getEntityManager().persist(cartItem);
        }
    }
}
