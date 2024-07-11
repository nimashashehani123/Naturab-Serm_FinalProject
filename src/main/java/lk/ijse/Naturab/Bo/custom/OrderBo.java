package lk.ijse.Naturab.Bo.custom;

import lk.ijse.Naturab.Bo.SuperBo;
import lk.ijse.Naturab.Model.OrderModel;
import org.apache.commons.collections.OrderedBidiMap;

import java.sql.SQLException;
import java.util.List;

public interface OrderBo extends SuperBo {
    public List<OrderModel> getAllOrders() throws SQLException, ClassNotFoundException ;
    public boolean deleteOrder(String id) throws SQLException, ClassNotFoundException;
    public  boolean saveOrder(OrderModel orderModel) throws SQLException, ClassNotFoundException;
    public OrderModel OrdersearchById(String id) throws SQLException, ClassNotFoundException ;
    public boolean updateOrder( OrderModel orderModel) throws SQLException, ClassNotFoundException ;
    public String generateNewOrderID() throws SQLException, ClassNotFoundException;
    public int getOrdercount() throws SQLException, ClassNotFoundException;
    public  int getOrdercount1() throws SQLException, ClassNotFoundException;
    public  List<String> getOrderIds() throws SQLException, ClassNotFoundException;

    public boolean updateQty(String id, int qty) throws SQLException, ClassNotFoundException;

    public  String getEmail(String id) throws SQLException, ClassNotFoundException;
    public List<Double> getOrderPayment() throws SQLException, ClassNotFoundException;
    public List<String> getOId() throws SQLException, ClassNotFoundException;
}
