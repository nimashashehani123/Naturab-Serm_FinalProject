package lk.ijse.Naturab.Bo.custom.Impl;

import lk.ijse.Naturab.Bo.custom.RejistrationBo;
import lk.ijse.Naturab.Dao.DAOFactory;
import lk.ijse.Naturab.Dao.custom.UserDAO;
import lk.ijse.Naturab.Model.UserModel;

import java.sql.SQLException;

public class RejistrationBoImpl implements RejistrationBo {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    public String UsersearchById(String id) throws SQLException, ClassNotFoundException {
        return (userDAO.searchById(id)).getPassword();
    }

    @Override
    public boolean saveUser(UserModel userModel) throws SQLException, ClassNotFoundException {
        return userDAO.save(userModel);
    }
}
