package lk.ijse.Naturab.Bo.custom.Impl;

import lk.ijse.Naturab.Bo.custom.SupplierBo;
import lk.ijse.Naturab.Dao.DAOFactory;
import lk.ijse.Naturab.Dao.custom.SupplierDAO;
import lk.ijse.Naturab.Model.ClientModel;
import lk.ijse.Naturab.Model.SupplierModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBoImpl implements SupplierBo {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    @Override
    public List<SupplierModel> getAllSupplier() throws SQLException, ClassNotFoundException {
        List<SupplierModel> allSuppliers = supplierDAO.getAll();
        ArrayList<SupplierModel> supplierModels = new ArrayList<>();
        for(SupplierModel supplierModel : allSuppliers){
            supplierModels.add( new SupplierModel(supplierModel.getSId(),supplierModel.getName(),supplierModel.getAddress(),supplierModel.getTel()));
        }
        return supplierModels;
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }

    @Override
    public boolean saveSupplier(SupplierModel supplierModel) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(supplierModel);
    }

    @Override
    public SupplierModel SuppliersearchById(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.searchById(id);
    }

    @Override
    public boolean updateSupplier(SupplierModel supplierModel) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(supplierModel);
    }

    @Override
    public String generateNewSupplierID() throws SQLException, ClassNotFoundException {
        return supplierDAO.generateNewID();
    }

    @Override
    public int getSuppliercount() throws SQLException, ClassNotFoundException {
        return supplierDAO.getcount();
    }

    @Override
    public int getSuppliercount1() throws SQLException, ClassNotFoundException {
        return supplierDAO.getcount1();
    }

    @Override
    public List<String> getSupplierIds() throws SQLException, ClassNotFoundException {
        return supplierDAO.getIds();
    }
}
