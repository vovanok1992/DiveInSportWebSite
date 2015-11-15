package tk.vovanok.beans;

import org.primefaces.model.LazyDataModel;
import tk.vovanok.dao.OrderDao;
import tk.vovanok.entities.Order;
import tk.vovanok.models.AdminOrderTableModel;

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
    private Order selectedOrder;

    public LazyDataModel<Order> getLazyModel() {

        if (lazyModel == null) {
            lazyModel = new AdminOrderTableModel();
            ((AdminOrderTableModel)lazyModel).setOrderDao(orderDao);
            ((AdminOrderTableModel)lazyModel).updateRowCount();
        }
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

}
