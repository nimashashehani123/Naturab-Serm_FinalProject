package lk.ijse.Naturab.Repositry;

import javafx.scene.paint.Material;
import lk.ijse.Naturab.Db.DbConnection;
import lk.ijse.Naturab.Model.ClientModel;
import lk.ijse.Naturab.Model.MaterialDetailModel;
import lk.ijse.Naturab.Model.MaterialModel;
import lk.ijse.Naturab.Model.OrderDetailModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialRepo {
    public static boolean saveMaterial(MaterialModel materialModel) throws SQLException {
        String sql = "INSERT INTO Material VALUES(?, ?, ?, ?, ?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,materialModel.getMId());
        pstm.setObject(2,materialModel.getDescription());
        pstm.setObject(3,materialModel.getUnitCost());
        pstm.setObject(4,materialModel.getQtyOnHand());
        pstm.setObject(5,materialModel.getSId());

        return pstm.executeUpdate() > 0;

    }
    public static boolean deleteMaterial(String id) throws SQLException {
        String sql = "DELETE FROM Material WHERE MId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }
    public static MaterialModel searchById(String id) throws SQLException {
        String sql = "SELECT * FROM Material WHERE MId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String m_id = resultSet.getString(1);
            String description = resultSet.getString(2);
            double unitcost = resultSet.getDouble(3);
            int qty = resultSet.getInt(4);
            String sid = resultSet.getString(5);

            MaterialModel materialModel = new MaterialModel(m_id, description, unitcost, qty, sid);

            return materialModel;
        }

        return null;
    }
    public static boolean updateMaterial( MaterialModel materialModel) throws SQLException {
        String sql = "UPDATE Material SET Description = ?, UnitCost = ?, QtyOnHand = ? ,SId = ? WHERE MId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, materialModel.getDescription());
        pstm.setObject(2, materialModel.getUnitCost());
        pstm.setObject(3, materialModel.getQtyOnHand());
        pstm.setObject(4, materialModel.getSId());
        pstm.setObject(5, materialModel.getMId());

        return pstm.executeUpdate() > 0;
    }
    public static List<MaterialModel> getAll() throws SQLException {
        String sql = "SELECT * FROM Material";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<MaterialModel> mList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String description = resultSet.getString(2);
            double unitcost = resultSet.getDouble(3);
            int qty = resultSet.getInt(4);
            String sid = resultSet.getString(5);

            MaterialModel materialModel = new MaterialModel(id, description, unitcost, qty, sid);
            mList.add(materialModel);
        }
        return mList;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT MId FROM Material";
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

    public static boolean update(List<MaterialDetailModel> mdList) throws SQLException {
        for (MaterialDetailModel md : mdList) {
            boolean isUpdateQty = updateQty(md.getMId(), md.getQty());
            if(!isUpdateQty) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateQty(String id, int qty) throws SQLException {
        String sql = "UPDATE Material SET QtyOnHand = QtyOnHand - ? WHERE MId = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setInt(1, qty);
        pstm.setString(2, id);

        return pstm.executeUpdate() > 0;
    }
}
