package lk.ijse.Naturab.Repositry;

import lk.ijse.Naturab.Db.DbConnection;
import lk.ijse.Naturab.Model.ClientModel;
import lk.ijse.Naturab.Model.OrderDetailModel;
import lk.ijse.Naturab.Model.ProductModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepo {
    public static boolean saveProduct(ProductModel productModel) throws SQLException {
        String sql = "INSERT INTO Product VALUES(?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql)) {
            pstm.setObject(1, productModel.getPId());
            pstm.setObject(2, productModel.getDescription());
            pstm.setObject(3, productModel.getDesign());
            pstm.setObject(4, productModel.getUnitPrice());
            pstm.setObject(5, productModel.getQtyOnHand());
            pstm.setObject(6, productModel.getMaId());
            pstm.setObject(7, productModel.getWId());

            return pstm.executeUpdate() > 0;
        }catch (SQLException e) {
        e.printStackTrace();
            return false;
        }

    }
    public static boolean deleteProduct(String id) throws SQLException {
        String sql = "DELETE FROM Product WHERE PId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }
    public static ProductModel searchById(String id) throws SQLException {
        String sql = "SELECT * FROM Product WHERE PId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String p_id = resultSet.getString(1);
            String description = resultSet.getString(2);
            String design = resultSet.getString(3);
            double unitprice = Double.parseDouble(resultSet.getString(4));
            int qty = Integer.parseInt(resultSet.getString(5));
            String maid = resultSet.getString(6);
            String wid = resultSet.getString(7);

            ProductModel productModel = new ProductModel(p_id, description, design, unitprice, qty, maid, wid);

            return productModel;
        }

        return null;
    }
    public static boolean updateProduct( ProductModel productModel) throws SQLException {
        String sql = "UPDATE Product SET Description = ?, Design = ?, UnitPrice = ? ,QtyOnHand = ? ,MaId = ? , WId = ?  WHERE PId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, productModel.getDescription());
        pstm.setObject(2, productModel.getDesign());
        pstm.setObject(3, productModel.getUnitPrice());
        pstm.setObject(4, productModel.getQtyOnHand());
        pstm.setObject(5, productModel.getMaId());
        pstm.setObject(6, productModel.getWId());
        pstm.setObject(7, productModel.getPId());

        return pstm.executeUpdate() > 0;
    }
    public static List<ProductModel> getAll() throws SQLException {
        String sql = "SELECT * FROM Product";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<ProductModel> pList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String description = resultSet.getString(2);
            String design = resultSet.getString(3);
            Double unitprice = Double.valueOf(resultSet.getString(4));
            int qty = Integer.parseInt(resultSet.getString(5));
            String maid = resultSet.getString(6);
            String wid = resultSet.getString(7);

            ProductModel productModel = new ProductModel(id, description, design, unitprice, qty, maid, wid);
            pList.add(productModel);
        }
        return pList;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT PId FROM Product";
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

    public static boolean update(List<OrderDetailModel> odList) throws SQLException {
        for (OrderDetailModel od : odList) {
            boolean isUpdateQty = updateQty(od.getPId(), od.getQty());
            if(!isUpdateQty) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateQty(String id, int qty) throws SQLException {
        String sql = "UPDATE Product SET QtyOnHand = QtyOnHand - ? WHERE PId = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setInt(1, qty);
        pstm.setString(2, id);

        return pstm.executeUpdate() > 0;
    }
}
