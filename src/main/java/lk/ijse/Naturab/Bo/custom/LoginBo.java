package lk.ijse.Naturab.Bo.custom;

import lk.ijse.Naturab.Bo.SuperBo;

import java.sql.SQLException;

public interface LoginBo extends SuperBo {
    public String UsersearchById(String id) throws SQLException, ClassNotFoundException;
}
