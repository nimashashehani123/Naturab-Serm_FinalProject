package lk.ijse.Naturab.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HomePageFormController {
    @FXML
    private JFXButton btnClient;

    @FXML
    private JFXButton btndashboard;

    @FXML
    private JFXButton btnemployee;

    @FXML
    private JFXButton btnmachine;

    @FXML
    private JFXButton btnmaterial;

    @FXML
    private JFXButton btnoperator;

    @FXML
    private JFXButton btnorder;

    @FXML
    private JFXButton btnproduct;

    @FXML
    private JFXButton btnsupplier;
    @FXML
    private AnchorPane root;

    @FXML
    void btndashboardOnAction(ActionEvent event) {

    }

    @FXML
    void btnClientOnAction(ActionEvent event) throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/ClientManageForm.fxml"));
        root.getChildren().clear();
        root.getChildren().add(anchorPane);

    }
    @FXML
    void btnemployeeOnAction(ActionEvent event)  {
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("/view/EmployeeManageForm.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        root.getChildren().clear();
        root.getChildren().add(anchorPane);
    }

    @FXML
    void btnmachineOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/MachineManageForm.fxml"));
        root.getChildren().clear();
        root.getChildren().add(anchorPane);
    }

    @FXML
    void btnmaterialOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/MaterialManageForm.fxml"));
        root.getChildren().clear();
        root.getChildren().add(anchorPane);
    }

    @FXML
    void btnoperatorOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/OperatorManageForm.fxml"));
        root.getChildren().clear();
        root.getChildren().add(anchorPane);
    }

    @FXML
    void btnorderOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/OrderManageForm.fxml"));
        root.getChildren().clear();
        root.getChildren().add(anchorPane);

    }

    @FXML
    void btnproductOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/ProductManageForm.fxml"));
        root.getChildren().clear();
        root.getChildren().add(anchorPane);

    }

    @FXML
    void btnsupplierOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/SupplierManageForm.fxml"));
        root.getChildren().clear();
        root.getChildren().add(anchorPane);

    }


}
