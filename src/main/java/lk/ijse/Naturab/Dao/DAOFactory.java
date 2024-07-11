package lk.ijse.Naturab.Dao;

import lk.ijse.Naturab.Dao.custom.Impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CLIENT,EMPLOYEE,MACHINE,MATERIAL,MATERIALDETAIL,USER,WAREHOUSE,SUPPLIER,PRODUCT,OPERATOR,ORDER,ORDERDETAIL
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types) {
            case CLIENT:
                return new ClientDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case MACHINE:
                return new MachineDAOImpl();
            case MATERIAL:
                return new MaterialDAOImpl();
            case MATERIALDETAIL:
                return new MaterialDetailDAOImpl();
            case USER:
                return new UserDAOImpl();
            case WAREHOUSE:
                return new WarehouseDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case PRODUCT:
                return new ProductDAOImpl();
            case OPERATOR:
                return new OperatorDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDERDETAIL:
                return new OrderDetailDAOImpl();
            default:
                return null;
        }
    }
}
