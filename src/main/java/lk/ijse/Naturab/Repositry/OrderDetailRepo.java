package lk.ijse.Naturab.Repositry;

import lk.ijse.Naturab.Db.DbConnection;
import lk.ijse.Naturab.Model.ClientModel;
import lk.ijse.Naturab.Model.OrderDetailModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderDetailRepo {

    public static boolean save(List<OrderDetailModel> odList) throws SQLException {
        for (OrderDetailModel od : odList) {
            boolean isSaved = save(od);
            if(!isSaved) {
                return false;
            }
        }
        return true;
    }

    private static boolean save(OrderDetailModel od) throws SQLException {
        String sql = "INSERT INTO Order_Details VALUES(?, ?, ?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setString(1, od.getOId());
        pstm.setString(2, od.getPId());
        pstm.setInt(3, od.getQty());

        return pstm.executeUpdate() > 0;
    }

    public static  List<String> get1productname() throws SQLException {
        String sql = "select COUNT(PId) as count,PId from  Order_Details GROUP BY PId ORDER BY count DESC LIMIT 4";
        List<String > nameList = new ArrayList<>();

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            String pId = resultSet.getString("PId");
            nameList.add(pId);
        }


        return nameList;
    }
    public static  List<Integer> get1productcount() throws SQLException {
        String sql = "select COUNT(PId) as count,PId from  Order_Details GROUP BY PId ORDER BY count DESC LIMIT 4";

        List<Integer > countList = new ArrayList<>();
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            int count = resultSet.getInt("count");
            countList.add(count);
        }


        return countList;
    }
}
