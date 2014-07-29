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
import tk.vovanok.dao.CommentDao;
import tk.vovanok.entities.Comment;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
public class AdminCommentsEditorModel extends LazyDataModel<Comment>{
  
    private CommentDao commentDao;

    private int numOfRows;
    private Long userId;
            
            
    List<Comment> datasource;
    
    public AdminCommentsEditorModel(){
    }
    
    public void updateRowCount(){
        numOfRows = getCommentDao().getNumberOfCommentsForUser(userId).intValue();
    }
    
    
    @Override
    public Comment getRowData(String rowKey) {
        for(Comment car : datasource) {  
            if(Long.toString(car.getId()).equals(rowKey))  
                return car;  
        }  
  
        return null;
    }

    @Override
    public Object getRowKey(Comment object) {
        return Long.toString(object.getId());
    }

    @Override
    public List<Comment> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        datasource = getCommentDao().getCommentsByUserId(userId, first, pageSize, sortField, sortOrder.equals(SortOrder.ASCENDING)); 

        
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
        return numOfRows;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public CommentDao getCommentDao() {
        return commentDao;
    }

    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    
}
