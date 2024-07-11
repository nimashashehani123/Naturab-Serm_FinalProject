package lk.ijse.Naturab.Bo.custom.Impl;

import lk.ijse.Naturab.Bo.custom.ClientBo;
import lk.ijse.Naturab.Dao.DAOFactory;
import lk.ijse.Naturab.Dao.custom.ClientDAO;
import lk.ijse.Naturab.Dao.custom.Impl.ClientDAOImpl;
import lk.ijse.Naturab.Model.ClientModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientBoImpl implements ClientBo {
  //  ClientDAO clientDAO = new ClientDAOImpl();
        ClientDAO clientDAO = (ClientDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CLIENT);
    @Override
    public List<ClientModel> getAllClient() throws SQLException, ClassNotFoundException {
        List<ClientModel> allClients = clientDAO.getAll();
        ArrayList<ClientModel> clientModels = new ArrayList<>();
        for(ClientModel clientModel : allClients){
            ClientModel clientModel1 = new ClientModel(clientModel.getCId(),clientModel.getName(),clientModel.getAddress(),clientModel.getTel(),clientModel.getEmail());
            clientModels.add(clientModel1);
        }
        return clientModels;
    }

    @Override
    public boolean deleteClient(String id) throws SQLException, ClassNotFoundException {
        return clientDAO.delete(id);
    }

    @Override
    public boolean saveClient(ClientModel object) throws SQLException, ClassNotFoundException {
        return clientDAO.save(object);
    }

    @Override
    public ClientModel ClientsearchById(String id) throws SQLException, ClassNotFoundException {
        return clientDAO.searchById(id);
    }

    @Override
    public boolean updateClient(ClientModel Object) throws SQLException, ClassNotFoundException {
        return clientDAO.update(Object);
    }

    @Override
    public String generateNewClientID() throws SQLException, ClassNotFoundException {
        return clientDAO.generateNewID();
    }

    @Override
    public List<String> getClientIds() throws SQLException, ClassNotFoundException {
        return clientDAO.getIds();
    }

}
