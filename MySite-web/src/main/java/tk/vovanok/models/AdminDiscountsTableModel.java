/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.models;

import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import tk.vovanok.dao.DiscountDao;
import tk.vovanok.entities.Discount;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */


public class AdminDiscountsTableModel extends LazyDataModel<Discount>{
    
    private DiscountDao discountDao;

    private Long numOfRows;
    private int userId;
            
            
    List<Discount> datasource;
    
  
    
    public void updateRowCount(){
       
        numOfRows = discountDao.countAll();
    }
    
    
    @Override
    public Discount getRowData(String rowKey) {
        for(Discount car : datasource) {  
            if(Long.toString(car.getId()).equals(rowKey))  
                return car;  
        }  
  
        return null;
    }

    @Override
    public Object getRowKey(Discount object) {
        return Long.toString(object.getId());
    }

    @Override
    public List<Discount> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        datasource = discountDao.getDiscounts(first, pageSize, sortField, sortOrder.equals(SortOrder.ASCENDING)); 

        
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

    public DiscountDao getDiscountDao() {
        return discountDao;
    }

    public void setDiscountDao(DiscountDao discountDao) {
        this.discountDao = discountDao;
    }

    
}
