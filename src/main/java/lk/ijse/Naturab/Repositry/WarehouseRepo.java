package lk.ijse.Naturab.Repositry;

import lk.ijse.Naturab.Db.DbConnection;
import lk.ijse.Naturab.Model.ClientModel;
import lk.ijse.Naturab.Model.WarehouseModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WarehouseRepo {
    public static boolean saveWarehouse(WarehouseModel warehouseModel) throws SQLException {
        String sql = "INSERT INTO Warehouse VALUES(?, ?, ?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,warehouseModel.getWId());
        pstm.setObject(2,warehouseModel.getLocation());
        pstm.setObject(3,warehouseModel.getCapacity());
        return pstm.executeUpdate() > 0;

    }
    public static List<String> getIds() throws SQLException {
        String sql = "SELECT WId FROM Warehouse";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }
}
