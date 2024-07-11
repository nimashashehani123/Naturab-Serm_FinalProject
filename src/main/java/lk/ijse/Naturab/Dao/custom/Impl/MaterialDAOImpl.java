package lk.ijse.Naturab.Dao.custom.Impl;

import lk.ijse.Naturab.Dao.SQLUtill;
import lk.ijse.Naturab.Dao.custom.MachineDAO;
import lk.ijse.Naturab.Dao.custom.MaterialDAO;
import lk.ijse.Naturab.Db.DbConnection;
import lk.ijse.Naturab.Model.ClientModel;
import lk.ijse.Naturab.Model.MachineModel;
import lk.ijse.Naturab.Model.MaterialModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAOImpl implements MaterialDAO {
    @Override
    public List<MaterialModel> getAll() throws SQLException, ClassNotFoundException {
        List<MaterialModel> maList = new ArrayList<>();
        ResultSet rst = SQLUtill.execute("SELECT * FROM Material");
        while (rst.next()) {
            maList.add(new MaterialModel(rst.getString(1),rst.getString(2),rst.getDouble(3),rst.getInt(4),rst.getString(5)));
        }
        return maList;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("DELETE FROM Material WHERE MId = ?",id);
    }

    @Override
    public boolean save(MaterialModel materialModel) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO Material VALUES(?, ?, ?, ?, ?)",materialModel.getMId(),materialModel.getDescription(),materialModel.getUnitCost(),materialModel.getQtyOnHand(),materialModel.getSId());
    }

    @Override
    public MaterialModel searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT * FROM Material WHERE MId = ?", id + "");
        rst.next();
        return new MaterialModel(rst.getString(1), rst.getString(2), rst.getDouble(3),rst.getInt(4),rst.getString(5));

    }


    @Override
    public boolean update(MaterialModel materialModel) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE Material SET Description = ?, UnitCost = ?, QtyOnHand = ? ,SId = ? WHERE MId = ?",materialModel.getDescription(),materialModel.getUnitCost(),materialModel.getQtyOnHand(),materialModel.getSId(),materialModel.getMId());
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
        ResultSet rst = SQLUtill.execute("SELECT MId FROM Material");
        List<String> idList = new ArrayList<>();
        while (rst.next()) {
            idList.add(rst.getString(1));
        }
        return idList;
    }

     public boolean updateQty(String id, int qty) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE Material SET QtyOnHand = QtyOnHand - ? WHERE MId = ?",qty,id);
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
