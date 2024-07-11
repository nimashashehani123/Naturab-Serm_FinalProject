package lk.ijse.Naturab.Dao.custom.Impl;

import lk.ijse.Naturab.Dao.SQLUtill;
import lk.ijse.Naturab.Dao.custom.EmployeeDAO;
import lk.ijse.Naturab.Model.ClientModel;
import lk.ijse.Naturab.Model.EmployeeModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    static String userid =  "U00-001" ;
    @Override
    public List<EmployeeModel> getAll() throws SQLException, ClassNotFoundException {
        List<EmployeeModel> emList = new ArrayList<>();
        ResultSet rst = SQLUtill.execute("SELECT * FROM OtherEmp");
        while (rst.next()) {
            emList.add(new EmployeeModel(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),  rst.getDouble(5),rst.getInt(4),rst.getString(4)));
        }
        return emList;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("DELETE FROM OtherEmp WHERE EmId = ?",id);
    }

    @Override
    public boolean save(EmployeeModel employeeModel) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO OtherEmp VALUES(?, ?, ?, ?, ?, ?, ?)",employeeModel.getEId(),employeeModel.getName(),employeeModel.getAddress(),employeeModel.getTel(),employeeModel.getSalary(),employeeModel.getYrOfExperience(),userid);

    }

    @Override
    public EmployeeModel searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT * FROM OtherEmp WHERE EmId = ?", id + "");
        rst.next();
        return new EmployeeModel(rst.getString(1), rst.getString(2), rst.getString(3),rst.getString(4),rst.getDouble(5),rst.getInt(6),rst.getString(7));

    }

    @Override
    public boolean update(EmployeeModel employeeModel) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE OtherEmp SET Name = ?, Address = ?, Tel = ? , Salary = ? , YrOfExperience = ? , UserId = ? WHERE EmId = ?",employeeModel.getName(),employeeModel.getAddress(),employeeModel.getTel(),employeeModel.getSalary(),employeeModel.getYrOfExperience(),userid,employeeModel.getEId());
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
