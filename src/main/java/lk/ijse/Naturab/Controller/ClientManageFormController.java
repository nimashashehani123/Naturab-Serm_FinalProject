package lk.ijse.Naturab.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Naturab.Model.ClientModel;
import lk.ijse.Naturab.Model.Tm.ClientTm;
import lk.ijse.Naturab.Repositry.ClientRepo;
import javafx.scene.input.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ClientManageFormController {
    @FXML
    private JFXButton btnback;

    @FXML
    private JFXButton btnclose;

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
    private TableColumn<?, ?> coldelete;

    @FXML
    private TableColumn<?, ?> coledit;
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
    private TextField txtsearchid;
    private MouseEvent event;


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

        Clear();
        loadAllClients();

    }

    @FXML
    void btnsearchOnAction(ActionEvent event) {
        String id = txtsearchid.getText();
        ObservableList<ClientTm> obList = FXCollections.observableArrayList();

        ClientModel clientModel ;

        try {
            clientModel  = ClientRepo.searchById(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (clientModel != null) {
            Image delete = new Image("/image/delete.png");
            ImageView imageView1 = new ImageView(delete);
            imageView1.setCache(true);
            imageView1.setFitHeight(20);
            imageView1.setFitWidth(20);
            Image edit = new Image("/image/update.png");
            ImageView imageView2 = new ImageView(edit);
            imageView2.setCache(true);
            imageView2.setFitHeight(20);
            imageView2.setFitWidth(20);

            JFXButton btndelete = new JFXButton(" ",imageView1);
            btndelete.setCursor(Cursor.HAND);


            JFXButton btnedit = new JFXButton(" ",imageView2);
            btnedit.setCursor(Cursor.HAND);

            ClientTm tm = new ClientTm(
                    clientModel.getCId(),
                    clientModel.getName(),
                    clientModel.getTel(),
                    clientModel.getEmail(),
                    btndelete,
                    btnedit
            );

            obList.add(tm);


            tblclient.setItems(obList);

        } else {
            new Alert(Alert.AlertType.INFORMATION, "client not found!").show();
        }
        Clear();

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
        loadAllClients();
    }

    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("CId"));
        colname.setCellValueFactory(new PropertyValueFactory<>("Name"));
        coltel.setCellValueFactory(new PropertyValueFactory<>("Tel"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        coledit.setCellValueFactory(new PropertyValueFactory<>("btnedit"));
        coldelete.setCellValueFactory(new PropertyValueFactory<>("btndelete"));
    }

    private void loadAllClients() {
        ObservableList<ClientTm> obList = FXCollections.observableArrayList();


        try {
            List<ClientModel> clientList = ClientRepo.getAll();
            for (ClientModel clientModel : clientList) {
                if(clientModel != null){

                Image delete = new Image("/image/delete.png");
                ImageView imageView1 = new ImageView(delete);
                //imageView1.setCache(true);
                imageView1.setFitHeight(20);
                imageView1.setFitWidth(20);
                Image edit = new Image("/image/update.png");
                ImageView imageView2 = new ImageView(edit);
               // imageView2.setCache(true);
                imageView2.setFitHeight(20);
                imageView2.setFitWidth(20);

                JFXButton btndelete = new JFXButton(" ",imageView1);
                btndelete.setCursor(Cursor.HAND);

                    btndelete.setOnAction((e) -> {
                        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

                        if(type.orElse(no) == yes) {
                            String id = txtid.getText();
                            boolean x = false;
                            try {
                                x = ClientRepo.deleteClient(id);
                            } catch (SQLException e1) {
                                throw new RuntimeException(e1);
                            }
                            if(x){
                                new Alert(Alert.AlertType.CONFIRMATION,"Client deleted").show();
                                Clear();
                            }
                            Clear();
                            loadAllClients();
                        }
                    });

               JFXButton btnedit = new JFXButton(" ",imageView2);
                btnedit.setCursor(Cursor.HAND);

                    btnedit.setOnAction((e) -> {
                        String id = txtid.getText();
                        String name = txtname.getText();
                        String address = txtaddress.getText();
                        String tel = txttel.getText();
                        String email = txtemail.getText();

                        ClientModel clientModel1 = new ClientModel(id, name, address, tel, email);

                        try {
                            boolean isUpdated = ClientRepo.updateClient(clientModel1);
                            if(isUpdated) {
                                new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
                                Clear();
                            }
                        } catch (SQLException e1) {
                            new Alert(Alert.AlertType.ERROR, e1.getMessage()).show();
                        }
                            Clear();
                            loadAllClients();
                    });

                ClientTm tm = new ClientTm(
                        clientModel.getCId(),
                        clientModel.getName(),
                        clientModel.getTel(),
                        clientModel.getEmail(),
                        btndelete,
                        btnedit
                );

                obList.add(tm);
            }}

            tblclient.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btncloseOnAction(ActionEvent event) {
        txtsearchid.clear();
        Clear();
        loadAllClients();
    }
    @FXML
    void tblOnMouseClick(MouseEvent event) {
        if (!tblclient.getSelectionModel().isEmpty()) {
            ClientTm client = tblclient.getSelectionModel().getSelectedItem();
            ClientModel client1;
            try {
                client1 = ClientRepo.searchById(client.getCId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            txtid.setText(client1.getCId());
            txtname.setText(client1.getName());
            txtaddress.setText(client1.getAddress());
            txttel.setText(client1.getTel());
            txtemail.setText(client1.getEmail());
        }
    }
    @FXML
    void btnbackOnAction(ActionEvent event) {

    }
    @FXML
    void tblOnMouseEntered(MouseEvent event) {
    }

    @FXML
    void tblOnMouseExited(MouseEvent event) {

    }




}
