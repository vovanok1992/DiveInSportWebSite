
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
public class AdminNewsTableModel extends LazyDataModel<Information> {

    private InformationDao informationDao;
    
    private Long numOfRows;
    
    private List<Information> datasource;
    
    public void updateRowCount(){
        numOfRows = informationDao.countAll();
    }
     
    @Override
    public Information getRowData(String rowKey) {
        for(Information car : getDatasource()) {  
            if(Long.toString(car.getId()).equals(rowKey))  
                return car;  
        }  
  
        return null;
    }
    
    @Override
    public List<Information> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        setDatasource(informationDao.get(first, pageSize, sortField, sortOrder.equals(SortOrder.ASCENDING))); 

        
        //rowCount  
        int dataSize = getDatasource().size();  
        this.setRowCount(dataSize);  
  
        //paginate  
        if(dataSize > pageSize) {  
            try {  
                return getDatasource().subList(first, first + pageSize);  
            }  
            catch(IndexOutOfBoundsException e) {  
                return getDatasource().subList(first, first + (dataSize % pageSize));  
            }  
        }  
        else {  
            return getDatasource();  
            
            
        }         
    }
    
    @Override
    public Object getRowKey(Information object) {
        return Long.toString(object.getId());
    }

    @Override
    public int getRowCount() {
        if(numOfRows == null) updateRowCount();
        return numOfRows.intValue();
    }
    

    public InformationDao getInformationDao() {
        return informationDao;
    }

    public void setInformationDao(InformationDao informationDao) {
        this.informationDao = informationDao;
    }

    public List<Information> getDatasource() {
        return datasource;
    }

    public void setDatasource(List<Information> datasource) {
        this.datasource = datasource;
    }
    

    
}
