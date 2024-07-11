package lk.ijse.Naturab.Bo.custom.Impl;

import lk.ijse.Naturab.Bo.custom.MaterialBo;
import lk.ijse.Naturab.Dao.DAOFactory;
import lk.ijse.Naturab.Dao.custom.ClientDAO;
import lk.ijse.Naturab.Dao.custom.MaterialDAO;
import lk.ijse.Naturab.Dao.custom.SupplierDAO;
import lk.ijse.Naturab.Model.ClientModel;
import lk.ijse.Naturab.Model.MaterialModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialBoImpl implements MaterialBo {
    MaterialDAO materialDAO = (MaterialDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MATERIAL);
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    @Override
    public List<MaterialModel> getAllMaterial() throws SQLException, ClassNotFoundException {
        List<MaterialModel> allMaterial = materialDAO.getAll();
        ArrayList<MaterialModel> materialModels = new ArrayList<>();
        for(MaterialModel materialModel : allMaterial){
            MaterialModel materialModel1 = new MaterialModel(materialModel.getMId(),materialModel.getDescription(),materialModel.getUnitCost(),materialModel.getQtyOnHand(),materialModel.getSId());
            materialModels.add(materialModel1);
        }
        return materialModels;
    }

    @Override
    public boolean deleteMaterial(String id) throws SQLException, ClassNotFoundException {
        return materialDAO.delete(id);
    }

    @Override
    public boolean saveMaterial(MaterialModel materialModel) throws SQLException, ClassNotFoundException {
        return materialDAO.save(materialModel);
    }



    @Override
    public boolean updateMaterial(MaterialModel materialModel) throws SQLException, ClassNotFoundException {
        return materialDAO.update(materialModel);
    }
    @Override
    public MaterialModel MaterialsearchById(String id) throws SQLException, ClassNotFoundException {
        return materialDAO.searchById(id);
    }

    @Override
    public String generateNewMaterialID() throws SQLException, ClassNotFoundException {
        return materialDAO.generateNewID();
    }

    @Override
    public int getMaterialcount() throws SQLException, ClassNotFoundException {
        return materialDAO.getcount();
    }

    @Override
    public int getMaterialcount1() throws SQLException, ClassNotFoundException {
        return materialDAO.getcount1();
    }
    @Override
    public List<String> getSupplierIds() throws SQLException, ClassNotFoundException {
        return supplierDAO.getIds();
    }


}