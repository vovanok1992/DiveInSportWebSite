/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.cart;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import javax.inject.Named;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */

public class BasicCartItem implements ShopItem{

    private String name;
    private String description;
    private String code;
    private BigDecimal price;
    private int shipmentId;
    private int ammount;
    private Map<Integer,Integer> selectedParams;
    
    
    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object obj) {
        if(! (obj instanceof BasicCartItem)) return false;
        BasicCartItem item = (BasicCartItem) obj;
        return code.equals(item.code) && (ammount == item.ammount);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.code);
        hash = 29 * hash + this.ammount;
        return hash;
    }
    
    

    public void setName(String name) {
        this.name = name;
    }


    public void setDescription(String desc) {
        this.description = desc;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }



    public int getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(int shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Map<Integer,Integer> getSelectedParams() {
        return selectedParams;
    }

    public void setSelectedParams(Map<Integer,Integer> selectedParams) {
        this.selectedParams = selectedParams;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }

    
    
}
