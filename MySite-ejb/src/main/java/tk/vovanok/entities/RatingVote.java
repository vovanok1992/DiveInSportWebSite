package tk.vovanok.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "RATE_VOTES")
public class RatingVote extends BaseEntity{
    
    @Column(name = "CREATED")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date made;
    
    @Column(name = "USER_ID")
    private Long userId;
    
    @Column(name = "SHIPMENT_ID")
    private Long shipmentId;
    
    @Column(name = "MARK")
    private int mark;

    public Date getMade() {
        return made;
    }

    public void setMade(Date made) {
        this.made = made;
    }


    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }
    
}
