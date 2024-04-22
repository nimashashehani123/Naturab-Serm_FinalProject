package lk.ijse.Naturab.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class OperatorManageFormController {

    @FXML
    private JFXButton btndelete;

    @FXML
    private JFXButton btnsave;

    @FXML
    private JFXButton btnsearch;

    @FXML
    private JFXButton btnupdate;

    @FXML
    private TableColumn<?, ?> coladdress;

    @FXML
    private TableColumn<?, ?> colmachine;

    @FXML
    private TableColumn<?, ?> colname;

    @FXML
    private TableColumn<?, ?> coltel;

    @FXML
    private TableView<?> tbloperator;

    @FXML
    private TextField txtaddress;

    @FXML
    private TextField txtexperience;

    @FXML
    private TextField txtid;

    @FXML
    private JFXComboBox<?> txtmachineid;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtsalary;

    @FXML
    private TextField txttel;

    @FXML
    private JFXComboBox<?> txtuserid;

    @FXML
    void btndeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnsaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnsearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnupdateOnAction(ActionEvent event) {

    }

}
