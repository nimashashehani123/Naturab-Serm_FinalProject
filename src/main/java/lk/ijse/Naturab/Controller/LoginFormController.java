package lk.ijse.Naturab.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Naturab.Bo.BoFactory;
import lk.ijse.Naturab.Bo.custom.LoginBo;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {

    @FXML
    private JFXButton btnLogin;

    public ImageView closedeye;

    public Hyperlink linkRejistration;

    public TextField txtPassword;

    public TextField txtUserId;
    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane roots;
    boolean passwordVisible;
     Image openEyeImage;
     Image closedEyeImage;
     LoginBo loginBo = (LoginBo) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.LOGIN);
    public void initialize() {

       /*   openEyeImage = new Image(getClass().getResourceAsStream("/image/delete.png"));
          closedEyeImage = new Image(getClass().getResourceAsStream("/image/closedeye.png"));

          passwordVisible = false;*/
        txtUserId.requestFocus();
    }
    @FXML
    void onaction(ActionEvent event) {
        txtPassword.requestFocus();
    }
    @FXML
    void pwonaction(ActionEvent event) throws IOException {
       btnLoginOnAction(event);
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        String userId = txtUserId.getText();
        String pw = txtPassword.getText();

        String Password = null;
        try {
            navigateToTheDashboard();
            Password = loginBo.UsersearchById(userId);
            if (Password != null) {
                if(pw.equals(Password)){
                    navigateToTheDashboard();
                }else{
                    new Alert(Alert.AlertType.ERROR, "sorry! password is incorrect!").show();
                }
            }else {
                new Alert(Alert.AlertType.INFORMATION, "sorry! user id can't be find!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
        }
    }

    void closedeyeOnMouseClick(MouseEvent event) {
        if (passwordVisible) {
            txtPassword.setManaged(true);
            txtPassword.setVisible(true);
            closedeye.setImage(closedEyeImage);
        } else {
            txtPassword.setManaged(false);
            txtPassword.setVisible(false);
            closedeye.setImage(openEyeImage);
        }
        passwordVisible = !passwordVisible;
    }

    @FXML
    void linkRejistrationOnAction(ActionEvent event) throws IOException {
         AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/RejistrationForm.fxml"));
        roots.getChildren().clear();
        roots.getChildren().add(anchorPane);
    }





    private void navigateToTheDashboard() throws IOException {
        root.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/view/HomePageForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }


    @FXML
    void onmouseentered(MouseEvent event) {

    }


    @FXML
    void passwordmousereleased(MouseEvent event) {

    }

    @FXML
    void idmousereleased(MouseEvent event) {

    }

}
