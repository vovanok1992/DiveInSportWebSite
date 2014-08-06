
package tk.vovanok.beans;

import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import tk.vovanok.dao.DiscountDao;
import tk.vovanok.entities.Discount;
import tk.vovanok.entities.User;
import tk.vovanok.models.AdminDiscountsTableModel;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Named
@ViewScoped
public class AdminDiscountTableBean implements Serializable{

    @EJB
    private DiscountDao discountDao;
    
    private LazyDataModel<Discount> lazyModel;
    private Discount selectedDiscount;
    
    
    private int percents;
    private String desc;
    private Date fin;
    private long shipId;
    
    public void save(){
        Discount d = new Discount();
        d.setDesc(desc);
        d.setCreated(new Date());
        d.setFinish(fin);
        d.setPercents(percents);
        d.setShipmentId(shipId);
        discountDao.save(d);
        System.out.println("fin");
    }

    public LazyDataModel<Discount> getLazyModel() {

        if (lazyModel == null) {
            lazyModel = new AdminDiscountsTableModel();
            ((AdminDiscountsTableModel)lazyModel).setDiscountDao(discountDao);
            ((AdminDiscountsTableModel)lazyModel).updateRowCount();
        }
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<Discount> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public Discount getSelectedDiscount() {
        return selectedDiscount;
    }

    public void setSelectedDiscount(Discount selectedDiscount) {
        this.selectedDiscount = selectedDiscount;
    }

    public int getPercents() {
        return percents;
    }

    public void setPercents(int percents) {
        this.percents = percents;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public long getShipId() {
        return shipId;
    }

    public void setShipId(long shipId) {
        this.shipId = shipId;
    }




    
}
