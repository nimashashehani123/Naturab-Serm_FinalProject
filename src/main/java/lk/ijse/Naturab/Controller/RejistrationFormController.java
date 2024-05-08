package lk.ijse.Naturab.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Naturab.Db.DbConnection;
import lk.ijse.Naturab.Model.UserModel;
import lk.ijse.Naturab.Repositry.UserRepo;
import lk.ijse.Naturab.Util.Regex;

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
        if (!isValied()){
            new Alert(Alert.AlertType.ERROR,"Pleace Check TextFilds !").show();
            return;
        }

        String userId = txtuserid.getText();
        String name = txtusername.getText();
        String password = txtpassword.getText();

        try {
            UserModel userModel = new UserModel(userId, name, password);
            boolean isSaved = UserRepo.saveUser(userModel);
            if(isSaved) {

                Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "user saved!");
                confirmation.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        try {
                            navigateTotheLoginPage();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void navigateTotheLoginPage() throws IOException {

        RejistrationForm.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }


    @FXML
    void txtidOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.Naturab.Util.TextField.ID,txtuserid);

    }

    @FXML
    void txtnameOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.Naturab.Util.TextField.NAME,txtusername);

    }

    @FXML
    void txtpasswordOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.Naturab.Util.TextField.PASSWORD,txtpassword);

    }

    public boolean isValied(){
        if (!Regex.setTextColor(lk.ijse.Naturab.Util.TextField.ID,txtuserid)) return false;
        if (!Regex.setTextColor(lk.ijse.Naturab.Util.TextField.NAME,txtusername)) return false;
        if (!Regex.setTextColor(lk.ijse.Naturab.Util.TextField.PASSWORD,txtpassword)) return false;
        return true;
    }

}
