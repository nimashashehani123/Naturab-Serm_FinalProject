package lk.ijse.Naturab.Bo.custom.Impl;

import lk.ijse.Naturab.Bo.custom.OrderBo;
import lk.ijse.Naturab.Dao.DAOFactory;
import lk.ijse.Naturab.Dao.custom.OrderDAO;
import lk.ijse.Naturab.Model.ClientModel;
import lk.ijse.Naturab.Model.OrderModel;
import org.apache.commons.collections.OrderedBidiMap;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderBoImpl implements OrderBo {

    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    @Override
    public List<OrderModel> getAllOrders() throws SQLException, ClassNotFoundException {
        List<OrderModel> allOrders = orderDAO.getAll();
        ArrayList<OrderModel> orderModels = new ArrayList<>();
        for(OrderModel orderModel : allOrders){
            OrderModel orderModel1 = new OrderModel(orderModel.getOId(),orderModel.getPlacedDate(),orderModel.getPaymentAmount(),orderModel.getStatus(),orderModel.getCId());
            orderModels.add(orderModel1);
        }
        return orderModels;
    }

    @Override
    public boolean deleteOrder(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.delete(id);
    }

    @Override
    public boolean saveOrder(OrderModel orderModel) throws SQLException, ClassNotFoundException {
        return orderDAO.save(orderModel);
    }

    @Override
    public OrderModel OrdersearchById(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.searchById(id);
    }

    @Override
    public boolean updateOrder(OrderModel orderModel) throws SQLException, ClassNotFoundException {
        return orderDAO.update(orderModel);
    }

    @Override
    public String generateNewOrderID() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewID();
    }

    @Override
    public int getOrdercount() throws SQLException, ClassNotFoundException {
        return orderDAO.getcount();
    }

    @Override
    public int getOrdercount1() throws SQLException, ClassNotFoundException {
        return orderDAO.getcount1();
    }

    @Override
    public List<String> getOrderIds() throws SQLException, ClassNotFoundException {
        return orderDAO.getIds();
    }

    @Override
    public boolean updateQty(String id, int qty) throws SQLException, ClassNotFoundException {
        return orderDAO.updateQty(id,qty);
    }

    @Override
    public String getEmail(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.getEmail(id);
    }

    @Override
    public List<Double> getOrderPayment() throws SQLException, ClassNotFoundException {
        return orderDAO.getPayment();
    }

    @Override
    public List<String> getOId() throws SQLException, ClassNotFoundException {
        return orderDAO.getOId();
    }
}
