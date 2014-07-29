/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import tk.vovanok.entities.User;
import tk.vovanok.entities.commons.AditionalParameter;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Named
@ViewScoped
public class OrderCartBean implements Serializable{
    private List<String> phones;
    
    private String selectedPhone;
    private String info;

    @Inject
    private LoginBean curUser;
    
    public void init(){
        phones = new ArrayList<>();
        if(! curUser.isValid()) return;
        User u = curUser.getU();
        Collection<AditionalParameter> params =  u.getAditionalInfo();
        for(AditionalParameter par : params){
            if(par.getParam().equals("phone")) getPhones().add(par.getArgument());
        }
    }

    public List<String> getPhones() {
        if(phones == null) init();
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public String getSelectedPhone() {
        if(phones != null && phones.size() > 0) selectedPhone = phones.get(0);
        return selectedPhone;
    }

    public void setSelectedPhone(String selectedPhone) {
        this.selectedPhone = selectedPhone;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    
    
}
