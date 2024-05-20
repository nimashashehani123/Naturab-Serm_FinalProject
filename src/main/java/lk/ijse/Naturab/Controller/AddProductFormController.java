package lk.ijse.Naturab.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lk.ijse.Naturab.Model.*;
import lk.ijse.Naturab.Model.Tm.ProductDetailTm;
import lk.ijse.Naturab.Repositry.*;
import lk.ijse.Naturab.Util.Regex;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddProductFormController {

    @FXML
    private JFXButton btnaddwarehouse;

    @FXML
    private JFXButton btnaddmachine;

    @FXML
    private JFXButton btnaddproduct;

    @FXML
    private JFXButton btnaddtotbl;

    @FXML
    private TableColumn<?, ?> coldelete;

    @FXML
    private TableColumn<?, ?> coldescription;

    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private TableColumn<?, ?> colqty;

    @FXML
    private TableColumn<?, ?> colunitcost;

    @FXML
    private ImageView imageView;

    @FXML
    private Label lbldescription;

    @FXML
    private Label lblqtyonhand;

    @FXML
    private Label lblunitcost;

    @FXML
    private TableView<ProductDetailTm> tblmaterialcart;

    @FXML
    private JFXComboBox<String> txtMaterialid;

    @FXML
    private TextField txtdescription;

    @FXML
    private TextField txtid;

    @FXML
    private JFXComboBox<String> txtmachineid;

    @FXML
    private TextField txtqty;

    @FXML
    private TextField txtqtyonhand;

    @FXML
    private TextField txtunitprice;

    @FXML
    private JFXComboBox<String> txtwarehouseid;

    String url;
    String imagePath = null;

    private ObservableList<ProductDetailTm> obList = FXCollections.observableArrayList();


    @FXML
    void btnaddproductOnAction(ActionEvent event) {
        if (!isValied()){
            new Alert(Alert.AlertType.ERROR,"Pleace Check TextFilds !").show();
            return;
        }
        String PId = txtid.getText();
        String Description = txtdescription.getText();
        Double UnitPrice = Double.valueOf(txtunitprice.getText());
        int Qty = Integer.parseInt(txtqtyonhand.getText());
        String MaId = txtmachineid.getValue();
        String WId = txtwarehouseid.getValue();

        var product = new ProductModel(PId,Description,url,UnitPrice,Qty,MaId,WId);
        List<MaterialDetailModel> odList = new ArrayList<>();

        for (int i = 0; i < tblmaterialcart.getItems().size(); i++) {
            ProductDetailTm tm = obList.get(i);

            MaterialDetailModel md = new MaterialDetailModel(
                    PId,
                    tm.getMId(),
                    tm.getQty()
            );

            odList.add(md);
        }

        AddProductModel ad = new AddProductModel(product, odList);
        try {
            boolean isAdd = AddProductRepo.addproduct(ad);
            if(isAdd) {
                new Alert(Alert.AlertType.CONFIRMATION, "Product Save!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Product Save Unsuccessfully!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    @FXML
    void btnaddtotblOnAction(ActionEvent event) {
        if (!isValied1()){
            new Alert(Alert.AlertType.ERROR,"Pleace Check TextFilds !").show();
            return;
        }
        String id = txtMaterialid.getValue();
        String description = lbldescription.getText();
        int qty = Integer.parseInt(txtqty.getText());
        double unitcost = Double.parseDouble(lblunitcost.getText());

        JFXButton btnRemove = new JFXButton("Remove");
        btnRemove.setCursor(Cursor.HAND);

        btnRemove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if(type.orElse(no) == yes) {
                int selectedIndex = tblmaterialcart.getSelectionModel().getSelectedIndex();
                obList.remove(selectedIndex);

                tblmaterialcart.refresh();
            }
        });


        ProductDetailTm tm = new ProductDetailTm(id, description, unitcost,  qty, btnRemove);
        obList.add(tm);

        tblmaterialcart.setItems(obList);

       // txtqty.clear();

    }

    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("MId"));
        coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colunitcost.setCellValueFactory(new PropertyValueFactory<>("unitCost"));
        coldelete.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));
    }
    @FXML
    void imageonclick(MouseEvent event) {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image file");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            url = file.toURI().toString();
            Image image = new Image(url);
            imagePath = file.getPath();
            imageView.setImage(image);
        }
    }
    public void initialize() {
        getCurrentProductId();
        getMachineIds();
        getMaterialIds();
        getWarehouseIds();
        setCellValueFactory();
    }


    private void getMaterialIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = MaterialRepo.getIds();

            for(String id : idList) {
                obList.add(id);
            }

            txtMaterialid.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void getMachineIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = MachineRepo.getIds();

            for(String id : idList) {
                obList.add(id);
            }

            txtmachineid.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void getWarehouseIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = WarehouseRepo.getIds();

            for(String id : idList) {
                obList.add(id);
            }

            txtwarehouseid.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void getCurrentProductId() {
        try {
            String currentId = ProductRepo.getCurrentId();

            String nextOrderId = generateNextProductId(currentId);
            txtid.setText(nextOrderId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextProductId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("P00");
            if (split.length > 1) {
                int idNum = Integer.parseInt(split[1]);
                return "P00" + ++idNum;
            }
        }
        return "P001";
    }

    @FXML
    void tblOnMouseClick(MouseEvent event) {

    }

    @FXML
    void txtMaterialidOnAction(ActionEvent event) {
        String id = txtMaterialid.getValue();

        try {
            MaterialModel materialModel = MaterialRepo.searchById(id);
            if(materialModel != null) {
                lbldescription.setText(materialModel.getDescription());
                lblunitcost.setText(String.valueOf(materialModel.getUnitCost()));
                lblqtyonhand.setText(String.valueOf(materialModel.getQtyOnHand()));
            }

            txtqty.requestFocus();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnaddwarehouseOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/WarehouseManageForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.setWidth(1173);
        stage.setHeight(393);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                getWarehouseIds();;
            }
        });
        stage.show();
    }

    @FXML
    void btnaddmachineOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MachineManageForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.setWidth(1215);
        stage.setHeight(393);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                getMachineIds();
            }
        });
        stage.show();
    }

    @FXML
    void txtdescriptionOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.Naturab.Util.TextField.ADDRESS,txtdescription);
    }

    @FXML
    void txtidOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.Naturab.Util.TextField.ID,txtid);
    }

    @FXML
    void txtqtyOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.Naturab.Util.TextField.QTY,txtqty);
    }

    @FXML
    void txtqtyonhandOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.Naturab.Util.TextField.QTY,txtqtyonhand);
    }

    @FXML
    void txtunitpriceOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.Naturab.Util.TextField.SALARY,txtunitprice);
    }

    public boolean isValied(){
        if (!Regex.setTextColor(lk.ijse.Naturab.Util.TextField.ID,txtid)) return false;
        if (!Regex.setTextColor(lk.ijse.Naturab.Util.TextField.ADDRESS,txtdescription)) return false;
        if (!Regex.setTextColor(lk.ijse.Naturab.Util.TextField.QTY,txtqty)) return false;
        if (!Regex.setTextColor(lk.ijse.Naturab.Util.TextField.QTY,txtqtyonhand)) return false;
        if (!Regex.setTextColor(lk.ijse.Naturab.Util.TextField.SALARY,txtunitprice)) return false;

        return true;
    }
    public boolean isValied1(){
        if (!Regex.setTextColor(lk.ijse.Naturab.Util.TextField.ID,txtid)) return false;
        if (!Regex.setTextColor(lk.ijse.Naturab.Util.TextField.ADDRESS,txtdescription)) return false;
        if (!Regex.setTextColor(lk.ijse.Naturab.Util.TextField.QTY,txtqty)) return false;
        if (!Regex.setTextColor(lk.ijse.Naturab.Util.TextField.SALARY,txtunitprice)) return false;

        return true;
    }

}
