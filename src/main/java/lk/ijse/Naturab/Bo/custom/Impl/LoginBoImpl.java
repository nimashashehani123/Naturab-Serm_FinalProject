package lk.ijse.Naturab.Bo.custom.Impl;

import lk.ijse.Naturab.Bo.custom.LoginBo;
import lk.ijse.Naturab.Dao.DAOFactory;
import lk.ijse.Naturab.Dao.custom.UserDAO;
import lk.ijse.Naturab.Model.UserModel;

import java.sql.SQLException;

public class  LoginBoImpl implements LoginBo {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    public String UsersearchById(String id) throws SQLException, ClassNotFoundException {
        UserModel userModel = userDAO.searchById(id);
        String password = userModel.getPassword();
        return password;
    }
}
