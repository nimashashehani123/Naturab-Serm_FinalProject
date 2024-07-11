package lk.ijse.Naturab.Bo.custom.Impl;

import lk.ijse.Naturab.Bo.custom.ProductBo;
import lk.ijse.Naturab.Dao.DAOFactory;
import lk.ijse.Naturab.Dao.custom.MachineDAO;
import lk.ijse.Naturab.Dao.custom.ProductDAO;
import lk.ijse.Naturab.Dao.custom.WarehouseDAO;
import lk.ijse.Naturab.Model.ClientModel;
import lk.ijse.Naturab.Model.ProductModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductBoImpl implements ProductBo {

    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT);
    MachineDAO machineDAO = (MachineDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MACHINE);
    WarehouseDAO warehouseDAO = (WarehouseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.WAREHOUSE);

    @Override
    public List<ProductModel> getAllProduct() throws SQLException, ClassNotFoundException {
        List<ProductModel> allProducts = productDAO.getAll();
        ArrayList<ProductModel> productModels = new ArrayList<>();
        for(ProductModel productModel : allProducts){
            ProductModel productModel1 = new ProductModel(productModel.getPId(),productModel.getDescription(),productModel.getDesign(),productModel.getUnitPrice(),productModel.getQtyOnHand(),productModel.getMaId(),productModel.getWId());
            productModels.add(productModel1);
        }
        return productModels;
    }

    @Override
    public boolean deleteProduct(String id) throws SQLException, ClassNotFoundException {
        return productDAO.delete(id);
    }

    @Override
    public boolean saveProduct(ProductModel productModel) throws SQLException, ClassNotFoundException {
        return productDAO.save(productModel);
    }

    @Override
    public ProductModel ProductsearchById(String id) throws SQLException, ClassNotFoundException {
        return productDAO.searchById(id);
    }

    @Override
    public boolean updateProduct(ProductModel productModel) throws SQLException, ClassNotFoundException {
        return productDAO.update(productModel);
    }

    @Override
    public String generateNewProductID() throws SQLException, ClassNotFoundException {
        return productDAO.generateNewID();
    }

    @Override
    public int getProductcount() throws SQLException, ClassNotFoundException {
        return productDAO.getcount();
    }

    @Override
    public int getProductcount1() throws SQLException, ClassNotFoundException {
        return productDAO.getcount1();
    }

    @Override
    public List<String> getProductIds() throws SQLException, ClassNotFoundException {
        return productDAO.getIds();
    }

    @Override
    public boolean updateProductQty(String id, int qty) throws SQLException, ClassNotFoundException {
        return productDAO.updateQty(id,qty);
    }
    @Override
    public List<String> getMachineIds() throws SQLException, ClassNotFoundException {
        return machineDAO.getIds();
    }
    @Override
    public List<String> getWarehouseIds() throws SQLException, ClassNotFoundException {
        return warehouseDAO.getIds();
    }

}
