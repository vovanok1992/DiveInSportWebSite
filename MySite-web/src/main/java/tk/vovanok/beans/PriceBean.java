/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.beans;


import java.io.Serializable;
import java.math.BigDecimal;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import tk.vovanok.commons.CurrencyUtils;
import tk.vovanok.entities.Shipment;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Named
@ViewScoped
public class PriceBean implements Serializable{
    
    @Inject
    LoginBean user;
    
    CurrencyUtils currUtils = new CurrencyUtils();
    
//    $ - USD=1
//    € - EUR=2
//    Р - RUB=3
//    ₴ - UAH=4
    
//    Статический itemValue="1"
//    Динамический itemValue="2"
//    Локальный(свой) itemValue="3"
    
    public String getPrice(Shipment s){

        int piceType = s.getPriceUnit();
        int priceUnit = s.getPriceCurrent().equals(new BigDecimal("0")) ? 1 :
                        s.getPriceCurrent().equals(new BigDecimal("-1")) ? 2 :
                        3;
        
        int need = user.getPriceType();
        String result = currUtils.convert(piceType, 
                                     need, 
                                     s.getPrice(), 
                                     s.getPriceCurrent(), 
                                     priceUnit).toString();
        
        
            return result+getPriceTag(need);
    }
    
    public String getPriceTag(int type){
        switch(type){
            case 0 :
            case 1 : return "$";
            case 2 : return "€";
            case 3 : return "Р";
            case 4 : return "₴";
        }
        return "?";
    }
}
