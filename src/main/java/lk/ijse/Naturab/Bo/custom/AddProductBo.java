package lk.ijse.Naturab.Bo.custom;

import lk.ijse.Naturab.Bo.SuperBo;
import lk.ijse.Naturab.Model.AddProductModel;
import lk.ijse.Naturab.Model.MaterialDetailModel;
import lk.ijse.Naturab.Model.MaterialModel;

import java.sql.SQLException;
import java.util.List;

public interface AddProductBo extends SuperBo {
    public  boolean saveMaterialDetail(List<MaterialDetailModel> mdList) throws SQLException, ClassNotFoundException;
    public List<String> getMaterialIds() throws SQLException, ClassNotFoundException;
    public boolean addproduct(AddProductModel ap) throws SQLException;
    public  List<String> getMachineIds() throws SQLException, ClassNotFoundException;
    public MaterialModel MaterialsearchById(String id) throws SQLException, ClassNotFoundException;
    public List<String> getWarehouseIds() throws SQLException, ClassNotFoundException;
    public String generateNewID() throws SQLException, ClassNotFoundException;
}
