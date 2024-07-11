package lk.ijse.Naturab.Dao.custom.Impl;
import lk.ijse.Naturab.Dao.SQLUtill;
import lk.ijse.Naturab.Dao.custom.ClientDAO;
import lk.ijse.Naturab.Db.DbConnection;
import lk.ijse.Naturab.Model.ClientModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {

    public  List<ClientModel> getAll() throws SQLException, ClassNotFoundException {
       List<ClientModel> clList = new ArrayList<>();
        ResultSet rst = SQLUtill.execute("SELECT * FROM Client;");
        while (rst.next()) {
            clList.add(new ClientModel(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),  rst.getString(5)));
        }
        return clList;
    }
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("DELETE FROM Client WHERE CId = ?",id);
    }

    public  boolean save(ClientModel clientModel) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO Client VALUES(?, ?, ?, ?, ?)",clientModel.getCId(),clientModel.getName(),clientModel.getAddress(),clientModel.getTel(),clientModel.getEmail());

    }

    public ClientModel searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT * FROM Client WHERE CId = ?;", id + "");
        rst.next();
        return new ClientModel(rst.getString(1), rst.getString(2), rst.getString(3),rst.getString(4),rst.getString(5));
    }
    public boolean update( ClientModel clientModel) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE Client SET Name = ?, Address = ?, Tel = ? ,Email = ? WHERE CId = ?;",clientModel.getName(),clientModel.getAddress(),clientModel.getTel(),clientModel.getEmail(),clientModel.getCId());
    }

    public String generateNewID() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtill.execute("SELECT CId FROM Client ORDER BY CId DESC LIMIT 1;");

        if (rst.next()) {
            String id = rst.getString("CId");
            int newClientId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newClientId);
        } else {
            return "C00-001";
        }
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
        ResultSet rst = SQLUtill.execute("SELECT CId FROM Client");
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
