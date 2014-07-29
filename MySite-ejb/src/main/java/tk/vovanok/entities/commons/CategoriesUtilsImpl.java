/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.entities.commons;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import tk.vovanok.dao.CategoryDao;
import tk.vovanok.entities.Category;

/**
 *
 * @author vovan_000
 */
@Singleton
public class CategoriesUtilsImpl implements CategoriesUtils{
    
    @EJB
    private CategoryDao categoryDao;
    
    List<Category> all;
    
    public  List<Category> getAll() {
        if (all == null) {
            all = categoryDao.findAll();
        }
        return all;
    }
    
    public void update(){
        all = categoryDao.findAll();
    }

    public List<Category> getParentItems(int cId){
        
        List<Category> result = new LinkedList<>();
        
        Category curr = null;
        for(Category cat: getAll()){
            if(cat.getId()==cId) {
                curr = cat;
                break;
            }
        }
        if(curr==null) return null;
        result.add(curr);

        while(curr.getParentId()!=0){
            for(Category tempCat:getAll()){
                if(tempCat.getId() == curr.getParentId()){
                    result.add(0,tempCat);
                    curr = tempCat;
                    break;
                }
            }
        }

        return result;
    }
    
    public List<Category> getChildWthCategories(int cId) {
        if(cId==0) return getAll();
        List<Category> result = new ArrayList<>();
        Category curr = null;
        for(Category cat : getAll()){
            if(cat.getId()==cId){
                curr = cat;
                break;
            }
        }

        result.add(curr);
        
        fill(curr, result, getAll());

        return result;
    }

    private void fill(Category curr, List<Category> result, List<Category> all) {
        for(int i = 0; i < all.size(); i++){
            if(all.get(i).getParentId().equals(curr.getId())){
                result.add(all.get(i));
                fill(all.get(i), result, all);
            }
        } 
        
    }
    
}
