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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Naturab.Db.DbConnection;
import lk.ijse.Naturab.Repositry.EmployeeRepo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        String userId = txtUserId.getText();
        String pw = txtPassword.getText();

        try {
            checkCredential(userId, pw);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void checkCredential(String userId, String pw) throws SQLException, IOException {
        String sql = "SELECT UserId, Password FROM User WHERE UserId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, userId);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            String dbPw = resultSet.getString("Password");

            if(pw.equals(dbPw)) {
                EmployeeRepo.getcurrentuser(userId);
                navigateToTheDashboard();
            } else {
                new Alert(Alert.AlertType.ERROR, "sorry! password is incorrect!").show();
            }

        } else {
            new Alert(Alert.AlertType.INFORMATION, "sorry! user id can't be find!").show();
        }
    }

    @FXML
    void closedeyeOnMouseClick(MouseEvent event) {

    }

    @FXML
    void linkRejistrationOnAction(ActionEvent event) throws IOException {
         AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/RejistrationForm.fxml"));
        roots.getChildren().clear();
        roots.getChildren().add(anchorPane);
    }





    private void navigateToTheDashboard() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/HomePageForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }

}
