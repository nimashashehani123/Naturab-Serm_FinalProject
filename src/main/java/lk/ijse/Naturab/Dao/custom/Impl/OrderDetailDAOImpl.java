package lk.ijse.Naturab.Dao.custom.Impl;

import lk.ijse.Naturab.Dao.SQLUtill;
import lk.ijse.Naturab.Dao.custom.OrderDetailDAO;
import lk.ijse.Naturab.Db.DbConnection;
import lk.ijse.Naturab.Model.OrderDetailModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public List<OrderDetailModel> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean save(OrderDetailModel object) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO Order_Details VALUES(?, ?, ?)",object.getOId(),object.getPId(),object.getQty());
    }

    @Override
    public OrderDetailModel searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(OrderDetailModel Object) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public int getcount() throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public int getcount1() throws SQLException, ClassNotFoundException {
        return 0;
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

    @Override
    public List<Double> getPayment() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<String> getOId() throws SQLException, ClassNotFoundException {
        return null;
    }
    public List<String> get1productname() throws SQLException, ClassNotFoundException {
       ResultSet rst = SQLUtill.execute("select COUNT(PId) as count,PId from  Order_Details GROUP BY PId ORDER BY count DESC LIMIT 4");
        List<String > nameList = new ArrayList<>();
        while (rst.next()) {
            nameList.add(rst.getString("PId"));
        }
        return nameList;
    }
    public List<Integer> get1productcount() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("select COUNT(PId) as count,PId from  Order_Details GROUP BY PId ORDER BY count DESC LIMIT 4") ;
        List<Integer > countList = new ArrayList<>();
        while (rst.next()) {
            countList.add(rst.getInt("count"));
        }
        return countList;
    }
}
