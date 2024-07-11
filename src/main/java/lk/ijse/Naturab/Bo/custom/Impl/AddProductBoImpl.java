package lk.ijse.Naturab.Bo.custom.Impl;

import lk.ijse.Naturab.Bo.custom.AddProductBo;
import lk.ijse.Naturab.Dao.DAOFactory;
import lk.ijse.Naturab.Dao.custom.*;
import lk.ijse.Naturab.Db.DbConnection;
import lk.ijse.Naturab.Model.AddProductModel;
import lk.ijse.Naturab.Model.MaterialDetailModel;
import lk.ijse.Naturab.Model.MaterialModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AddProductBoImpl implements AddProductBo {
    MaterialDetailDAO materialDetailDAO = (MaterialDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MATERIALDETAIL);
    MaterialDAO materialDAO = (MaterialDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MATERIAL);
    MachineDAO machineDAO = (MachineDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MACHINE);
    WarehouseDAO warehouseDAO = (WarehouseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.WAREHOUSE);
    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT);


    public  boolean saveMaterialDetail(List<MaterialDetailModel> mdList) throws SQLException, ClassNotFoundException {
        for (MaterialDetailModel md : mdList) {
            boolean isSaved = materialDetailDAO.save(md);
            if(!isSaved) {
                return false;
            }
        }
        return true;
    }
    public List<String> getMaterialIds() throws SQLException, ClassNotFoundException {
        return materialDAO.getIds();
    }

    public boolean addproduct(AddProductModel ap) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isProductSaved = productDAO.save(ap.getProduct());
            if (isProductSaved) {
                System.out.println("productsave");
                boolean isQtyUpdated = IsupdateQty( ap.getMaList());
                if (isQtyUpdated) {
                    System.out.println("qty update");
                    boolean isMaterialDetailSaved = saveMaterialDetail(ap.getMaList());
                    if (isMaterialDetailSaved) {
                        System.out.println("materialdetail update");
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public List<String> getMachineIds() throws SQLException, ClassNotFoundException {
        return machineDAO.getIds();
    }
    @Override
    public MaterialModel MaterialsearchById(String id) throws SQLException, ClassNotFoundException {
        return materialDAO.searchById(id);
    }

    public boolean IsupdateQty(List<MaterialDetailModel> mdList) throws SQLException, ClassNotFoundException {
        for (MaterialDetailModel md : mdList) {
            boolean isUpdateQty = materialDAO.updateQty(md.getMId(), md.getQty());
            if(!isUpdateQty) {
                return false;
            }
        }
        return true;
    }
    @Override
    public List<String> getWarehouseIds() throws SQLException, ClassNotFoundException {
        return warehouseDAO.getIds();
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return productDAO.generateNewID();
    }

}
