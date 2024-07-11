package lk.ijse.Naturab.Dao.custom.Impl;

import lk.ijse.Naturab.Dao.SQLUtill;
import lk.ijse.Naturab.Dao.custom.SupplierDAO;
import lk.ijse.Naturab.Model.ClientModel;
import lk.ijse.Naturab.Model.SupplierModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public List<SupplierModel> getAll() throws SQLException, ClassNotFoundException {
        List<SupplierModel> suList = new ArrayList<>();
        ResultSet rst = SQLUtill.execute("SELECT * FROM Supplier");
        while (rst.next()) {
            suList.add(new SupplierModel(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4)));
        }
        return suList;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("DELETE FROM Supplier WHERE SId = ?",id);
    }

    @Override
    public boolean save(SupplierModel supplierModel) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO Supplier VALUES(?, ?, ?, ?)",supplierModel.getSId(),supplierModel.getName(),supplierModel.getAddress(),supplierModel.getTel());
    }

    @Override
    public SupplierModel searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT * FROM Supplier WHERE SId = ?", id + "");
        rst.next();
        return new SupplierModel(rst.getString(1), rst.getString(2), rst.getString(3),rst.getString(4));

    }

    @Override
    public boolean update(SupplierModel supplierModel) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE Supplier SET Name = ?, Address = ?, Tel = ? WHERE SId = ?",supplierModel.getName(),supplierModel.getAddress(),supplierModel.getTel(),supplierModel.getSId());
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
        ResultSet rst = SQLUtill.execute("SELECT SId FROM Supplier");
        List<String> idList = new ArrayList<>();
        while (rst.next()) {
            idList.add(rst.getString(1));
        }
        return idList;
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

    @Override
    public List<String> get1productname() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Integer> get1productcount() throws SQLException, ClassNotFoundException {
        return null;
    }
}
