package lk.ijse.Naturab.Repositry;

import lk.ijse.Naturab.Db.DbConnection;
import lk.ijse.Naturab.Model.ClientModel;
import lk.ijse.Naturab.Model.OrderModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderRepo {

    public static boolean save(OrderModel order) throws SQLException {
        String sql = "INSERT INTO Orders VALUES(?, ?, ?,?,?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setString(1, order.getOId());
        pstm.setDate(2, (java.sql.Date) order.getPlacedDate());
        pstm.setDouble(3, order.getPaymentAmount());
        pstm.setString(4, order.getStatus());
        pstm.setString(5, order.getCId());

        return pstm.executeUpdate() > 0;
    }
    public static String getCurrentId() throws SQLException {
        String sql = "SELECT OId FROM Orders ORDER BY OId DESC LIMIT 1";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            String orderId = resultSet.getString(1);
            return orderId;
        }
        return null;
    }

    public static OrderModel searchById(String id) throws SQLException {
        String sql = "SELECT * FROM Orders WHERE OId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String o_id = resultSet.getString(1);
            Date  placeddate = resultSet.getDate(2);
            double paymentamount = resultSet.getDouble(3);
            String status = resultSet.getString(4);
            String CId = resultSet.getString(5);

            OrderModel orderModel = new OrderModel(o_id, placeddate, paymentamount, status, CId);

            return orderModel;
        }

        return null;
    }

    public static boolean deleteOrder(String id) throws SQLException {
        String sql = "DELETE FROM Orders WHERE OId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static boolean updateOrder( OrderModel orderModel) throws SQLException {
        String sql = "UPDATE Orders SET PlacedDate = ?, PaymentAmount = ?, Status = ? ,CId = ? WHERE OId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, orderModel.getPlacedDate());
        pstm.setObject(2, orderModel.getPaymentAmount());
        pstm.setObject(3, orderModel.getStatus());
        pstm.setObject(4, orderModel.getCId());
        pstm.setObject(5, orderModel.getOId());

        return pstm.executeUpdate() > 0;
    }

    public static List<OrderModel> getAll() throws SQLException {
        String sql = "SELECT * FROM Orders";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<OrderModel> oList = new ArrayList<>();

        while (resultSet.next()) {
            String o_id = resultSet.getString(1);
            Date  placeddate = resultSet.getDate(2);
            double paymentamount = resultSet.getDouble(3);
            String status = resultSet.getString(4);
            String CId = resultSet.getString(5);

            OrderModel orderModel = new OrderModel(o_id, placeddate, paymentamount, status, CId);
            oList.add(orderModel);
        }
        return oList;
    }

}
