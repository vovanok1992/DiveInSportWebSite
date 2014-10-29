/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.beans;

import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import tk.vovanok.dao.InformationDao;
import tk.vovanok.entities.Information;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Named
@ViewScoped
public class NewsEditorBean implements Serializable{
    @EJB
    private InformationDao informationDao;
    private String text;
    private String topic;
    private String basicText;
    

    public void save(){
        Information inf = new Information();
        inf.setType(2);
        inf.setTopic(topic);
        inf.setText(text);
        inf.setBasicText(basicText);
        inf.setCreated(new Date());
        informationDao.save(inf);
        System.out.println("saved");
        
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
