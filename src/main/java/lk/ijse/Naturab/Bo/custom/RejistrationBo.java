package lk.ijse.Naturab.Bo.custom;

import lk.ijse.Naturab.Bo.SuperBo;
import lk.ijse.Naturab.Model.UserModel;

import java.sql.SQLException;

public interface RejistrationBo extends SuperBo {
    public String UsersearchById(String id) throws SQLException, ClassNotFoundException;
    public boolean saveUser(UserModel userModel) throws SQLException, ClassNotFoundException;
}
