package lk.ijse.Naturab.Dao.custom.Impl;

import lk.ijse.Naturab.Dao.SQLUtill;
import lk.ijse.Naturab.Dao.custom.MachineDAO;
import lk.ijse.Naturab.Db.DbConnection;
import lk.ijse.Naturab.Model.ClientModel;
import lk.ijse.Naturab.Model.MachineModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MachineDAOImpl implements MachineDAO {
    @Override
    public List<MachineModel> getAll() throws SQLException, ClassNotFoundException {
        List<MachineModel> maList = new ArrayList<>();
        ResultSet rst = SQLUtill.execute("SELECT * FROM Machine");
        while (rst.next()) {
            maList.add(new MachineModel(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),  rst.getString(5)));
        }
        return maList;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("DELETE FROM Machine WHERE MaId = ?",id);
    }

    @Override
    public boolean save(MachineModel machineModel) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO Machine VALUES(?, ?, ?, ?, ?)",machineModel.getMaId(),machineModel.getDescription(),machineModel.getType(),machineModel.getCapacity(),machineModel.getStatus());
    }

    @Override
    public MachineModel searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT * FROM Machine WHERE MaId = ?",id + "");
        rst.next();
        return new MachineModel(rst.getString(1), rst.getString(2), rst.getString(3),rst.getString(4),rst.getString(5));

    }

    @Override
    public boolean update(MachineModel machineModel) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE Machine SET Description = ?, Type = ?, Capacity = ? ,Status = ? WHERE MaId = ?",machineModel.getDescription(),machineModel.getType(),machineModel.getCapacity(),machineModel.getStatus(),machineModel.getMaId());
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }
    public int getcount() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT COUNT(*) AS machine_count FROM Machine where Status = 'Active'");
        int count = 0;
        while(rst.next()){
            count = rst.getInt("machine_count");
        }
        return count;
    }
    public  int getcount1() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT COUNT(*) AS machine_count FROM Machine where Status = 'Broken'");
        int count = 0;
        while(rst.next()){
            count = rst.getInt("machine_count");
        }
        return count;
    }

    public List<String> getIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT MaId FROM Machine");
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
