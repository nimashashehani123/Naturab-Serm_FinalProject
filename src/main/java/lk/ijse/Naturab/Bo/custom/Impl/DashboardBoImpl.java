package lk.ijse.Naturab.Bo.custom.Impl;

import lk.ijse.Naturab.Bo.custom.DashboardBo;
import lk.ijse.Naturab.Dao.DAOFactory;
import lk.ijse.Naturab.Dao.custom.MachineDAO;
import lk.ijse.Naturab.Dao.custom.OrderDAO;
import lk.ijse.Naturab.Model.ClientModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DashboardBoImpl implements DashboardBo {
    MachineDAO machineDAO = (MachineDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MACHINE);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    @Override
    public int getMachinecount() throws SQLException, ClassNotFoundException {
        return machineDAO.getcount();
    }

    @Override
    public int getMachinecount1() throws SQLException, ClassNotFoundException {
        return machineDAO.getcount1();
    }
    @Override
    public List<Double> getOrderPayment() throws SQLException, ClassNotFoundException {
        List<Double> plist = orderDAO.getPayment();
        ArrayList<Double> plist1 = new ArrayList<>();
        for(Double d : plist){
            plist1.add(d);
        }
        return plist1;
    }

    @Override
    public List<String> getOId() throws SQLException, ClassNotFoundException {
        List<String > oidlist = orderDAO.getOId();
        ArrayList<String > oid = new ArrayList<>();
        for(String s : oidlist){
            oid.add(s);
        }
        return oid;
    }
    @Override
    public int getOrdercount() throws SQLException, ClassNotFoundException {
        return orderDAO.getcount();
    }

    @Override
    public int getOrdercount1() throws SQLException, ClassNotFoundException {
        return orderDAO.getcount1();
    }
}
