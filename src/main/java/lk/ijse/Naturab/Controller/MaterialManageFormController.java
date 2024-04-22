package lk.ijse.Naturab.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class MaterialManageFormController {

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
    private TableColumn<?, ?> colqty;

    @FXML
    private TableColumn<?, ?> colsupplierid;

    @FXML
    private TableColumn<?, ?> colunitprice;

    @FXML
    private AnchorPane material;

    @FXML
    private TableView<?> tblmaterial;

    @FXML
    private TextField txtdescription;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtqty;

    @FXML
    private JFXComboBox<?> txtsupplierid;

    @FXML
    private TextField txtunitcost;

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
