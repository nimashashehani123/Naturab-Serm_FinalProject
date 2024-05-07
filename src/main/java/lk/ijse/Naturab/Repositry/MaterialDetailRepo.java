package lk.ijse.Naturab.Repositry;

import lk.ijse.Naturab.Db.DbConnection;
import lk.ijse.Naturab.Model.MaterialDetailModel;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MaterialDetailRepo {
    public static boolean save(List<MaterialDetailModel> mdList) throws SQLException {
        for (MaterialDetailModel md : mdList) {
            boolean isSaved = save(md);
            if(!isSaved) {
                return false;
            }
        }
        return true;
    }

    private static boolean save(MaterialDetailModel md) throws SQLException {
        String sql = "INSERT INTO Material_Details VALUES(?, ?, ?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setString(1, md.getMId());
        pstm.setString(2, md.getPId());
        pstm.setInt(3, md.getQty());

        return pstm.executeUpdate() > 0;
    }
}
