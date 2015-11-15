package tk.vovanok.models;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import tk.vovanok.dao.OrderDao;
import tk.vovanok.entities.Order;

import java.util.List;
import java.util.Map;

/**
 * Created by vovan_000 on 15.11.2015.
 */
public class AdminOrderTableModel extends LazyDataModel<Order> {

    private OrderDao orderDao;

    private Long numOfRows;
    private int userId;

    List<Order> datasource;

    public void updateRowCount(){
        numOfRows = getOrderDao().countAll();
    }

    @Override
    public Order getRowData(String rowKey) {
        for(Order car : datasource) {
            if(Long.toString(car.getId()).equals(rowKey))
                return car;
        }
        return null;
    }

    @Override
    public Object getRowKey(Order object) {
        return Long.toString(object.getId());
    }

    @Override
    public List<Order> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        datasource = getOrderDao().getOrders(first, pageSize, sortField, sortOrder.equals(SortOrder.ASCENDING));

        //rowCount
        int dataSize = datasource.size();
        this.setRowCount(dataSize);

        //paginate
        if(dataSize > pageSize) {
            try {
                return datasource.subList(first, first + pageSize);
            }
            catch(IndexOutOfBoundsException e) {
                return datasource.subList(first, first + (dataSize % pageSize));
            }
        }
        else {
            return datasource;
        }
    }

    @Override
    public int getRowCount() {
        return numOfRows.intValue();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

}