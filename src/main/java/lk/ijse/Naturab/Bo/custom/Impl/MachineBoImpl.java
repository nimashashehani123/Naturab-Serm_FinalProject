package lk.ijse.Naturab.Bo.custom.Impl;

import lk.ijse.Naturab.Bo.custom.MachineBo;
import lk.ijse.Naturab.Dao.DAOFactory;
import lk.ijse.Naturab.Dao.custom.ClientDAO;
import lk.ijse.Naturab.Dao.custom.MachineDAO;
import lk.ijse.Naturab.Dao.custom.OperatorDAO;
import lk.ijse.Naturab.Model.ClientModel;
import lk.ijse.Naturab.Model.MachineModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MachineBoImpl implements MachineBo {
    MachineDAO machineDAO = (MachineDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MACHINE);
    OperatorDAO operatorDAO = (OperatorDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.OPERATOR);

    @Override
    public List<MachineModel> getAllMachine() throws SQLException, ClassNotFoundException {
        List<MachineModel> allMachine = machineDAO.getAll();
        ArrayList<MachineModel> machineModels = new ArrayList<>();
        for(MachineModel machineModel : allMachine){
            machineModels.add( new MachineModel(machineModel.getMaId(),machineModel.getDescription(),machineModel.getType(),machineModel.getCapacity(),machineModel.getStatus()));
        }
        return machineModels;
    }

    @Override
    public boolean deleteMachine(String id) throws SQLException, ClassNotFoundException {
        return machineDAO.delete(id);
    }

    @Override
    public boolean saveMachine(MachineModel machineModel) throws SQLException, ClassNotFoundException {
        return machineDAO.save(machineModel);
    }

    @Override
    public MachineModel MachinesearchById(String id) throws SQLException, ClassNotFoundException {
        return machineDAO.searchById(id);
    }

    @Override
    public boolean updateMachine(MachineModel machineModel) throws SQLException, ClassNotFoundException {
        return machineDAO.update(machineModel);
    }

    @Override
    public String generateNewMachineID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public int getMachinecount() throws SQLException, ClassNotFoundException {
        return machineDAO.getcount();
    }

    @Override
    public int getMachinecount1() throws SQLException, ClassNotFoundException {
        return machineDAO.getcount1();
    }
    @Override
    public String getOperatorEmail(String id) throws SQLException, ClassNotFoundException {
        return operatorDAO.getEmail(id);
    }
}
