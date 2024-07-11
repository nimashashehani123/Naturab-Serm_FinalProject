package lk.ijse.Naturab.Dao.custom.Impl;

import lk.ijse.Naturab.Dao.SQLUtill;
import lk.ijse.Naturab.Dao.custom.MaterialDetailDAO;
import lk.ijse.Naturab.Model.MaterialDetailModel;

import java.sql.SQLException;
import java.util.List;

public class MaterialDetailDAOImpl implements MaterialDetailDAO {
    @Override
    public List<MaterialDetailModel> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean save(MaterialDetailModel materialDetailModel) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO Material_Details VALUES(?, ?, ?)",materialDetailModel.getMId(),materialDetailModel.getPId(),materialDetailModel.getQty());
    }

    @Override
    public MaterialDetailModel searchById(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean update(MaterialDetailModel Object) throws SQLException, ClassNotFoundException {
        return false;
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
        return null;
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
