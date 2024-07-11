package lk.ijse.Naturab.Bo;

import lk.ijse.Naturab.Bo.custom.Impl.*;

public class BoFactory {

    private static BoFactory boFactory;
    private BoFactory(){
    }
    public static BoFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BoFactory() : boFactory;
    }

    public enum BOTypes{
        CLIENT,EMPLOYEE,MACHINE,DASHBOARD,MATERIAL,ADDPRODUCT,REJISTRATION,WAREHOUSE,SUPPLIER,PRODUCT,OPERATOR,ORDER,PLACEORDER,LOGIN
    }

    public SuperBo getBO(BOTypes types){
        switch (types){
            case CLIENT:
                return new ClientBoImpl();
            case EMPLOYEE:
                return new EmployeeBoImpl();
            case MACHINE:
                return new MachineBoImpl();
            case DASHBOARD:
                return new DashboardBoImpl();
            case MATERIAL:
                return new MaterialBoImpl();
            case ADDPRODUCT:
                return new AddProductBoImpl();
            case REJISTRATION:
                return new RejistrationBoImpl();
            case WAREHOUSE:
                return new WarehouseBoImpl();
            case SUPPLIER:
                return new SupplierBoImpl();
            case PRODUCT:
                return new ProductBoImpl();
            case OPERATOR:
                return new OperatorBoImpl();
            case ORDER:
                return new OrderBoImpl();
            case PLACEORDER:
                return new PlaceorderBoImpl();
            case LOGIN:
                return new LoginBoImpl();
            default:
                return null;
        }
    }}
