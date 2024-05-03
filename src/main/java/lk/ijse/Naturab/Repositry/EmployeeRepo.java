package lk.ijse.Naturab.Repositry;


import lk.ijse.Naturab.Db.DbConnection;
import lk.ijse.Naturab.Model.EmployeeModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepo {
    static String userid ;
    public static void getcurrentuser(String userId){
        userid = userId;
    }
    public static boolean saveEmp(EmployeeModel employeeModel) throws SQLException {
        String sql = "INSERT INTO OtherEmp VALUES(?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        pstm.setObject(1,employeeModel.getEId());
        pstm.setObject(2, employeeModel.getName());
        pstm.setObject(3, employeeModel.getAddress());
        pstm.setObject(4, employeeModel.getTel());
        pstm.setObject(5, employeeModel.getSalary());
        pstm.setObject(6, employeeModel.getYrOfExperience());
        pstm.setObject(7, userid);


        return pstm.executeUpdate() > 0;

    }
    public static boolean deleteEmp(String id) throws SQLException {
        String sql = "DELETE FROM OtherEmp WHERE EmId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }
    public static EmployeeModel searchById(String id) throws SQLException {
        String sql = "SELECT * FROM OtherEmp WHERE EmId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String emp_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);
            double salary = resultSet.getDouble(5);
            int yrofexperience = resultSet.getInt(5);
            String userid = resultSet.getString(6);

            EmployeeModel employeeModel = new EmployeeModel(emp_id, name, address, tel, salary, yrofexperience, userid);

            return employeeModel;
        }

        return null;
    }
    public static boolean updateEmp( EmployeeModel employeeModel) throws SQLException {
        String sql = "UPDATE OtherEmp SET Name = ?, Address = ?, Tel = ? , Salary = ? , YrOfExperience , UserId = ? WHERE EmId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, employeeModel.getName());
        pstm.setObject(2, employeeModel.getAddress());
        pstm.setObject(3, employeeModel.getTel());
        pstm.setObject(4, employeeModel.getSalary());
        pstm.setObject(5, employeeModel.getYrOfExperience());
        pstm.setObject(6, userid);
        pstm.setObject(5, employeeModel.getEId());

        return pstm.executeUpdate() > 0;
    }
    public static List<EmployeeModel> getAll() throws SQLException {
        String sql = "SELECT * FROM OtherEmp";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<EmployeeModel> emList = new ArrayList<>();

        while (resultSet.next()) {
            String emp_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String tel = resultSet.getString(4);
            double salary = resultSet.getDouble(5);
            int yrofexperience = resultSet.getInt(5);
            String userid = resultSet.getString(6);

            EmployeeModel employeeModel = new EmployeeModel(emp_id, name, address, tel, salary, yrofexperience, userid);

            emList.add(employeeModel);
        }
        return emList;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT CId FROM Client";
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
