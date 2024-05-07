package lk.ijse.Naturab.Repositry;

import lk.ijse.Naturab.Db.DbConnection;
import lk.ijse.Naturab.Model.EmployeeModel;
import lk.ijse.Naturab.Model.OperatorModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperatorRepo {
    static String userid;

    public static void getcurrentuser(String userId) {
        userid = userId;

    }

    public static boolean saveOperator(OperatorModel operatorModel) throws SQLException {
        String sql = "INSERT INTO Operator VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1, operatorModel.getOpId());
        pstm.setObject(2, operatorModel.getName());
        pstm.setObject(3, operatorModel.getAddress());
        pstm.setObject(4, operatorModel.getTel());
        pstm.setObject(5, operatorModel.getSalary());
        pstm.setObject(6, operatorModel.getYrOfExperience());
        pstm.setObject(7, userid);
        pstm.setObject(8, operatorModel.getMaId());



        return pstm.executeUpdate() > 0;

    }

    public static boolean deleteOperator(String id) throws SQLException {
        String sql = "DELETE FROM Operator WHERE OpId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    public static OperatorModel searchById(String id) throws SQLException {
        String sql = "SELECT * FROM Operator WHERE OpId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String op_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);
            double salary = resultSet.getDouble(5);
            int yrofexperience = resultSet.getInt(6);
            String userid = resultSet.getString(7);
            String maid = resultSet.getString(8);

            OperatorModel operatorModel = new OperatorModel(op_id, name, address, tel, salary, yrofexperience, userid,maid);

            return operatorModel;
        }

        return null;
    }

    public static boolean updateOperator(OperatorModel operatorModel) throws SQLException {
        String sql = "UPDATE Operator SET Name = ?, Address = ?, Tel = ? , Salary = ? , YrOfExperience = ? , UserId = ? , MaId = ? WHERE OpId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, operatorModel.getName());
        pstm.setObject(2, operatorModel.getAddress());
        pstm.setObject(3, operatorModel.getTel());
        pstm.setObject(4, operatorModel.getSalary());
        pstm.setObject(5, operatorModel.getYrOfExperience());
        pstm.setObject(6, userid);
        pstm.setObject(7, operatorModel.getMaId());
        pstm.setObject(8, operatorModel.getOpId());

        return pstm.executeUpdate() > 0;
    }

    public static List<OperatorModel> getAll() throws SQLException {
        String sql = "SELECT * FROM Operator";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<OperatorModel> opList = new ArrayList<>();

        while (resultSet.next()) {
            String op_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);
            double salary = resultSet.getDouble(5);
            int yrofexperience = resultSet.getInt(6);
            String userid = resultSet.getString(7);
            String maid = resultSet.getString(8);

            OperatorModel operatorModel = new OperatorModel(op_id, name, address, tel, salary, yrofexperience, userid, maid);

            opList.add(operatorModel);
        }
        return opList;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT OpId FROM Operator";
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
}
