package lk.ijse.Naturab.Repositry;

import lk.ijse.Naturab.Db.DbConnection;
import lk.ijse.Naturab.Model.AddProductModel;

import java.sql.Connection;
import java.sql.SQLException;

public class AddProductRepo {
    public static boolean addproduct(AddProductModel ap) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isProductSaved = ProductRepo.saveProduct(ap.getProduct());
            if (isProductSaved) {
                System.out.println("productsave");
                boolean isQtyUpdated = MaterialRepo.update( ap.getMaList());
                if (isQtyUpdated) {
                    System.out.println("qty update");
                    boolean isMaterialDetailSaved = MaterialDetailRepo.save(ap.getMaList());
                    if (isMaterialDetailSaved) {
                        System.out.println("materialdetail update");
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
