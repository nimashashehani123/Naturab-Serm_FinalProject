package lk.ijse.Naturab.Repositry;

import lk.ijse.Naturab.Db.DbConnection;
import lk.ijse.Naturab.Model.SupplierModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepo {
    public static boolean saveSpplier(SupplierModel supplierModel) throws SQLException {
        String sql = "INSERT INTO Supplier VALUES(?, ?, ?, ?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,supplierModel.getSId());
        pstm.setObject(2, supplierModel.getName());
        pstm.setObject(3, supplierModel.getAddress());
        pstm.setObject(4, supplierModel.getTel());

        return pstm.executeUpdate() > 0;

    }
    public static boolean deleteSupplier(String id) throws SQLException {
        String sql = "DELETE FROM Supplier WHERE SId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }
    public static SupplierModel searchById(String id) throws SQLException {
        String sql = "SELECT * FROM Supplier WHERE SId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String su_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);


            SupplierModel supplierModel = new SupplierModel(su_id, name, address, tel);

            return supplierModel;
        }

        return null;
    }
    public static boolean updateSupplier( SupplierModel supplierModel) throws SQLException {
        String sql = "UPDATE Supplier SET Name = ?, Address = ?, Tel = ? WHERE SId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, supplierModel.getName());
        pstm.setObject(2, supplierModel.getAddress());
        pstm.setObject(3, supplierModel.getTel());
        pstm.setObject(4, supplierModel.getSId());

        return pstm.executeUpdate() > 0;
    }
    public static List<SupplierModel> getAll() throws SQLException {
        String sql = "SELECT * FROM Supplier";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<SupplierModel> suList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);

            SupplierModel supplierModel = new SupplierModel(id, name, address, tel);
            suList.add(supplierModel);
        }
        return suList;
    }
}
