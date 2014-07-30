/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
@Table(name = "INFO_ADM")
public class Information extends BaseEntity {

    @Column(name="TYPE") 
    private int type;
    
    @Column(name="TEXT",length = 12000)
    private String text;
    
    @Column(name="BASIC_INFO",length = 3000)
    private String basicText;
    
    @Column(name="TOPIC")
    private String topic;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getBasicText() {
        return basicText;
    }

    public void setBasicText(String basicText) {
        this.basicText = basicText;
    }
    
}
