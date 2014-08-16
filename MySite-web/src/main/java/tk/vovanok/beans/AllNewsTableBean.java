/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.beans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
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
public class AllNewsTableBean implements Serializable{

    int page = 0;
    int pageSize = 10;
    private boolean sortAsc = true;
    
    @EJB
    private InformationDao informationDao;
    
    List<Information> currentNews;
    
    @PostConstruct
    public void init(){
        update();
    }
    
    public void update(){
        currentNews = informationDao.get(page, pageSize, "created", isSortAsc());
    }
    
    
    public List<Information> getPage(){
        return currentNews;
    }

    public boolean isSortAsc() {
        return sortAsc;
    }

    public void setSortAsc(boolean sortAsc) {
        this.sortAsc = sortAsc;
    }

}
