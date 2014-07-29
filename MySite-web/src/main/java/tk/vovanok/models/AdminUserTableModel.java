/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.models;

import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import tk.vovanok.dao.UserDao;
import tk.vovanok.entities.User;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */


public class AdminUserTableModel extends LazyDataModel<User>{
    
    private UserDao userDao;

    private Long numOfRows;
    private int userId;
            
            
    List<User> datasource;
    
  
    
    public void updateRowCount(){
       
        numOfRows = getUserDao().countAll();
    }
    
    
    @Override
    public User getRowData(String rowKey) {
        for(User car : datasource) {  
            if(Long.toString(car.getId()).equals(rowKey))  
                return car;  
        }  
  
        return null;
    }

    @Override
    public Object getRowKey(User object) {
        return Long.toString(object.getId());
    }

    @Override
    public List<User> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        datasource = getUserDao().getUsers(first, pageSize, sortField, sortOrder.equals(SortOrder.ASCENDING)); 

        
        //rowCount  
        int dataSize = datasource.size();  
        this.setRowCount(dataSize);  
  
        //paginate  
        if(dataSize > pageSize) {  
            try {  
                return datasource.subList(first, first + pageSize);  
            }  
            catch(IndexOutOfBoundsException e) {  
                return datasource.subList(first, first + (dataSize % pageSize));  
            }  
        }  
        else {  
            return datasource;  
            
            
        }         
    }
    
    
    @Override
    public int getRowCount() {
        return numOfRows.intValue();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    
}
