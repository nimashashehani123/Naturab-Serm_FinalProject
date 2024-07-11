package lk.ijse.Naturab.Dao.custom.Impl;

import lk.ijse.Naturab.Dao.SQLUtill;
import lk.ijse.Naturab.Dao.custom.UserDAO;
import lk.ijse.Naturab.Model.UserModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public List<UserModel> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean save(UserModel userModel) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO User VALUES(?, ?, ?)", userModel.getUserId(),userModel.getUserName(),userModel.getPassword());
    }

    @Override
    public UserModel searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT Password FROM User WHERE UserId = ?", id +"");
        rst.next();
        return new UserModel(rst.getString(1), rst.getString(2), rst.getString(3));

    }

    @Override
    public boolean update(UserModel Object) throws SQLException, ClassNotFoundException {
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
