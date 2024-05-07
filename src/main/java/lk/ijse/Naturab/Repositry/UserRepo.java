package lk.ijse.Naturab.Repositry;

import lk.ijse.Naturab.Db.DbConnection;
import lk.ijse.Naturab.Model.ClientModel;
import lk.ijse.Naturab.Model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepo {
    public static boolean saveUser(UserModel userModel) throws SQLException {
        String sql = "INSERT INTO User VALUES(?, ?, ?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,userModel.getUserId());
        pstm.setObject(2, userModel.getUserName());
        pstm.setObject(3, userModel.getPassword());

        return pstm.executeUpdate() > 0;

    }

    public static boolean deleteClient(String id) throws SQLException {
        String sql = "DELETE FROM Client WHERE CId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static String searchById(String id) throws SQLException {
        String sql = "SELECT Password FROM User WHERE UserId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String Pw = resultSet.getString("Password");
            return Pw;
        }

        return null;
    }

    public static boolean updateClient( ClientModel clientModel) throws SQLException {
        String sql = "UPDATE Client SET Name = ?, Address = ?, Tel = ? ,Email = ? WHERE CId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, clientModel.getName());
        pstm.setObject(2, clientModel.getAddress());
        pstm.setObject(3, clientModel.getTel());
        pstm.setObject(4, clientModel.getEmail());
        pstm.setObject(5, clientModel.getCId());

        return pstm.executeUpdate() > 0;
    }
}
