/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.beans;

import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Named
@RequestScoped
public class SearchTextBoxBean implements Serializable{
    private String text;

    public void redirect() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("catalog.xhtml?search="+text);
    }
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
