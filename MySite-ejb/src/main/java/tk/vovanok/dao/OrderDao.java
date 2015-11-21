package tk.vovanok.dao;

import tk.vovanok.entities.Order;

import javax.ejb.Local;
import java.util.List;

/**
 * Created by vovan_000 on 15.11.2015.
 */
@Local
public interface OrderDao extends GenericDao<Order, Long>{

     List<Order> getOrders(int starstRow, int pageSize, String orderField, boolean asc);

     List<Order> getOrders(int starstRow, int pageSize, String orderField, boolean asc, String status);

     Long countNewOrders();

}
