package lk.ijse.Naturab.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Naturab.Db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RejistrationFormController {

    @FXML
    public AnchorPane RejistrationForm;
    @FXML
    private JFXButton btnReg;
    @FXML
    private TextField txtpassword;

    @FXML
    private TextField txtuserid;

    @FXML
    private TextField txtusername;

    @FXML
    void btnRegOnAction(ActionEvent event) throws IOException {

        String userId = txtuserid.getText();
        String name = txtusername.getText();
        String password = txtpassword.getText();

        try {
            boolean isSaved = saveUser(userId, name, password);
            if(isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "user saved!").show();
                navigateTotheLoginPage();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void navigateTotheLoginPage() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"));
        RejistrationForm.getChildren().clear();
        RejistrationForm.getChildren().add(anchorPane);
    }

    private boolean saveUser(String userId, String name, String password) throws SQLException {
        String sql = "INSERT INTO User VALUES(?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, userId);
        pstm.setObject(2, name);
        pstm.setObject(3, password);

        return pstm.executeUpdate() > 0;
    }


    @FXML
    void txtidOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtnameOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtpasswordOnKeyReleased(KeyEvent event) {

    }

}
