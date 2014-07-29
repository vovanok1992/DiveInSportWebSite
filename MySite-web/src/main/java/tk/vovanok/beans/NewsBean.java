/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.beans;


import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import tk.vovanok.dao.InformationDao;
import tk.vovanok.entities.Information;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Named
@RequestScoped
public class NewsBean {
    @EJB
    private InformationDao informationDaoImpl;
    
    private List<Information> lastNews;
    
    

    public List<Information> getLastNews() {
        if(lastNews == null) {
          
            lastNews = informationDaoImpl.getLast(3);
        }
        return lastNews;
    }

    public void setLastNews(List<Information> lastNews) {
        this.lastNews = lastNews;
    }
    
    
}
