package lk.ijse.Naturab.Bo.custom;

import lk.ijse.Naturab.Bo.SuperBo;
import lk.ijse.Naturab.Model.ProductModel;

import java.sql.SQLException;
import java.util.List;

public interface ProductBo extends SuperBo {
    public List<ProductModel> getAllProduct() throws SQLException, ClassNotFoundException ;
    public boolean deleteProduct(String id) throws SQLException, ClassNotFoundException;
    public  boolean saveProduct(ProductModel productModel) throws SQLException, ClassNotFoundException;
    public ProductModel ProductsearchById(String id) throws SQLException, ClassNotFoundException ;
    public boolean updateProduct( ProductModel productModel) throws SQLException, ClassNotFoundException ;
    public String generateNewProductID() throws SQLException, ClassNotFoundException;
    public int getProductcount() throws SQLException, ClassNotFoundException;
    public  int getProductcount1() throws SQLException, ClassNotFoundException;
    public  List<String> getProductIds() throws SQLException, ClassNotFoundException;
    public boolean updateProductQty(String id, int qty) throws SQLException, ClassNotFoundException;
    public List<String> getMachineIds() throws SQLException, ClassNotFoundException;
    public List<String> getWarehouseIds() throws SQLException, ClassNotFoundException;
}
