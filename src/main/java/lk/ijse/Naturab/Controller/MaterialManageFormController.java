package lk.ijse.Naturab.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import lk.ijse.Naturab.Model.MaterialModel;
import lk.ijse.Naturab.Model.Tm.ClientTm;
import lk.ijse.Naturab.Model.Tm.MaterialTm;
import lk.ijse.Naturab.Repositry.ClientRepo;
import lk.ijse.Naturab.Repositry.MachineRepo;
import lk.ijse.Naturab.Repositry.MaterialRepo;
import lk.ijse.Naturab.Repositry.SupplierRepo;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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
    private TableView<MaterialTm> tblmaterial;

    @FXML
    private TextField txtdescription;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtqty;

    @FXML
    private TextField txtsearchid;

    @FXML
    private JFXComboBox<String> txtsupplierid;

    @FXML
    private TextField txtunitcost;

    @FXML
    void btnbackOnAction(ActionEvent event) {

    }

    @FXML
    void btncloseOnAction(ActionEvent event) {
        txtsearchid.clear();
        Clear();
        loadAllMaterial();

    }

    @FXML
    void btnsaveOnAction(ActionEvent event) {
        String MId = txtid.getText();
        String description = txtdescription.getText();
        double unitcost = Double.parseDouble(txtunitcost.getText());
        int qty = Integer.parseInt(txtqty.getText());
        String sid = txtsupplierid.getValue();

        MaterialModel materialModel = new MaterialModel(MId,description,unitcost,qty,sid);

        boolean x = false;
        try {
            x = MaterialRepo.saveMaterial(materialModel);
            if(x){
                new Alert(Alert.AlertType.CONFIRMATION,"Material saved").show();
                Clear();}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Clear();
        loadAllMaterial();


    }

    @FXML
    void btnsearchOnAction(ActionEvent event) {
        String id = txtsearchid.getText();
        ObservableList<MaterialTm> obList = FXCollections.observableArrayList();

        MaterialModel materialModel ;

        try {
            materialModel  = MaterialRepo.searchById(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (materialModel != null) {
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
                        x = MaterialRepo.deleteMaterial(id1);
                    } catch (SQLException e1) {
                        throw new RuntimeException(e1);
                    }
                    if(x){
                        new Alert(Alert.AlertType.CONFIRMATION,"Material deleted").show();
                        Clear();
                    }
                    Clear();
                    loadAllMaterial();
                }
            });



            JFXButton btnedit = new JFXButton(" ",imageView2);
            btnedit.setCursor(Cursor.HAND);

            btnedit.setOnAction((e) -> {
                String id2 = txtid.getText();
                String description = txtdescription.getText();
                double unitcost = Double.parseDouble(txtunitcost.getText());
                int qty = Integer.parseInt(txtqty.getText());
                String sid = txtsupplierid.getValue();

                MaterialModel materialModel1 = new MaterialModel(id2, description, unitcost,qty,sid);

                try {
                    boolean isUpdated = MaterialRepo.updateMaterial(materialModel1);
                    if(isUpdated) {
                        new Alert(Alert.AlertType.CONFIRMATION, "material updated!").show();
                        Clear();
                    }
                } catch (SQLException e1) {
                    new Alert(Alert.AlertType.ERROR, e1.getMessage()).show();
                }
                Clear();
                loadAllMaterial();
            });


            MaterialTm tm = new MaterialTm(
                    materialModel.getMId(),
                    materialModel.getDescription(),
                    materialModel.getUnitCost(),
                    materialModel.getQtyOnHand(),
                    materialModel.getSId(),
                    btndelete,
                    btnedit
            );

            obList.add(tm);


            tblmaterial.setItems(obList);

        } else {
            new Alert(Alert.AlertType.INFORMATION, "material not found!").show();
        }
        Clear();
    }
    void Clear() {
        txtid.clear();
        txtdescription.clear();
        txtunitcost.clear();
        txtqty.clear();
        txtsupplierid.setValue(null);

    }
    public void initialize() {
        getSupplierIds();
        setCellValueFactory();
        loadAllMaterial();
    }

    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("MId"));
        coldescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colunitcost.setCellValueFactory(new PropertyValueFactory<>("UnitCost"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        colsid.setCellValueFactory(new PropertyValueFactory<>("SId"));
        coledit.setCellValueFactory(new PropertyValueFactory<>("btnedit"));
        coldelete.setCellValueFactory(new PropertyValueFactory<>("btndelete"));
    }

    private void loadAllMaterial() {
        ObservableList<MaterialTm> obList = FXCollections.observableArrayList();


        try {
            List<MaterialModel> materialList = MaterialRepo.getAll();
            for (MaterialModel materialModel : materialList) {
                if(materialList != null){

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
                                x = MaterialRepo.deleteMaterial(id);
                            } catch (SQLException e1) {
                                throw new RuntimeException(e1);
                            }
                            if(x){
                                new Alert(Alert.AlertType.CONFIRMATION,"Material deleted").show();
                                Clear();
                            }
                            Clear();
                            loadAllMaterial();
                        }
                    });

                    JFXButton btnedit = new JFXButton(" ",imageView2);
                    btnedit.setCursor(Cursor.HAND);

                    btnedit.setOnAction((e) -> {
                        String id = txtid.getText();
                        String description = txtdescription.getText();
                        double unitcost = Double.parseDouble(txtunitcost.getText());
                        int qty = Integer.parseInt(txtqty.getText());
                        String sid = txtsupplierid.getValue();

                        MaterialModel materialModel1 = new MaterialModel(id, description, unitcost,qty,sid);

                        try {
                            boolean isUpdated = MaterialRepo.updateMaterial(materialModel1);
                            if(isUpdated) {
                                new Alert(Alert.AlertType.CONFIRMATION, "material updated!").show();
                                Clear();
                            }
                        } catch (SQLException e1) {
                            new Alert(Alert.AlertType.ERROR, e1.getMessage()).show();
                        }
                        Clear();
                        loadAllMaterial();
                    });

                    MaterialTm tm = new MaterialTm(
                            materialModel.getMId(),
                            materialModel.getDescription(),
                            materialModel.getUnitCost(),
                            materialModel.getQtyOnHand(),
                            materialModel.getSId(),
                            btndelete,
                            btnedit
                    );

                    obList.add(tm);
                }}

            tblmaterial.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void tblOnMouseClick(MouseEvent event) {
        if (!tblmaterial.getSelectionModel().isEmpty()) {
            MaterialTm material = tblmaterial.getSelectionModel().getSelectedItem();
            MaterialModel material1;
            try {
                material1 = MaterialRepo.searchById(material.getMId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            txtid.setText(material1.getMId());
            txtdescription.setText(material1.getDescription());
            txtunitcost.setText(String.valueOf(material1.getUnitCost()));
            txtqty.setText(String.valueOf(material1.getQtyOnHand()));
            txtsupplierid.setValue(material1.getSId());
        }

    }
    private void getSupplierIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = SupplierRepo.getIds();

            for(String id : idList) {
                obList.add(id);
            }

            txtsupplierid.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void tblOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void tblOnMouseExited(MouseEvent event) {

    }


    @FXML
    void txtdescriptionOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtidOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtqtyOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtsearchidOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtunitcostOnKeyReleased(KeyEvent event) {

    }

}
