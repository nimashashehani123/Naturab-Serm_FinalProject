package lk.ijse.Naturab.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import lk.ijse.Naturab.Model.ClientModel;
import lk.ijse.Naturab.Model.WarehouseModel;
import lk.ijse.Naturab.Repositry.ClientRepo;
import lk.ijse.Naturab.Repositry.WarehouseRepo;
import lk.ijse.Naturab.Util.Regex;

import java.sql.SQLException;

public class WarehouseManageFormController {

    @FXML
    private JFXButton btnback;

    @FXML
    private JFXButton btnsave;

    @FXML
    private TextField txtcapacity;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtlocation;

    @FXML
    void OnMouseClick(MouseEvent event) {

    }

    @FXML
    void btnbackOnAction(ActionEvent event) {

    }

    @FXML
    void btnsaveOnAction(ActionEvent event) {
        if (!isValied()){
            new Alert(Alert.AlertType.ERROR,"Pleace Check TextFilds !").show();
            return;
        }
        String WId = txtid.getText();
        String Location = txtlocation.getText();
        String Capacity = txtcapacity.getText();

        boolean x = false;
        WarehouseModel warehouseModel = new WarehouseModel(WId,Location,Capacity);
        try {

            x = WarehouseRepo.saveWarehouse(warehouseModel);
            if (x) {
                new Alert(Alert.AlertType.CONFIRMATION, "Client saved").show();

            }
        } catch(SQLException e){
            new Alert(Alert.AlertType.CONFIRMATION, "Duplicate ID ! Please Enter Another ID.").show();
        }

        Clear();


    }

    void Clear() {
        txtid.clear();
        txtlocation.clear();
        txtcapacity.clear();


    }


    @FXML
    void txtcapacityOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.Naturab.Util.TextField.ADDRESS,txtcapacity);

    }

    @FXML
    void txtidOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.Naturab.Util.TextField.ID,txtid);

    }

    @FXML
    void txtlocationOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.Naturab.Util.TextField.ADDRESS,txtlocation);

    }


    public boolean isValied(){
        if (!Regex.setTextColor(lk.ijse.Naturab.Util.TextField.ID,txtid)) return false;
        if (!Regex.setTextColor(lk.ijse.Naturab.Util.TextField.ADDRESS,txtlocation)) return false;
        if (!Regex.setTextColor(lk.ijse.Naturab.Util.TextField.ADDRESS,txtcapacity)) return false;
        return true;
    }


}
