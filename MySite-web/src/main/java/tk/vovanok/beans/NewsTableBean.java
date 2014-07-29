
package tk.vovanok.beans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import tk.vovanok.dao.InformationDao;
import tk.vovanok.entities.Information;
import tk.vovanok.models.AdminNewsTableModel;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Named
@ViewScoped
public class NewsTableBean implements Serializable{
    @EJB
    private InformationDao informationDao;
    
    private AdminNewsTableModel model;
    
    private List<Information> allTopics;
    
    private Information selected;
    
    @PostConstruct
    public void init(){
        allTopics = informationDao.findAll();
        model = new AdminNewsTableModel();
        model.setInformationDao(informationDao);
    }
    
    public void updateInfo(){
        informationDao.update(selected);
    }
    
    public void delete(){
        informationDao.delete(selected);
    }

    public List<Information> getAllTopics() {
        return allTopics;
    }

    public void setAllTopics(List<Information> allTopics) {
        this.allTopics = allTopics;
    }

    public Information getSelected() {
        return selected;
    }

    public void setSelected(Information selected) {
        this.selected = selected;
    }

    public AdminNewsTableModel getModel() {
        return model;
    }

    public void setModel(AdminNewsTableModel model) {
        this.model = model;
    }
    
            




    
}