package lk.ijse.Naturab.Bo.custom;

import lk.ijse.Naturab.Bo.SuperBo;
import lk.ijse.Naturab.Model.*;

import java.sql.SQLException;
import java.util.List;

public interface PlaceOrderBo extends SuperBo {
    public boolean saveOrder(OrderModel orderModel) throws SQLException, ClassNotFoundException;
    public boolean placeOrder(PlaceOrderModel po) throws SQLException;
    public boolean save(List<OrderDetailModel> odList) throws SQLException, ClassNotFoundException;
    public ClientModel ClientsearchById(String id) throws SQLException, ClassNotFoundException;
    public ProductModel ProductsearchById(String id) throws SQLException, ClassNotFoundException;
    public  List<String> getClientIds() throws SQLException, ClassNotFoundException;
    public List<String> getProductIds() throws SQLException, ClassNotFoundException;
    public String generateNewOrderID() throws SQLException, ClassNotFoundException;
}
