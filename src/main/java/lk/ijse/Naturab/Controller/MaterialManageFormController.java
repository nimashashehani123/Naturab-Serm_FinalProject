package lk.ijse.Naturab.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class MaterialManageFormController {

    @FXML
    private JFXButton btnback;

    @FXML
    private JFXButton btnclose;

    @FXML
    private JFXButton btnsave;

    @FXML
    private JFXButton btnsearch;

    @FXML
    private TableColumn<?, ?> coldelete;

    @FXML
    private TableColumn<?, ?> coldescription;

    @FXML
    private TableColumn<?, ?> coledit;

    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private TableColumn<?, ?> colqty;

    @FXML
    private TableColumn<?, ?> colsid;

    @FXML
    private TableColumn<?, ?> colunitcost;

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
    private TextField txtsearchid;

    @FXML
    private JFXComboBox<?> txtsupplierid;

    @FXML
    private TextField txtunitcost;

    @FXML
    void btnbackOnAction(ActionEvent event) {

    }

    @FXML
    void btncloseOnAction(ActionEvent event) {

    }

    @FXML
    void btnsaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnsearchOnAction(ActionEvent event) {

    }

    @FXML
    void tblOnMouseClick(MouseEvent event) {

    }

    @FXML
    void tblOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void tblOnMouseExited(MouseEvent event) {

    }

}
