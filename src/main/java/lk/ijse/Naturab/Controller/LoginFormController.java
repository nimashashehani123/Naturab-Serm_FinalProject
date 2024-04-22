package lk.ijse.Naturab.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private JFXButton btnLogin;

    @FXML
    public ImageView closedeye;

    @FXML
    public Hyperlink linkRejistration;

    @FXML
    public TextField txtPassword;

    @FXML
    public TextField txtUserId;
    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane roots;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/RejistrationForm.fxml"));
        roots.getChildren().clear();
        roots.getChildren().add(anchorPane);

    }

    @FXML
    void closedeyeOnMouseClick(MouseEvent event) {

    }

    @FXML
    void linkRejistrationOnAction(ActionEvent event) {

    }

}
