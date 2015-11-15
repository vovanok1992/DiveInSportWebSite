package tk.vovanok.dao;

import tk.vovanok.entities.BasicCartItem;

import javax.ejb.Local;
import java.util.Collection;

/**
 * Created by vovan_000 on 15.11.2015.
 */
@Local
public interface CartItemDao extends GenericDao<BasicCartItem, Long>{

    void saveAll(Collection<BasicCartItem> items);

}
