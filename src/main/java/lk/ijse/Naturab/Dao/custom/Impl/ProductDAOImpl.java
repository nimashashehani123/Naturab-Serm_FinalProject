package lk.ijse.Naturab.Dao.custom.Impl;

import lk.ijse.Naturab.Dao.SQLUtill;
import lk.ijse.Naturab.Dao.custom.ProductDAO;
import lk.ijse.Naturab.Model.ClientModel;
import lk.ijse.Naturab.Model.ProductModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public List<ProductModel> getAll() throws SQLException, ClassNotFoundException {
        List<ProductModel> poList = new ArrayList<>();
        ResultSet rst = SQLUtill.execute("SELECT * FROM Product");
        while (rst.next()) {
            poList.add(new ProductModel(rst.getString(1),rst.getString(2),rst.getString(3),rst.getDouble(4),  rst.getInt(5),rst.getString(6),rst.getString(7)));
        }
        return poList;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("DELETE FROM Product WHERE PId = ?",id);
    }

    @Override
    public boolean save(ProductModel productModel) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO Product VALUES(?, ?, ?, ?, ?, ?, ?)",productModel.getPId(),productModel.getDescription(),productModel.getDesign(),productModel.getUnitPrice(),productModel.getQtyOnHand(),productModel.getMaId(),productModel.getWId());
    }

    @Override
    public ProductModel searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT * FROM Product WHERE PId = ?", id + "");
        rst.next();
        return new ProductModel(rst.getString(1), rst.getString(2), rst.getString(3),rst.getDouble(4),rst.getInt(5),rst.getString(6),rst.getString(7));

    }

    @Override
    public boolean update(ProductModel productModel) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE Product SET Description = ?, Design = ?, UnitPrice = ? ,QtyOnHand = ? ,MaId = ? , WId = ?  WHERE PId = ?",productModel.getDescription(),productModel.getDesign(),productModel.getUnitPrice(),productModel.getQtyOnHand(),productModel.getMaId(),productModel.getWId(),productModel.getPId());
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT PId FROM Product ORDER BY PId DESC LIMIT 1;");

        if (rst.next()) {
            String id = rst.getString("PId");
            int newClientId = Integer.parseInt(id.replace("P00-", "")) + 1;
            return String.format("P00-%03d", newClientId);
        } else {
            return "P00-001";
        }
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
        ResultSet rst = SQLUtill.execute("SELECT PId FROM Product");
        List<String> idList = new ArrayList<>();
        while (rst.next()) {
            idList.add(rst.getString(1));
        }
        return idList;
    }

    @Override
    public boolean updateQty(String id, int qty) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE Product SET QtyOnHand = QtyOnHand - ? WHERE PId = ?",qty,id);
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

    @Override
    public List<String> get1productname() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Integer> get1productcount() throws SQLException, ClassNotFoundException {
        return null;
    }
}
