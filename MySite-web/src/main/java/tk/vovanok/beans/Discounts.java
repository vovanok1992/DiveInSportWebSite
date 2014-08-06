
package tk.vovanok.beans;

import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import tk.vovanok.dao.DiscountDao;
import tk.vovanok.entities.Discount;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Named
@RequestScoped
public class Discounts {
    @EJB
    private DiscountDao discountDaoImpl;
    
    Map<Long, Discount> discs = new HashMap<>();
    
    public boolean exits(Long id){
        discs.put(id, discountDaoImpl.getForShipment(id));
        return discs.get(id) != null;
    }
    
    public Discount getDisc(Long id){
        return discs.get(id);
    }    
    
    
}
