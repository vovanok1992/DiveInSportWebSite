package tk.vovanok.beans;

import tk.vovanok.dao.OrderDao;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by vovan_000 on 21.11.2015.
 */
@Named
@RequestScoped
public class AdminNotificationsBean {

    @Inject
    OrderDao orderDao;

    private long newOrders;

    @PostConstruct
    public void init(){
        newOrders = orderDao.countNewOrders();
    }

    public long getNewOrders() {
        return newOrders;
    }

    public void setNewOrders(long newOrders) {
        this.newOrders = newOrders;
    }
}
