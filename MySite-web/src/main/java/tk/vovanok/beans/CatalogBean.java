/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.beans;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import tk.vovanok.commons.CategorySortsTypes;
import tk.vovanok.dao.DiscountDao;
import tk.vovanok.dao.ShipmentDao;
import tk.vovanok.entities.Discount;
import tk.vovanok.entities.Shipment;

/**
 *
 * @author vovan_000
 */

@Named
@ViewScoped
public class CatalogBean implements Serializable{
    @EJB
    private ShipmentDao shipmentDao;
    @EJB
    private DiscountDao discountDao;
    
    List<Shipment> result ;
    private List<Integer> pages;
    Map<Long,Discount> discounts;
    
    private int id;
    private int page = 1;
    private int pageSize = 10;
    private int sortType = CategorySortsTypes.BY_DATE_DESC;
    private String search; 
    int allShipmentsCount;
    

    private String testStr;

    @PostConstruct
    public void init(){
        
    }
    
    public List<Shipment> getByCategoryId(){
        if(result==null) updateList();
        return result;
    }
    
    public Discount getDiscount(Long id){
        return discounts.get(id);
    }
    
    List<Long> getIdList(){
        List<Long> ids = new ArrayList<>();
            
            for(Shipment s: result){
                ids.add(s.getId());
            }
            
        return ids;
    }
    
    public void updateList(){
        if(search!=null){
            result = shipmentDao.search(search, pageSize, page-1, sortType);
            allShipmentsCount =  shipmentDao.countSearchResults(search).intValue();
            discounts = discountDao.getIdIn(getIdList());
            
            return;
        }
//        (int)
        allShipmentsCount =  (int) shipmentDao.getNuberOfShipments(id);
        result=shipmentDao.getBasicInfoList(sortType,id, getPage()-1, getPageSize());
        discounts = discountDao.getIdIn(getIdList());
           
            
    }
    
    public void deleteShipment(int id){
        shipmentDao.delete((long)id);
        updateList();
        RequestContext.getCurrentInstance().update("content"); 
    }
    
    public List<Integer> getPages() {
        if(pages!=null) return pages;
        else updatePages();
        return pages;
    }

    private void updatePages() {
        pages = new ArrayList<>();
        int lastPage = allShipmentsCount / pageSize + 1;
        
        if (allShipmentsCount <= pageSize * 5) {
            for(int i = 1; i < lastPage + 1; i++) pages.add(i); 
            return;
        }
        
        if (page < 4) {
            pages.add(1);
            pages.add(2);
            pages.add(3);
            pages.add(4);
            pages.add(-1);
            pages.add(lastPage);
            return;
        }
        
        if (page < lastPage-2) {
            pages.add(1);
            pages.add(-1);
            pages.add(page-1);
            pages.add(page);
            pages.add(page + 1);
            pages.add(-1);
            pages.add(lastPage);
            return;
        }
        
        pages.add(1);
        pages.add(-1);
        pages.add(lastPage-3);
        pages.add(lastPage-2);
        pages.add(lastPage-1);
        pages.add(lastPage);
        
    }

     public void handleChange() {  
        
        updateList();
        updatePages();
        RequestContext.getCurrentInstance().update(":ships");
        RequestContext.getCurrentInstance().execute("window.history.pushState('string', 'Title', '"+makeUrl()+"');");
        
    }  
    
    public String makeUrl(){
        String ser = (search == null) || search.equals("null") ? "&categoryId="+id : "&search="+search;
        
        return "catalog.xhtml?"+"&p="+page+"&sort="+sortType+ser;
    }
     
    /**
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(int page) {
        this.page = page;
        handleChange();
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return the sortType
     */
    public int getSortType() {
        return sortType;
    }

    /**
     * @param sortType the sortType to set
     */
    public void setSortType(int sortType) {
        this.sortType = sortType;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the testStr
     */
    public String getTestStr() {
        return testStr;
    }

    /**
     * @param testStr the testStr to set
     */
    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
