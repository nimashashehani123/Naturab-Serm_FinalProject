package lk.ijse.Naturab.Bo.custom;

import lk.ijse.Naturab.Bo.SuperBo;
import lk.ijse.Naturab.Model.MachineModel;

import java.sql.SQLException;
import java.util.List;

public interface MachineBo extends SuperBo {
    public List<MachineModel> getAllMachine() throws SQLException, ClassNotFoundException ;
    public boolean deleteMachine(String id) throws SQLException, ClassNotFoundException;
    public  boolean saveMachine(MachineModel object) throws SQLException, ClassNotFoundException;
    public MachineModel MachinesearchById(String id) throws SQLException, ClassNotFoundException ;
    public boolean updateMachine( MachineModel Object) throws SQLException, ClassNotFoundException ;
    public String generateNewMachineID() throws SQLException, ClassNotFoundException;
    public int getMachinecount() throws SQLException, ClassNotFoundException;
    public  int getMachinecount1() throws SQLException, ClassNotFoundException;
    public String getOperatorEmail(String id) throws SQLException, ClassNotFoundException;

}
