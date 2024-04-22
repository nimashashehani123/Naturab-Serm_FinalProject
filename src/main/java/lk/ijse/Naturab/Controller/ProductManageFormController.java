package lk.ijse.Naturab.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ProductManageFormController {

    @FXML
    private JFXButton btndelete;

    @FXML
    private JFXButton btnsave;

    @FXML
    private JFXButton btnsearch;

    @FXML
    private JFXButton btnupdate;

    @FXML
    private TableColumn<?, ?> coldescription;

    @FXML
    private TableColumn<?, ?> colmaterial;

    @FXML
    private TableColumn<?, ?> colqty;

    @FXML
    private TableColumn<?, ?> colunitprice;

    @FXML
    private TableColumn<?, ?> colwarehouse;

    @FXML
    private AnchorPane product;

    @FXML
    private TableView<?> tblproduct;

    @FXML
    private TextField txtdescription;

    @FXML
    private TextField txtdesign;

    @FXML
    private TextField txtid;

    @FXML
    private JFXComboBox<?> txtmaterialid;

    @FXML
    private TextField txtqtyonhand;

    @FXML
    private TextField txtunitprice;

    @FXML
    private JFXComboBox<?> txtwarehouseid;

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
