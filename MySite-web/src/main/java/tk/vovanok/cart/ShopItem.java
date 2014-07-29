/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.cart;

import java.math.BigDecimal;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
public interface ShopItem {
    
    BigDecimal getPrice();
    
    String getName();
    
    String getDescription();
    
}
