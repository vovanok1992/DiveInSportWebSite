/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.beans;


import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;
import tk.vovanok.entities.Category;
import tk.vovanok.entities.commons.CategoriesUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */

@Named
@RequestScoped
public class MainCategoriesBean implements Serializable{

    @EJB
    private CategoriesUtils categoriesUtils;

    private List<Category> cats;
    private Map<Long, List<Category>> childs;
    private MenuModel model;  
    

    @PostConstruct
    public void init(){
        model = new DefaultMenuModel();
        cats = new ArrayList<>();
        childs =new HashMap<>();

        List<Category> all = categoriesUtils.getAll();
        for(Category c: all){
            if (c.getParentId() == 0) {
                cats.add(c);
                List<Category> curr = new ArrayList<>();
                for(Category cs: all){
                    if(cs.getParentId().equals(c.getId())){
                        curr.add(cs);
                    }
                }
                childs.put(c.getId(), curr);
                DefaultMenuItem item = new DefaultMenuItem();
                item.setValue(c.getName());
                item.setUrl("/catalog.xhtml?categoryId="+c.getId());
                item.setId(c.getName());
                this.model.addElement(item);
            }
        }
    }
    
    public List<Category> getChildCats(Long c){
        return childs.get(c);
    }

    public MenuModel getModel() {  
         if(model==null) init();
        return model;  
     }     
    
    public List<Category> getCats() {
        return cats;
    }

    public void setCats(List<Category> cats) {
        this.cats = cats;
    }

}
