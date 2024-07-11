package lk.ijse.Naturab.Bo.custom.Impl;

import lk.ijse.Naturab.Bo.custom.PlaceOrderBo;
import lk.ijse.Naturab.Dao.DAOFactory;
import lk.ijse.Naturab.Dao.custom.ClientDAO;
import lk.ijse.Naturab.Dao.custom.OrderDAO;
import lk.ijse.Naturab.Dao.custom.OrderDetailDAO;
import lk.ijse.Naturab.Dao.custom.ProductDAO;
import lk.ijse.Naturab.Db.DbConnection;
import lk.ijse.Naturab.Model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PlaceorderBoImpl implements PlaceOrderBo {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAIL);
    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT);
    ClientDAO clientDAO = (ClientDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CLIENT);
    @Override
    public ClientModel ClientsearchById(String id) throws SQLException, ClassNotFoundException {
        return clientDAO.searchById(id);
    }
    @Override
    public boolean saveOrder(OrderModel orderModel) throws SQLException, ClassNotFoundException {
        return orderDAO.save(orderModel);
    }
    @Override
    public List<String> getClientIds() throws SQLException, ClassNotFoundException {
        return clientDAO.getIds();
    }
    @Override
    public ProductModel ProductsearchById(String id) throws SQLException, ClassNotFoundException {
        return productDAO.searchById(id);
    }
    public boolean save(List<OrderDetailModel> odList) throws SQLException, ClassNotFoundException {
        for (OrderDetailModel od : odList) {
            boolean isSaved = orderDetailDAO.save(od);
            if(!isSaved) {
                return false;
            }
        }
        return true;
    }
    public boolean update(List<OrderDetailModel> odList) throws SQLException, ClassNotFoundException {
        for (OrderDetailModel od : odList) {
            boolean isUpdateQty = productDAO.updateQty(od.getPId(), od.getQty());
            if (!isUpdateQty) {
                return false;
            }
        }
        return true;
    }
    @Override
    public String generateNewOrderID() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewID();
    }
    @Override
    public List<String> getProductIds() throws SQLException, ClassNotFoundException {
        return productDAO.getIds();
    }
    public boolean placeOrder(PlaceOrderModel po) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            boolean isOrderSaved = saveOrder(po.getOrder());
            if (isOrderSaved) {
                boolean isQtyUpdated = update( po.getOdList());
                if (isQtyUpdated) {
                    boolean isOrderDetailSaved = save(po.getOdList());
                    if (isOrderDetailSaved) {
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            System.out.println(e);
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
