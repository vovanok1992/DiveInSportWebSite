package tk.vovanok.models;

import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import tk.vovanok.dao.InformationDao;
import tk.vovanok.entities.Information;
/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */

public class NewsTableModel extends LazyDataModel<Information>{
    
    private InformationDao informationDao;
    private Long numOfRows;

    List<Information> datasource;
    
    public void updateRowCount(){
       
        numOfRows = getInformationDao().countAll();
    }
    
    
    @Override
    public Information getRowData(String rowKey) {
        for(Information car : datasource) {  
            if(Long.toString(car.getId()).equals(rowKey))  
                return car;  
        }  
  
        return null;
    }

    @Override
    public Object getRowKey(Information object) {
        return Long.toString(object.getId());
    }

    @Override
    public List<Information> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        datasource = informationDao.get(first, pageSize, sortField, sortOrder.equals(SortOrder.ASCENDING)); 
 
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

    public InformationDao getInformationDao() {
        return informationDao;
    }

    public void setInformationDao(InformationDao informationDao) {
        this.informationDao = informationDao;
    }




    
}
