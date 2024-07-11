package lk.ijse.Naturab.Bo.custom;

import lk.ijse.Naturab.Bo.SuperBo;
import lk.ijse.Naturab.Model.ClientModel;

import java.sql.SQLException;
import java.util.List;

public interface ClientBo extends SuperBo {
    public List<ClientModel> getAllClient() throws SQLException, ClassNotFoundException ;
    public boolean deleteClient(String id) throws SQLException, ClassNotFoundException;
    public  boolean saveClient(ClientModel object) throws SQLException, ClassNotFoundException;

    public ClientModel ClientsearchById(String id) throws SQLException, ClassNotFoundException ;
    public boolean updateClient( ClientModel Object) throws SQLException, ClassNotFoundException ;
    public String generateNewClientID() throws SQLException, ClassNotFoundException;
    public  List<String> getClientIds() throws SQLException, ClassNotFoundException;
}
