package lk.ijse.Naturab.Bo.custom;

import lk.ijse.Naturab.Bo.SuperBo;
import lk.ijse.Naturab.Model.OperatorModel;

import java.sql.SQLException;
import java.util.List;

public interface OperatorBo extends SuperBo {
    public List<OperatorModel> getAllOperators() throws SQLException, ClassNotFoundException ;
    public boolean deleteOperator(String id) throws SQLException, ClassNotFoundException;
    public  boolean saveOperator(OperatorModel operatorModel) throws SQLException, ClassNotFoundException;
    public OperatorModel OperatorsearchById(String id) throws SQLException, ClassNotFoundException ;
    public boolean updateOperator(OperatorModel operatorModel) throws SQLException, ClassNotFoundException ;
    public String generateNewOperatorID() throws SQLException, ClassNotFoundException;
    public int getOperatorcount() throws SQLException, ClassNotFoundException;
    public  int getOperatorcount1() throws SQLException, ClassNotFoundException;
    public  List<String> getOperatorIds() throws SQLException, ClassNotFoundException;

    public boolean updateQty(String id, int qty) throws SQLException, ClassNotFoundException;
    public  String getOperatorEmail(String id) throws SQLException, ClassNotFoundException;
    public List<String> getMachineIds() throws SQLException, ClassNotFoundException;

}
