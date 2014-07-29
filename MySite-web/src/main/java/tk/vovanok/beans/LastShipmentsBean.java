/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.beans;


import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import tk.vovanok.dao.ShipmentDao;
import tk.vovanok.entities.Shipment;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Named
@ViewScoped
public class LastShipmentsBean implements Serializable{
    
    @EJB
    private ShipmentDao shipmentDao;

    private List<Shipment> lastShipments;

    void init(){
        lastShipments = shipmentDao.getLast(15);
    }
    
    public List<Shipment> getLastShipments() {
        if(lastShipments == null) init();
        return lastShipments;
    }

    public void setLastShipments(List<Shipment> lastShipments) {
        this.lastShipments = lastShipments;
    }
    
    
}
