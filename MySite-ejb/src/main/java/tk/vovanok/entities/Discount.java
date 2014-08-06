
package tk.vovanok.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Entity
@Table
public class Discount extends BaseEntity{
    
    @Column(name = "DATE_EXPIRED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finish;
    @Column(name = "FOR_SHIPMENT")
    private Long shipmentId;
    @Column(name = "DESCRIPTIN")
    private String desc;
    @Column(name = "DISCOUNT")
    private int percents;

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPercents() {
        return percents;
    }

    public void setPercents(int percents) {
        this.percents = percents;
    }
    
}
