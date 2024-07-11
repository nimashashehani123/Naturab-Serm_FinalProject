package lk.ijse.Naturab.Dao.custom.Impl;
import lk.ijse.Naturab.Dao.SQLUtill;
import lk.ijse.Naturab.Dao.custom.OrderDAO;
import lk.ijse.Naturab.Model.OrderModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class OrderDAOImpl implements OrderDAO {
    @Override
    public List<OrderModel> getAll() throws SQLException, ClassNotFoundException {
        List<OrderModel> odList = new ArrayList<>();
        ResultSet rst = SQLUtill.execute("SELECT * FROM Orders");
        while (rst.next()) {
            odList.add(new OrderModel(rst.getString(1),rst.getDate(2),rst.getDouble(3),rst.getString(4),  rst.getString(5)));
        }
        return odList;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("DELETE FROM Orders WHERE OId = ?",id);
    }

    @Override
    public boolean save(OrderModel orderModel) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO Orders VALUES(?, ?, ?,?,?)",orderModel.getOId(),orderModel.getPlacedDate(),orderModel.getPaymentAmount(),orderModel.getStatus(),orderModel.getCId());
    }

    @Override
    public OrderModel searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT * FROM Orders WHERE OId = ?",id + "");
        rst.next();
        return new OrderModel(rst.getString(1),rst.getDate(2),rst.getDouble(3),rst.getString(4),rst.getString(5));
    }

    @Override
    public boolean update(OrderModel orderModel) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE Orders SET PlacedDate = ?, PaymentAmount = ?, Status = ? ,CId = ? WHERE OId = ?",orderModel.getPlacedDate(),orderModel.getPaymentAmount(),orderModel.getStatus(),orderModel.getCId(),orderModel.getOId());
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT OId FROM Orders ORDER BY OId DESC LIMIT 1");

        if (rst.next()) {
            String id = rst.getString("OId");
            int newClientId = Integer.parseInt(id.replace("O00-", "")) + 1;
            return String.format("O00-%03d", newClientId);
        } else {
            return "O00-001";
        }
    }

    @Override
    public int getcount() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT COUNT(*) AS order_count FROM Orders where Status =  'Completed'");
        int count = 0;
        while(rst.next()){
            count = rst.getInt("order_count");
        }
        return count; 
    }

    @Override
    public int getcount1() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT COUNT(*) AS order_count FROM Orders where Status =  'InCompleted'");
        int count1 = 0;
        while(rst.next()){
            count1 = rst.getInt("order_count");
        }
        return count1;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean updateQty(String id, int qty) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getEmail(String id) throws SQLException, ClassNotFoundException {
        return null;
    }
    public List<Double> getPayment() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("select Orders.OId ,PaymentAmount from  Orders GROUP BY OId ORDER BY PaymentAmount DESC LIMIT 4");
        List<Double > paymentList = new ArrayList<>();
        while (rst.next()) {
            Double amount = ( rst.getDouble("PaymentAmount"));
            paymentList.add(amount);
        }
        return paymentList;
    }
    public List<String> getOId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("select Orders.OId ,PaymentAmount from  Orders GROUP BY OId ORDER BY PaymentAmount DESC LIMIT 4;") ;
        List<String > oidList = new ArrayList<>();
        while (rst.next()) {
            oidList.add(rst.getString("OId"));
        }
        return oidList;
    }

    @Override
    public List<String> get1productname() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Integer> get1productcount() throws SQLException, ClassNotFoundException {
        return null;
    }
}
