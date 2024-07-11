package lk.ijse.Naturab.Bo.custom;

import lk.ijse.Naturab.Bo.SuperBo;
import lk.ijse.Naturab.Model.SupplierModel;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.SubmissionPublisher;

public interface SupplierBo extends SuperBo {
    public List<SupplierModel> getAllSupplier() throws SQLException, ClassNotFoundException ;
    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException;
    public  boolean saveSupplier(SupplierModel supplierModel) throws SQLException, ClassNotFoundException;
    public SupplierModel SuppliersearchById(String id) throws SQLException, ClassNotFoundException ;
    public boolean updateSupplier( SupplierModel supplierModel) throws SQLException, ClassNotFoundException ;
    public String generateNewSupplierID() throws SQLException, ClassNotFoundException;
    public int getSuppliercount() throws SQLException, ClassNotFoundException;
    public  int getSuppliercount1() throws SQLException, ClassNotFoundException;
    public  List<String> getSupplierIds() throws SQLException, ClassNotFoundException;

}
