package lk.ijse.Naturab.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Naturab.Model.ClientModel;
import lk.ijse.Naturab.Model.Tm.ClientTm;
import lk.ijse.Naturab.Repositry.ClientRepo;

import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

public class ClientManageFormController {

    @FXML
    private JFXButton btndelete;

    @FXML
    private JFXButton btnsave;

    @FXML
    private JFXButton btnsearch;

    @FXML
    private JFXButton btnupdate;

    @FXML
    private AnchorPane client;

    @FXML
    private TableColumn<?, ?> colemail;

    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private TableColumn<?, ?> colname;

    @FXML
    private TableColumn<?, ?> coltel;
    @FXML
    private TableView<ClientTm> tblclient;


    @FXML
    private TextField txtaddress;

    @FXML
    private TextField txtemail;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txttel;


    @FXML
    void btndeleteOnAction(ActionEvent event) {
        String id = txtid.getText();
        boolean x = false;
        try {
            x = ClientRepo.deleteClient(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(x){
            new Alert(Alert.AlertType.CONFIRMATION,"Client deleted").show();
            Clear();
        }

        loadAllCustomers();

    }

    @FXML
    void btnsaveOnAction(ActionEvent event) {
        String CId = txtid.getText();
        String Name = txtname.getText();
        String Address = txtaddress.getText();
        String Tel = txttel.getText();
        String Email = txtemail.getText();
        ClientModel clientModel = new ClientModel(CId,Name,Address,Tel,Email);

        boolean x = false;
        try {
            x = ClientRepo.saveClient(clientModel);
            if(x){
                new Alert(Alert.AlertType.CONFIRMATION,"Client saved").show();
                Clear();}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        loadAllCustomers();

    }

    @FXML
    void btnsearchOnAction(ActionEvent event) {
        String id = txtid.getText();

        ClientModel clientModel= null;
        try {
            clientModel = ClientRepo.searchById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (clientModel != null) {
            txtid.setText(clientModel.getCId());
            txtname.setText(clientModel.getName());
            txtaddress.setText(clientModel.getAddress());
            txttel.setText(clientModel.getTel());
            txtemail.setText(clientModel.getEmail());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
        }

        loadAllCustomers();

    }

    @FXML
    void btnupdateOnAction(ActionEvent event) {
        String id = txtid.getText();
        String name = txtname.getText();
        String address = txtaddress.getText();
        String tel = txttel.getText();
        String email = txtemail.getText();

        ClientModel clientModel = new ClientModel(id, name, address, tel, email);

        try {
            boolean isUpdated = ClientRepo.updateClient(clientModel);
            if(isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        loadAllCustomers();

    }
    void Clear() {
        txtid.clear();
        txtname.clear();
        txtaddress.clear();
        txttel.clear();
        txtemail.clear();

    }
    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
    }

    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("CId"));
        colname.setCellValueFactory(new PropertyValueFactory<>("Name"));
        coltel.setCellValueFactory(new PropertyValueFactory<>("Tel"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("Email"));
    }

    private void loadAllCustomers() {
        ObservableList<ClientTm> obList = FXCollections.observableArrayList();

        try {
            List<ClientModel> clientList = ClientRepo.getAll();
            for (ClientModel clientModel : clientList) {
                ClientTm tm = new ClientTm(
                        clientModel.getCId(),
                        clientModel.getName(),
                        clientModel.getTel(),
                        clientModel.getEmail()
                );

                obList.add(tm);
            }

            tblclient.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
