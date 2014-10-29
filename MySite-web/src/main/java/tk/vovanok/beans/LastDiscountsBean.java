
package tk.vovanok.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import tk.vovanok.dao.DiscountDao;
import tk.vovanok.dao.ShipmentDao;
import tk.vovanok.entities.Discount;
import tk.vovanok.entities.Shipment;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Named
@ViewScoped
public class LastDiscountsBean implements Serializable{
    @EJB
    private ShipmentDao shipmentDao;
    @EJB
    private DiscountDao discountDao;
    
    
    
    private List<Shipment> ships;
    Map<Long, Discount> discs;
    
    int page = 0;
    int pageSize = 4;
    
    @PostConstruct
    public void init(){
        List<Discount> discList = discountDao.getActive(pageSize, page, true);
        discs = new HashMap<>();
        List<Long> shipsId = new ArrayList<>();
        for(Discount d : discList){
            shipsId.add(d.getShipmentId());
            discs.put(d.getShipmentId(), d);
        }
        if(shipsId.isEmpty()) return;
        ships = (shipmentDao.getIdIn(shipsId));
    }
    
    public Discount getDiscount(Long id){
        return discs.get(id);
    }

    public List<Shipment> getShips() {
        return ships;
    }

    public void setShips(List<Shipment> ships) {
        this.ships = ships;
    }
}
