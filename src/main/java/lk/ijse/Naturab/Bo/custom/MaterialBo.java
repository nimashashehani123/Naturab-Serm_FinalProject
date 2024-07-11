package lk.ijse.Naturab.Bo.custom;

import lk.ijse.Naturab.Bo.SuperBo;
import lk.ijse.Naturab.Model.MaterialModel;

import java.sql.SQLException;
import java.util.List;

public interface MaterialBo extends SuperBo {
    public List<MaterialModel> getAllMaterial() throws SQLException, ClassNotFoundException ;
    public boolean deleteMaterial(String id) throws SQLException, ClassNotFoundException;
    public  boolean saveMaterial(MaterialModel materialModel) throws SQLException, ClassNotFoundException;
    public boolean updateMaterial( MaterialModel materialModel) throws SQLException, ClassNotFoundException ;
    public String generateNewMaterialID() throws SQLException, ClassNotFoundException;

    public MaterialModel MaterialsearchById(String id) throws SQLException, ClassNotFoundException;
    public int getMaterialcount() throws SQLException, ClassNotFoundException;
    public  int getMaterialcount1() throws SQLException, ClassNotFoundException;
    public List<String> getSupplierIds() throws SQLException, ClassNotFoundException;
}
