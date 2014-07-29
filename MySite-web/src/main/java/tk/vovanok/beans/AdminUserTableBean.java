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
import org.primefaces.model.LazyDataModel;
import tk.vovanok.models.AdminUserTableModel;
import tk.vovanok.dao.UserDao;
import tk.vovanok.entities.User;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Named
@ViewScoped
public class AdminUserTableBean implements Serializable{

    @EJB
    private UserDao userDao;
    
    private LazyDataModel<User> lazyModel;
    private User selectedUser;

    public LazyDataModel<User> getLazyModel() {

        if (lazyModel == null) {
            lazyModel = new AdminUserTableModel();
            ((AdminUserTableModel)lazyModel).setUserDao(userDao);
            ((AdminUserTableModel)lazyModel).updateRowCount();
        }
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<User> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public User getSelectedUser() {
       
        return selectedUser;
    }

    public void setSelectedUser(User selectedComment) {
        this.selectedUser = selectedComment;
    }


    
}
