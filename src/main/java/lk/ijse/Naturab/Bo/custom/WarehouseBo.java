package lk.ijse.Naturab.Bo.custom;

import lk.ijse.Naturab.Bo.SuperBo;
import lk.ijse.Naturab.Model.WarehouseModel;

import java.sql.SQLException;
import java.util.List;

public interface WarehouseBo extends SuperBo {
    public  boolean saveWarehouse(WarehouseModel warehouseModel) throws SQLException, ClassNotFoundException;
    public List<String> getWarehouseIds() throws SQLException, ClassNotFoundException;
}
