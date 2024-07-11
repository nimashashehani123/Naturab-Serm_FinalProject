package lk.ijse.Naturab.Bo.custom.Impl;

import lk.ijse.Naturab.Bo.custom.WarehouseBo;
import lk.ijse.Naturab.Dao.DAOFactory;
import lk.ijse.Naturab.Dao.custom.MachineDAO;
import lk.ijse.Naturab.Dao.custom.WarehouseDAO;
import lk.ijse.Naturab.Model.WarehouseModel;

import java.sql.SQLException;
import java.util.List;

public class WarehouseBoImpl implements WarehouseBo {
    WarehouseDAO warehouseDAO = (WarehouseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.WAREHOUSE);

    @Override
    public boolean saveWarehouse(WarehouseModel warehouseModel) throws SQLException, ClassNotFoundException {
        return warehouseDAO.save(warehouseModel);
    }

    @Override
    public List<String> getWarehouseIds() throws SQLException, ClassNotFoundException {
        return warehouseDAO.getIds();
    }
}
