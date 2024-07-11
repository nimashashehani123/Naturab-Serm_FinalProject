package lk.ijse.Naturab.Bo.custom.Impl;

import lk.ijse.Naturab.Bo.custom.OperatorBo;
import lk.ijse.Naturab.Dao.DAOFactory;
import lk.ijse.Naturab.Dao.custom.MachineDAO;
import lk.ijse.Naturab.Dao.custom.OperatorDAO;
import lk.ijse.Naturab.Model.ClientModel;
import lk.ijse.Naturab.Model.OperatorModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperatorBoImpl implements OperatorBo {
    OperatorDAO operatorDAO = (OperatorDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.OPERATOR);
    MachineDAO machineDAO = (MachineDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MACHINE);
    @Override
    public List<OperatorModel> getAllOperators() throws SQLException, ClassNotFoundException {
        List<OperatorModel> allOperators = operatorDAO.getAll();
        ArrayList<OperatorModel> operatorModels = new ArrayList<>();
        for(OperatorModel operatorModel : allOperators){
            OperatorModel operatorModel1 = new OperatorModel(operatorModel.getOpId(),operatorModel.getName(),operatorModel.getAddress(),operatorModel.getTel(),operatorModel.getSalary(),operatorModel.getYrOfExperience(),operatorModel.getUserid(),operatorModel.getMaId());
            operatorModels.add(operatorModel1);
        }
        return operatorModels;
    }

    @Override
    public boolean deleteOperator(String id) throws SQLException, ClassNotFoundException {
        return operatorDAO.delete(id);
    }

    @Override
    public boolean saveOperator(OperatorModel operatorModel) throws SQLException, ClassNotFoundException {
        return operatorDAO.save(operatorModel);
    }

    @Override
    public OperatorModel OperatorsearchById(String id) throws SQLException, ClassNotFoundException {
        return operatorDAO.searchById(id);
    }

    @Override
    public boolean updateOperator(OperatorModel operatorModel) throws SQLException, ClassNotFoundException {
        return operatorDAO.update(operatorModel);
    }

    @Override
    public String generateNewOperatorID() throws SQLException, ClassNotFoundException {
        return operatorDAO.generateNewID();
    }

    @Override
    public int getOperatorcount() throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public int getOperatorcount1() throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public List<String> getOperatorIds() throws SQLException, ClassNotFoundException {
        return operatorDAO.getIds();
    }

    @Override
    public boolean updateQty(String id, int qty) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getOperatorEmail(String id) throws SQLException, ClassNotFoundException {
        return operatorDAO.getEmail(id);
    }
    @Override
    public List<String> getMachineIds() throws SQLException, ClassNotFoundException {
        return machineDAO.getIds();
    }
}
