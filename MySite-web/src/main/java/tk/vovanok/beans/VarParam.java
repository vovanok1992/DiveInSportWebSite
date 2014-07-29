/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import tk.vovanok.commons.Variant;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Named

public class VarParam implements Serializable {
    
    private String name;
    private int id;
    private int selected;
    private String desc;
    private List<Variant> values;
    
    public String getVariantValue(int id){
        for(Variant v : values){
            if(v.getKey() == id) return v.getValue();
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<Variant> getValues() {
        return values;
    }

    public void setValues(List<Variant> values) {
        this.values = values;
    }


    
    
}
