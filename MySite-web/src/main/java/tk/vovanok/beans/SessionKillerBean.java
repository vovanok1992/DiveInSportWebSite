/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.beans;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import tk.vovanok.system.Sessions;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Named
@RequestScoped
public class SessionKillerBean {
    
    private int sessionSize;
    
    public void killAll(){
        System.out.println("Killing all sessions");
        Sessions.invalidateAll();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(SessionKillerBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public int getSessionSize() {
        return Sessions.getSize();
    }


        
    
}
