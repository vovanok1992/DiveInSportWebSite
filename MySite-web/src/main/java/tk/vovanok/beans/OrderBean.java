package tk.vovanok.beans;

import tk.vovanok.dao.OrderDao;
import tk.vovanok.entities.Order;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by vovan_000 on 15.11.2015.
 */
@Named
@RequestScoped
public class OrderBean {

    @Inject
    OrderDao orderDao;

    private Order order;
    private String id;

    public void init(){
        order = getOrderById(id);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ваш заказ принят.", " В ближайшее время с Вами свяжутся."));
    }

    public Order getOrderById(String id){
        Long idLong = Long.parseLong(id);
        Order order = orderDao.findById(idLong);
        return order;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
