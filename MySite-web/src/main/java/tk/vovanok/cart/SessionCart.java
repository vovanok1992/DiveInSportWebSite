/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.cart;


import org.primefaces.context.RequestContext;
import tk.vovanok.beans.VarParam;
import tk.vovanok.commons.CurrencyUtils;
import tk.vovanok.commons.Variant;
import tk.vovanok.dao.ShipmentDao;
import tk.vovanok.entities.BasicCartItem;
import tk.vovanok.entities.Shipment;
import tk.vovanok.entities.commons.ShipmentParameter;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Named
@SessionScoped
public class SessionCart implements Serializable{
    @EJB
    private ShipmentDao shipmentDao;
    
    private List<BasicCartItem> items;
    private List<VarParam> parameters;
    private Shipment loaded;
    private int ammount = 1;
    private String deliveryType;
    private String payType;


    public void generateCartItem(){
        if(loaded == null) System.out.println("WTF");
        
        StringBuilder code = new StringBuilder(""+loaded.getId());
        StringBuilder desc = new StringBuilder(loaded.getName()).append(". ");
       
        for(VarParam par: parameters){
            code.append("#").append(par.getId()).append("-")
                    .append(par.getSelected());
     

            desc.append(par.getName()).append(" - ")
                    .append(par.getVariantValue(par.getSelected())).append(". ");
  
        }
        
        BasicCartItem item = new BasicCartItem();
        item.setName(loaded.getName());
        item.setShipmentId(loaded.getId().intValue());
        item.setPrice(CurrencyUtils.getPrice(loaded.getPrice(), loaded.getPriceCurrent()));
        item.setDescription(desc.toString());
        item.setCode(code.toString());
        item.setAmmount(ammount);
       
        
        addItem(item);
        
        System.out.println(code.toString());
        System.out.println(desc.toString());
        ammount = 1;
    }
    
    public void removeItem(BasicCartItem item){
        if(items != null && items.contains(item)) items.remove(item);
    }
    
    public void addItem(BasicCartItem item){
        if(items == null) items = new ArrayList<>();
        items.add(item);
        RequestContext.getCurrentInstance().update("cartcontent");
        System.out.println(items.size() + " asdasd ");
        
    }
    
    
    public void loadById(long id){
        
         if(loaded!=null && (loaded.getId() == id)) return;         
        
         System.out.println(" "+id);
         loaded = shipmentDao.findById(id);
         generateMenu(loaded);  
    }
    
    public void load(Shipment s){
        if(loaded!=null && (loaded.getId().equals(s.getId()))) return;
        loaded = s;
        generateMenu(s);

    }
    
    public void generateMenu(Shipment s){
         parameters = new ArrayList<>();
        
        for(ShipmentParameter ss: s.getParameters()){
            if(ss.isMulty()) {
                VarParam curr = new VarParam();
                curr.setId(ss.getIdNum());
                curr.setName(ss.getName());
                
                List<Variant> allParams = new ArrayList<>();
                String[] strParams = ss.getArgument().split(";");
                for(int i = 0; i < strParams.length; i++){
                    Variant v = new Variant();
                    v.setKey(i);
                    v.setValue(strParams[i]);
                    allParams.add(v);
                }
                curr.setValues(allParams);
                loaded = s;
                parameters.add(curr);
            }
        }
    }
    
    public void makeOrder(){
        
        System.out.println("ORDERING");
    }
    
    public List<VarParam> getParameters() {

        return parameters;
    }

    public void setParameters(List<VarParam> parameters) {
        this.parameters = parameters;
    }
    


    public List<BasicCartItem> getItems() {
        return items;
    }

    public void setItems(List<BasicCartItem> items) {
        this.items = items;
    }

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayType() {
        return payType;
    }
}
