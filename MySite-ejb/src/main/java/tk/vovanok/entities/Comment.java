package tk.vovanok.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vovan_000
 */
@Entity
@Table(name = "SHIPMENT_COMMENTS")
public class Comment extends BaseEntity{
       
    @Column(name = "FOR_SHIPMENT_BY_ID")
    private Long forShipmentId;
    
    @Column(name = "COMMENT_TEXT",length = 10000)
    private String text;
    
    @Column(name = "USER_NAME")
    private String userName;
    
    @Column(name = "USER_ID")
    private Long userId;
    
    @Column(name = "IP")
    private String ip;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_ADDED")
    private Date dateAdded;



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getForShipmentId() {
        return forShipmentId;
    }

    public void setForShipmentId(Long forShipmentId) {
        this.forShipmentId = forShipmentId;
    }

    
}
