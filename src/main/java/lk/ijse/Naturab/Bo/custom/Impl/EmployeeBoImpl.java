package lk.ijse.Naturab.Bo.custom.Impl;

import lk.ijse.Naturab.Bo.custom.EmployeeBo;
import lk.ijse.Naturab.Dao.DAOFactory;
import lk.ijse.Naturab.Dao.custom.ClientDAO;
import lk.ijse.Naturab.Dao.custom.EmployeeDAO;
import lk.ijse.Naturab.Model.ClientModel;
import lk.ijse.Naturab.Model.EmployeeModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBoImpl implements EmployeeBo {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);
    @Override
    public List<EmployeeModel> getAllEmployee() throws SQLException, ClassNotFoundException {
        List<EmployeeModel> allEmp = employeeDAO.getAll();
        ArrayList<EmployeeModel> employeeModels = new ArrayList<>();
        for(EmployeeModel employeeModel : allEmp){
            employeeModels.add(new EmployeeModel(employeeModel.getEId(),employeeModel.getName(),employeeModel.getAddress(),employeeModel.getTel(),employeeModel.getSalary(),employeeModel.getYrOfExperience(),employeeModel.getUserid()));
        }
        return employeeModels;
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public boolean saveEmployee(EmployeeModel object) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(object);
    }

    @Override
    public EmployeeModel EmployeesearchById(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.searchById(id);
    }

    @Override
    public boolean updateEmployee(EmployeeModel Object) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(Object);
    }

    @Override
    public String generateNewEmployeeID() throws SQLException, ClassNotFoundException {
        return employeeDAO.generateNewID();
    }
}
