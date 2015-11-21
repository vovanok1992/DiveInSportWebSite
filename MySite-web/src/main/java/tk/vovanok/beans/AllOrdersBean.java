package tk.vovanok.beans;

import org.primefaces.model.LazyDataModel;
import tk.vovanok.dao.OrderDao;
import tk.vovanok.entities.Order;
import tk.vovanok.models.AdminOrderTableModel;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by vovan_000 on 15.11.2015.
 */
@Named
@ViewScoped
public class AllOrdersBean implements Serializable{

    @EJB
    private OrderDao orderDao;

    private LazyDataModel<Order> lazyModel;
    private LazyDataModel<Order> lazyProcessing;
    private LazyDataModel<Order> lazyProcessed;
    private LazyDataModel<Order> lazyNew;

    private Order selectedOrder;

    @PostConstruct
    public void init(){
        lazyModel = new AdminOrderTableModel();
        ((AdminOrderTableModel)lazyModel).setOrderDao(orderDao);
        ((AdminOrderTableModel)lazyModel).updateRowCount();

        lazyProcessing = new AdminOrderTableModel();
        ((AdminOrderTableModel)lazyProcessing).setOrderDao(orderDao);
        ((AdminOrderTableModel)lazyProcessing).setStatus("processing");
        ((AdminOrderTableModel)lazyProcessing).updateRowCount();

        lazyProcessed = new AdminOrderTableModel();
        ((AdminOrderTableModel)lazyProcessed).setOrderDao(orderDao);
        ((AdminOrderTableModel)lazyProcessed).setStatus("processed");
        ((AdminOrderTableModel)lazyProcessed).updateRowCount();

        lazyNew = new AdminOrderTableModel();
        ((AdminOrderTableModel)lazyNew).setOrderDao(orderDao);
        ((AdminOrderTableModel)lazyNew).setStatus("new");
        ((AdminOrderTableModel)lazyNew).updateRowCount();
    }

    public void changeStatus(Long id, String status){
        Order order = orderDao.findById(id);
        order.setStatus(status);
        orderDao.update(order);
    }

    public LazyDataModel<Order> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<Order> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public Order getSelectedOrder() {

        return selectedOrder;
    }

    public void setSelectedOrder(Order selectedComment) {
        this.selectedOrder = selectedComment;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public LazyDataModel<Order> getLazyProcessing() {
        return lazyProcessing;
    }

    public void setLazyProcessing(LazyDataModel<Order> lazyProcessing) {
        this.lazyProcessing = lazyProcessing;
    }

    public LazyDataModel<Order> getLazyProcessed() {
        return lazyProcessed;
    }

    public void setLazyProcessed(LazyDataModel<Order> lazyProcessed) {
        this.lazyProcessed = lazyProcessed;
    }

    public LazyDataModel<Order> getLazyNew() {
        return lazyNew;
    }

    public void setLazyNew(LazyDataModel<Order> lazyNew) {
        this.lazyNew = lazyNew;
    }
}
