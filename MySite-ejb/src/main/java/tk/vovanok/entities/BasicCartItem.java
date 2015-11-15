/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.entities;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Entity
public class BasicCartItem extends BaseEntity{

    private String name;
    private String description;
    private String code;
    private BigDecimal price;
    private int shipmentId;
    private int ammount;

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BasicCartItem)) return false;
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

    @Override
    public String toString() {
        return "BasicCartItem{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", code='" + code + '\'' +
                ", price=" + price +
                ", shipmentId=" + shipmentId +
                ", ammount=" + ammount +
                '}';
    }
}
