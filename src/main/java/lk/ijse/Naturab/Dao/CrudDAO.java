package lk.ijse.Naturab.Dao;

import lk.ijse.Naturab.Model.ClientModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO {
    public List<T> getAll() throws SQLException, ClassNotFoundException ;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public  boolean save(T object) throws SQLException, ClassNotFoundException;
    public T searchById(String id) throws SQLException, ClassNotFoundException ;
    public boolean update( T Object) throws SQLException, ClassNotFoundException ;
    public String generateNewID() throws SQLException, ClassNotFoundException;
    public int getcount() throws SQLException, ClassNotFoundException;
    public  int getcount1() throws SQLException, ClassNotFoundException;
    public  List<String> getIds() throws SQLException, ClassNotFoundException;
    public boolean updateQty(String id, int qty) throws SQLException, ClassNotFoundException;
    public  String getEmail(String id) throws SQLException, ClassNotFoundException;
    public List<Double> getPayment() throws SQLException, ClassNotFoundException;
    public List<String> getOId() throws SQLException, ClassNotFoundException;
    public List<String> get1productname() throws SQLException, ClassNotFoundException;
    public List<Integer> get1productcount() throws SQLException, ClassNotFoundException;


}
