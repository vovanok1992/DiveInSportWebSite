
package tk.vovanok.dao;

import java.util.List;
import tk.vovanok.entities.Shipment;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
public interface ShipmentDao extends GenericDao<Shipment, Long>{

    public List<Shipment> search(String search, int pageSize, int i, int sortType);

    public Long countSearchResults(String search);

    public long getNuberOfShipments(int id);

    public List<Shipment> getBasicInfoList(int sortType, int id, int i, int pageSize);

    public List<Shipment> getLast(int i);

    public void removeAllByCategoryId(Long id);
    
    public List<Shipment> getIdIn(List<Long> list);
    
}
