
package tk.vovanok.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import tk.vovanok.entities.Discount;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
public interface DiscountDao extends GenericDao<Discount, Long>{
    
    Discount getForShipment(Long shipId);
    
    List<Discount> getActive(int limit, int page, boolean asc);
    
    public Map<Long, Discount> getIdIn(List<Long> list);
    public List<Discount> getDiscounts(int starstRow, int pageSize, String orderField, boolean asc);


}
