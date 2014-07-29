/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.commons;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author vovan_000
 */
public class CurrencyUtils {
    
    public String getStatic(int unit){
        switch(unit){
            case 0:
            case 1: return "10";
            case 2: return "15";
            case 3: return "0.4";
            default: return "1";
        } 
    }

//    $ - USD=1
//    € - EUR=2
//    Р - RUB=3
//    ₴ - UAH=4
    
//    Статический itemValue="1"
//    Динамический itemValue="2"
//    Локальный(свой) itemValue="3"
    
    public BigDecimal convert(int from, int to, BigDecimal curPrice, BigDecimal localCurrency, int currencyType){
        BigDecimal result = curPrice;
            if(from == to){
                 return result; 
            } else {
                
                BigDecimal priceUan = curPrice.multiply(getOfficalCurrency(from));
                result = priceUan.divide(getOfficalCurrency(to), 2, RoundingMode.HALF_UP);
            }
            
        
        return result;
    }
    
    public String getDinamic(int unit){
//        System.out.println("UNIT: "+unit);
        return getStatic(unit);
    }
    
    public static BigDecimal getPrice(BigDecimal val, BigDecimal current){
        return val;
    }

    private BigDecimal getOfficalCurrency(int to) {
        return new BigDecimal(getDinamic(to));         
    }
    
    
}
