package tk.vovanok.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by vovan_000 on 21.03.2015.
 */
@Entity
@Table(name = "ORDERS")
public class Order extends BaseEntity {

    private Long userId;
    private String name;
    private String email;
    private String phone;
    private String payType;
    private String deliveryType;
    private String info;

    @OneToMany
    private List<BasicCartItem> cartItems;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    @Override
    public String toString() {
        return "Order{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", payType='" + payType + '\'' +
                ", deliveryType='" + deliveryType + '\'' +
                ", cartItems=" + cartItems +
                '}';
    }

    public List<BasicCartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<BasicCartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
