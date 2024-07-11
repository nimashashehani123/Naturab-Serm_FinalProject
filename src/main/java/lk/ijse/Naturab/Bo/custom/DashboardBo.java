package lk.ijse.Naturab.Bo.custom;

import lk.ijse.Naturab.Bo.SuperBo;

import java.sql.SQLException;
import java.util.List;

public interface DashboardBo extends SuperBo {
    public int getMachinecount() throws SQLException, ClassNotFoundException;
    public  int getMachinecount1() throws SQLException, ClassNotFoundException;
    public List<Double> getOrderPayment() throws SQLException, ClassNotFoundException;
    public List<String> getOId() throws SQLException, ClassNotFoundException;
    public int getOrdercount() throws SQLException, ClassNotFoundException;
    public  int getOrdercount1() throws SQLException, ClassNotFoundException;
}
