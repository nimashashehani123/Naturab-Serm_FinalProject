package lk.ijse.Naturab.Bo.custom;

import lk.ijse.Naturab.Bo.SuperBo;
import lk.ijse.Naturab.Model.EmployeeModel;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeBo extends SuperBo {
    public List<EmployeeModel> getAllEmployee() throws SQLException, ClassNotFoundException ;
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;
    public  boolean saveEmployee(EmployeeModel object) throws SQLException, ClassNotFoundException;
    public EmployeeModel EmployeesearchById(String id) throws SQLException, ClassNotFoundException ;
    public boolean updateEmployee( EmployeeModel Object) throws SQLException, ClassNotFoundException ;
    public String generateNewEmployeeID() throws SQLException, ClassNotFoundException;

}
