package lk.ijse.Naturab.Repositry;

import lk.ijse.Naturab.Db.DbConnection;
import lk.ijse.Naturab.Model.PlaceOrderModel;


import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderRepo{
    public static boolean placeOrder(PlaceOrderModel po) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isOrderSaved = OrderRepo.save(po.getOrder());
            if (isOrderSaved) {
                boolean isQtyUpdated = ProductRepo.update( po.getOdList());
                if (isQtyUpdated) {
                    boolean isOrderDetailSaved = OrderDetailRepo.save(po.getOdList());
                    if (isOrderDetailSaved) {
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
}
