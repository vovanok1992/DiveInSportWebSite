/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tk.vovanok.beans;

import tk.vovanok.cart.SessionCart;
import tk.vovanok.dao.CartItemDao;
import tk.vovanok.dao.OrderDao;
import tk.vovanok.entities.Order;
import tk.vovanok.entities.User;
import tk.vovanok.entities.commons.AdditionalParameter;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Vovan <vovanok1992 at gmail.com>
 */
@Named
@ViewScoped
public class OrderCartBean implements Serializable{

    @Inject
    OrderDao orderDao;

    @Inject
    CartItemDao cartItemDao;

    @Inject
    private LoginBean curUser;

    @Inject
    SessionCart cart;

    private List<String> phones;
    private List<String> emails;

    private String selectedPhone;
    private String info;
    private String deliveryType;
    private String payType;
    private String email;
    private String name;

    @PostConstruct
    public void init(){
        System.out.println("||| INIT |||");
        phones = new ArrayList<>();
        emails = new ArrayList<>();

        if(! curUser.isValid()) return;

        User u = curUser.getU();
        Collection<AdditionalParameter> params =  u.getAditionalInfo();
        for(AdditionalParameter par : params){
            if(par.getParam().equals("phone")) getPhones().add(par.getArgument());
            else if(par.getParam().equals("email")) getPhones().add(par.getArgument());
        }

        email = emails.isEmpty() ? "" : emails.get(0);
        selectedPhone = phones.isEmpty()?"":getPhones().get(0);

        cart.getItems();

    }

    public List<String> getPhones() {
        if(phones == null) init();
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    public String getSelectedPhone() {
        if(phones != null && phones.size() > 0) selectedPhone = phones.get(0);
        return selectedPhone;
    }

    public void setSelectedPhone(String selectedPhone) {
        this.selectedPhone = selectedPhone;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void makeOrder() {
        System.out.println("click");
        Order o = new Order();

        if(curUser!=null && curUser.getU() !=null){
            o.setUserId(curUser.getU().getId());
        }

        o.setName(name);
        o.setEmail(email);
        o.setPayType(payType);
        o.setDeliveryType(deliveryType);
        o.setPhone(selectedPhone);
        o.setCreated(new Date());
        o.setInfo(info);
        o.setCartItems(cart.getItems());


        System.out.println("RES:= " + o);
        cartItemDao.saveAll(o.getCartItems());
        orderDao.save(o);
        System.out.println("SAVE COMPLETE!!!!!" + o.getId());
    }
}

