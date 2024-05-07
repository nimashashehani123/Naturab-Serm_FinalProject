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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Naturab.Model.ClientModel;
import lk.ijse.Naturab.Model.SupplierModel;
import lk.ijse.Naturab.Model.Tm.ClientTm;
import lk.ijse.Naturab.Model.Tm.SupplierTm;
import lk.ijse.Naturab.Repositry.ClientRepo;
import lk.ijse.Naturab.Repositry.SupplierRepo;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class SupplierManageFormController {
    @FXML
    private JFXButton btnback;

    @FXML
    private JFXButton btnclose;

    @FXML
    private JFXButton btnsave;

    @FXML
    private JFXButton btnsearch;

    @FXML
    private TableColumn<?, ?> coladdress;

    @FXML
    private TableColumn<?, ?> coldelete;

    @FXML
    private TableColumn<?, ?> coledit;

    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private TableColumn<?, ?> colname;

    @FXML
    private TableColumn<?, ?> coltel;

    @FXML
    private AnchorPane supplier;

    @FXML
    private TableView<SupplierTm> tblsupplier;

    @FXML
    private TextField txtaddress;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtsearchid;

    @FXML
    private TextField txttel;

    @FXML
    void btnsaveOnAction(ActionEvent event) {
        String SId = txtid.getText();
        String Name = txtname.getText();
        String Address = txtaddress.getText();
        String Tel = txttel.getText();
        SupplierModel supplierModel = new SupplierModel(SId,Name,Address,Tel);

        boolean x = false;
        try {
            x = SupplierRepo.saveSpplier(supplierModel);
            if(x){
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier saved").show();
                Clear();}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Clear();
        loadAllSuppliers();


    }

    @FXML
    void btnsearchOnAction(ActionEvent event) {
        String id = txtsearchid.getText();
        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();

        SupplierModel supplierModel;

        try {
            supplierModel  = SupplierRepo.searchById(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (supplierModel != null) {
            Image delete = new Image("/image/delete.png");
            ImageView imageView1 = new ImageView(delete);

            imageView1.setFitHeight(20);
            imageView1.setFitWidth(20);
            Image edit = new Image("/image/update.png");
            ImageView imageView2 = new ImageView(edit);

            imageView2.setFitHeight(20);
            imageView2.setFitWidth(20);

            JFXButton btndelete = new JFXButton(" ",imageView1);
            btndelete.setCursor(Cursor.HAND);
            btndelete.setOnAction((e) -> {
                ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

                if(type.orElse(no) == yes) {
                    String id1 = txtid.getText();
                    boolean x = false;
                    try {
                        x = SupplierRepo.deleteSupplier(id1);
                    } catch (SQLException e1) {
                        throw new RuntimeException(e1);
                    }
                    if(x){
                        new Alert(Alert.AlertType.CONFIRMATION,"Supplier deleted").show();

                    }
                    Clear();
                    loadAllSuppliers();
                }
            });


            JFXButton btnedit = new JFXButton(" ",imageView2);
            btnedit.setCursor(Cursor.HAND);
            btnedit.setOnAction((e) -> {
                String id2 = txtid.getText();
                String name = txtname.getText();
                String address = txtaddress.getText();
                String tel = txttel.getText();

                SupplierModel supplierModel1 = new SupplierModel(id2, name, address, tel);

                try {
                    boolean isUpdated = SupplierRepo.updateSupplier(supplierModel1);
                    if(isUpdated) {
                        new Alert(Alert.AlertType.CONFIRMATION, "supplier updated!").show();
                        Clear();
                    }
                } catch (SQLException e1) {
                    new Alert(Alert.AlertType.ERROR, e1.getMessage()).show();
                }
                Clear();
                loadAllSuppliers();
            });

            SupplierTm tm = new SupplierTm(
                    supplierModel.getSId(),
                    supplierModel.getName(),
                    supplierModel.getAddress(),
                    supplierModel.getTel(),
                    btndelete,
                    btnedit
            );

            obList.add(tm);


            tblsupplier.setItems(obList);

        } else {
            new Alert(Alert.AlertType.INFORMATION, "supplier not found!").show();
        }
        Clear();


    }
    void Clear() {
        txtid.clear();
        txtname.clear();
        txtaddress.clear();
        txttel.clear();

    }
    public void initialize() {
        setCellValueFactory();
        loadAllSuppliers();
    }
    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("SId"));
        colname.setCellValueFactory(new PropertyValueFactory<>("Name"));
        coltel.setCellValueFactory(new PropertyValueFactory<>("Tel"));
        coladdress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        coledit.setCellValueFactory(new PropertyValueFactory<>("btnedit"));
        coldelete.setCellValueFactory(new PropertyValueFactory<>("btndelete"));
    }
    private void loadAllSuppliers() {
        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();


        try {
            List<SupplierModel> supllierList = SupplierRepo.getAll();
            for (SupplierModel supplierModel: supllierList) {
                if(supplierModel != null){

                    Image delete = new Image("/image/delete.png");
                    ImageView imageView1 = new ImageView(delete);

                    imageView1.setFitHeight(20);
                    imageView1.setFitWidth(20);
                    Image edit = new Image("/image/update.png");
                    ImageView imageView2 = new ImageView(edit);

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
                                x = SupplierRepo.deleteSupplier(id);
                            } catch (SQLException e1) {
                                throw new RuntimeException(e1);
                            }
                            if(x){
                                new Alert(Alert.AlertType.CONFIRMATION,"Supplier deleted").show();

                            }
                            Clear();
                            loadAllSuppliers();
                        }
                    });

                    JFXButton btnedit = new JFXButton(" ",imageView2);
                    btnedit.setCursor(Cursor.HAND);

                    btnedit.setOnAction((e) -> {
                        String id = txtid.getText();
                        String name = txtname.getText();
                        String address = txtaddress.getText();
                        String tel = txttel.getText();

                        SupplierModel supplierModel1 = new SupplierModel(id, name, address, tel);

                        try {
                            boolean isUpdated = SupplierRepo.updateSupplier(supplierModel1);
                            if(isUpdated) {
                                new Alert(Alert.AlertType.CONFIRMATION, "supplier updated!").show();
                                Clear();
                            }
                        } catch (SQLException e1) {
                            new Alert(Alert.AlertType.ERROR, e1.getMessage()).show();
                        }
                        Clear();
                        loadAllSuppliers();
                    });

                    SupplierTm tm = new SupplierTm(
                            supplierModel.getSId(),
                            supplierModel.getName(),
                            supplierModel.getAddress(),
                            supplierModel.getTel(),
                            btndelete,
                            btnedit
                    );

                    obList.add(tm);
                }}

            tblsupplier.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void btncloseOnAction(ActionEvent event) {
        txtsearchid.clear();
        loadAllSuppliers();
    }

    @FXML
    void tblOnMouseClick(MouseEvent event) {
        if (!tblsupplier.getSelectionModel().isEmpty()) {
            SupplierTm supplier = tblsupplier.getSelectionModel().getSelectedItem();
            SupplierModel supplier1;
            try {
                supplier1 = SupplierRepo.searchById(supplier.getSId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            txtid.setText(supplier1.getSId());
            txtname.setText(supplier1.getName());
            txtaddress.setText(supplier1.getAddress());
            txttel.setText(supplier1.getTel());
        }

    }
    @FXML
    void btnbackOnAction(ActionEvent event) {

    }

    @FXML
    void txtaddressOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtidOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtnameOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtsearchidOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txttelOnKeyReleased(KeyEvent event) {

    }
}
