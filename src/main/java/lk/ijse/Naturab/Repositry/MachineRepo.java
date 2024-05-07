package lk.ijse.Naturab.Repositry;

import lk.ijse.Naturab.Db.DbConnection;
import lk.ijse.Naturab.Model.ClientModel;
import lk.ijse.Naturab.Model.MachineModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MachineRepo {
    public static boolean saveMachine(MachineModel machineModel) throws SQLException {
        String sql = "INSERT INTO Machine VALUES(?, ?, ?, ?, ?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,machineModel.getMaId());
        pstm.setObject(2,machineModel.getDescription());
        pstm.setObject(3,machineModel.getType());
        pstm.setObject(4,machineModel.getCapacity());
        pstm.setObject(5,machineModel.getStatus());

        return pstm.executeUpdate() > 0;

    }
    public static boolean deleteMachine(String id) throws SQLException {
        String sql = "DELETE FROM Machine WHERE MaId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }
    public static MachineModel searchById(String id) throws SQLException {
        String sql = "SELECT * FROM Machine WHERE MaId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String ma_id = resultSet.getString(1);
            String description = resultSet.getString(2);
            String type = resultSet.getString(3);
            String capacity = resultSet.getString(4);
            String status = resultSet.getString(5);

            MachineModel machineModel = new MachineModel(ma_id, description, type, capacity,status);

            return machineModel;
        }

        return null;
    }
    public static boolean updateMachine(MachineModel machineModel) throws SQLException {
        String sql = "UPDATE Machine SET Description = ?, Type = ?, Capacity = ? ,Status = ? WHERE MaId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, machineModel.getDescription());
        pstm.setObject(2, machineModel.getType());
        pstm.setObject(3, machineModel.getCapacity());
        pstm.setObject(4, machineModel.getStatus());
        pstm.setObject(5, machineModel.getMaId());

        return pstm.executeUpdate() > 0;
    }
    public static List<MachineModel> getAll() throws SQLException {
        String sql = "SELECT * FROM Machine";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<MachineModel> maList = new ArrayList<>();

        while (resultSet.next()) {
            String ma_id = resultSet.getString(1);
            String description = resultSet.getString(2);
            String type = resultSet.getString(3);
            String capacity = resultSet.getString(4);
            String status = resultSet.getString(5);

            MachineModel machineModel = new MachineModel(ma_id, description, type, capacity,status);

            maList.add(machineModel);
        }
        return maList;
    }
    public static List<String> getIds() throws SQLException {
        String sql = "SELECT MaId FROM Machine";
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
