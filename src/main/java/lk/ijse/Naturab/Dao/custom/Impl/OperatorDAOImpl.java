package lk.ijse.Naturab.Dao.custom.Impl;

import lk.ijse.Naturab.Dao.SQLUtill;
import lk.ijse.Naturab.Dao.custom.OperatorDAO;
import lk.ijse.Naturab.Db.DbConnection;
import lk.ijse.Naturab.Model.ClientModel;
import lk.ijse.Naturab.Model.OperatorModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperatorDAOImpl implements OperatorDAO {
    static String userid = "U00-001";

    @Override
    public List<OperatorModel> getAll() throws SQLException, ClassNotFoundException {
        List<OperatorModel> opList = new ArrayList<>();
        ResultSet rst = SQLUtill.execute("SELECT * FROM Operator");
        while (rst.next()) {
            opList.add(new OperatorModel(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),  rst.getDouble(5),rst.getInt(6),rst.getString(7),rst.getString(8)));
        }
        return opList;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("DELETE FROM Operator WHERE OpId = ?",id);
    }

    @Override
    public boolean save(OperatorModel operatorModel) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO Operator VALUES(?, ?, ?, ?, ?, ?, ?, ?)",operatorModel.getOpId(),operatorModel.getName(),operatorModel.getAddress(),operatorModel.getTel(),operatorModel.getSalary(),operatorModel.getYrOfExperience(),userid,operatorModel.getMaId());
    }

    @Override
    public OperatorModel searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT * FROM Operator WHERE OpId = ?",id + "");
        rst.next();
        return new OperatorModel(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getDouble(5),rst.getInt(6),rst.getString(7),rst.getString(8));
    }

    @Override
    public boolean update(OperatorModel operatorModel) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE Operator SET Name = ?, Email = ?, Tel = ? , Salary = ? , YrOfExperience = ? , UserId = ? , MaId = ? WHERE OpId = ?",operatorModel.getName(),operatorModel.getAddress(),operatorModel.getTel(),operatorModel.getSalary(),operatorModel.getYrOfExperience(),userid,operatorModel.getMaId(),operatorModel.getOpId());
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
        ResultSet rst = SQLUtill.execute("SELECT OpId FROM Operator");
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

    public  String getEmail(String id) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("select Operator.Email from Operator where MaId = ?",id);
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
