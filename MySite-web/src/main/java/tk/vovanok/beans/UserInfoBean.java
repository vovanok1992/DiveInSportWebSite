/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.beans;


import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import tk.vovanok.dao.UserDao;
import tk.vovanok.entities.User;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Named
@ViewScoped
public class UserInfoBean implements Serializable{
    
    @EJB
    private UserDao userDao;
    private Long id;
    private User u;

    private boolean exists = true;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getU() {
        if(exists && u==null){
            u = userDao.findById(id);
            exists = (u!=null);
        }
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }
}
