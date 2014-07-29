
package tk.vovanok.beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
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
public class ReadNewBean implements Serializable{
    
    private Long id;
    
    private Information curr;
    
    @EJB
    private InformationDao dao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Information getCurr() {
        if(curr == null){
            try {
                curr = dao.findById(id);
                if(curr == null) FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(ReadNewBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return curr;
    }

    public void setCurr(Information curr) {
        this.curr = curr;
    }

    public InformationDao getDao() {
        return dao;
    }

    public void setDao(InformationDao dao) {
        this.dao = dao;
    }
    
    
    
    
}
