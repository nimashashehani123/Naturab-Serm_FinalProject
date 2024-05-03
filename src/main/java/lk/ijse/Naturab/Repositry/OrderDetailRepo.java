package lk.ijse.Naturab.Repositry;

import lk.ijse.Naturab.Db.DbConnection;
import lk.ijse.Naturab.Model.OrderDetailModel;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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
}
