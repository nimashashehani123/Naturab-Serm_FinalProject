package lk.ijse.Naturab.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lk.ijse.Naturab.Model.ClientModel;
import lk.ijse.Naturab.Model.OrderModel;
import lk.ijse.Naturab.Model.ProductModel;
import lk.ijse.Naturab.Model.Tm.ClientTm;
import lk.ijse.Naturab.Model.Tm.ProductTm;
import lk.ijse.Naturab.Repositry.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ProductManageFormController {

    @FXML
    private JFXButton btnclose;

    @FXML
    private JFXButton btnaddproduct;

    @FXML
    private JFXButton btnsearch;

    @FXML
    private TableColumn<?, ?> coldelete;

    @FXML
    private TableColumn<?, ?> coldescription;

    @FXML
    private TableColumn<?, ?> coldesign;

    @FXML
    private TableColumn<?, ?> coledit;

    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private TableColumn<?, ?> colqty;

    @FXML
    private TableColumn<?, ?> colunitprice;

    @FXML
    private TableColumn<?, ?> colwarehouseid;



    @FXML
    private AnchorPane product;

    @FXML
    private TableView<ProductTm> tblcproduct;


    @FXML
    private TextField txtid;

    @FXML
    private JFXComboBox<String> txtmachineid;

    @FXML
    private JFXComboBox<String> txtwarehouseid;




    @FXML
    private TextField txtqty;

    @FXML
    private TextField txtsearchid;

    @FXML
    private TextField txtunitprice;


    @FXML
    void btncloseOnAction(ActionEvent event) {
        txtsearchid.clear();
        Clear();
        loadAllProducts();

    }

   /* @FXML
    void btnsaveOnAction(ActionEvent event) {

        boolean x = false;
        try {
            x = ProductRepo.saveProduct(productModel);
            if(x){
                new Alert(Alert.AlertType.CONFIRMATION,"Product saved").show();
                Clear();}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Clear();
        loadAllProducts();

    }*/
    void Clear() {
        txtid.clear();
        txtunitprice.clear();
        txtqty.clear();
        txtmachineid.setValue(null);
        txtwarehouseid.setValue(null);

    }
    public void initialize() {
        getMachineIds();
        getWarehouseIds();
        setCellValueFactory();
        loadAllProducts();
    }

    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("PId"));
        coldescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        coldesign.setCellValueFactory(new PropertyValueFactory<>("Design"));
        colunitprice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("QtyOnHand"));
        colwarehouseid.setCellValueFactory(new PropertyValueFactory<>("WId"));
        coledit.setCellValueFactory(new PropertyValueFactory<>("btnedit"));
        coldelete.setCellValueFactory(new PropertyValueFactory<>("btndelete"));
    }


    @FXML
    void btnsearchOnAction(ActionEvent event) {
        String id = txtsearchid.getText();
        ObservableList<ProductTm> obList = FXCollections.observableArrayList();

        ProductModel productModel ;

        try {
            productModel  = ProductRepo.searchById(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (productModel != null) {
            Image delete = new Image("/image/delete.png");
            ImageView imageView1 = new ImageView(delete);

            imageView1.setFitHeight(20);
            imageView1.setFitWidth(20);
            Image edit = new Image("/image/update.png");
            ImageView imageView2 = new ImageView(edit);

            imageView2.setFitHeight(20);
            imageView2.setFitWidth(20);

            Image design = new Image(productModel.getDesign());
            ImageView imageView3 = new ImageView(design);

            imageView3.setFitHeight(100);
            imageView3.setFitWidth(100);

            JFXButton btndelete = new JFXButton(" ",imageView1);
            btndelete.setCursor(Cursor.HAND);

            btndelete.setOnAction((e) -> {
                ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

                if(type.orElse(no) == yes) {
                    String Pid = txtid.getText();
                    boolean x = false;
                    try {
                        x = ProductRepo.deleteProduct(Pid);
                    } catch (SQLException e1) {
                        throw new RuntimeException(e1);
                    }
                    if(x){
                        new Alert(Alert.AlertType.CONFIRMATION,"Product deleted").show();
                        Clear();
                    }
                    Clear();
                    loadAllProducts();
                }
            });


            JFXButton btnedit = new JFXButton(" ",imageView2);
            btnedit.setCursor(Cursor.HAND);

            btnedit.setOnAction((e) -> {
                String PId = txtid.getText();
                ProductModel product;
                try {
                    product = ProductRepo.searchById(PId);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                String description = product.getDescription();
                String design1 = product.getDesign();
                double UnitPrice = Double.parseDouble(txtunitprice.getText());
                int Qty = Integer.parseInt(txtqty.getText());
                String MaId = txtmachineid.getValue();
                String WId = txtwarehouseid.getValue();


                ProductModel productModel1 = new ProductModel(PId,  description, design1, UnitPrice, Qty, MaId, WId);

                try {
                    boolean isUpdated = ProductRepo.updateProduct(productModel1);
                    if(isUpdated) {
                        new Alert(Alert.AlertType.CONFIRMATION, "product updated!").show();
                        Clear();
                    }
                } catch (SQLException e1) {
                    new Alert(Alert.AlertType.ERROR, e1.getMessage()).show();
                }
                Clear();
                loadAllProducts();
            });


            ProductTm tm = new ProductTm(
                    productModel.getPId(),
                    productModel.getDescription(),
                    imageView3,
                    productModel.getUnitPrice(),
                    productModel.getQtyOnHand(),
                    productModel.getWId(),
                    btndelete,
                    btnedit
            );

            obList.add(tm);


            tblcproduct.setItems(obList);

        } else {
            new Alert(Alert.AlertType.INFORMATION, "product not found!").show();
        }
        Clear();

    }

    private void loadAllProducts() {
        ObservableList<ProductTm> obList = FXCollections.observableArrayList();


        try {
            List<ProductModel> productList = ProductRepo.getAll();
            for (ProductModel productModel : productList) {
                if(productModel != null){

                    Image delete = new Image("/image/delete.png");
                    ImageView imageView1 = new ImageView(delete);

                    imageView1.setFitHeight(20);
                    imageView1.setFitWidth(20);
                    Image edit = new Image("/image/update.png");
                    ImageView imageView2 = new ImageView(edit);

                    imageView2.setFitHeight(20);
                    imageView2.setFitWidth(20);

                    Image design = new Image(productModel.getDesign());
                    ImageView imageView3 = new ImageView(design);

                    imageView3.setFitHeight(100);
                    imageView3.setFitWidth(100);


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
                                x = ProductRepo.deleteProduct(id);
                            } catch (SQLException e1) {
                                throw new RuntimeException(e1);
                            }
                            if(x){
                                new Alert(Alert.AlertType.CONFIRMATION,"Product deleted").show();
                                Clear();
                            }
                            Clear();
                            loadAllProducts();
                        }
                    });

                    JFXButton btnedit = new JFXButton(" ",imageView2);
                    btnedit.setCursor(Cursor.HAND);

                    btnedit.setOnAction((e) -> {
                        String PId = txtid.getText();
                        ProductModel product;
                        try {
                            product = ProductRepo.searchById(PId);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        String description = product.getDescription();
                        String design1 = product.getDesign();
                        double UnitPrice = Double.parseDouble(txtunitprice.getText());
                        int Qty = Integer.parseInt(txtqty.getText());
                        String MaId = txtmachineid.getValue();
                        String WId = txtwarehouseid.getValue();


                        ProductModel productModel1 = new ProductModel(PId,  description, design1, UnitPrice, Qty, MaId, WId);

                        try {
                            boolean isUpdated = ProductRepo.updateProduct(productModel1);
                            if(isUpdated) {
                                new Alert(Alert.AlertType.CONFIRMATION, "product updated!").show();
                                Clear();
                            }
                        } catch (SQLException e1) {
                            new Alert(Alert.AlertType.ERROR, e1.getMessage()).show();
                        }
                        Clear();
                        loadAllProducts();
                    });

                    ProductTm tm = new ProductTm(
                            productModel.getPId(),
                            productModel.getDescription(),
                            imageView3,
                            productModel.getUnitPrice(),
                            productModel.getQtyOnHand(),
                            productModel.getWId(),
                            btndelete,
                            btnedit
                    );

                    obList.add(tm);
                }}

            tblcproduct.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void tblOnMouseClick(MouseEvent event) {
        if (!tblcproduct.getSelectionModel().isEmpty()) {
            ProductTm product = tblcproduct.getSelectionModel().getSelectedItem();
            ProductModel product1;
            try {
                product1 = ProductRepo.searchById(product.getPId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            txtid.setText(product1.getPId());
            txtunitprice.setText(String.valueOf(product1.getUnitPrice()));
            txtqty.setText(String.valueOf(product1.getQtyOnHand()));
            txtmachineid.setValue(product1.getMaId());
            txtwarehouseid.setValue(product1.getWId());

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

    @FXML
    void btnaddproductOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/AddProductForm.fxml"));
        product.getChildren().clear();
        product.getChildren().add(anchorPane);


    }

    public void btnbackOnAction(ActionEvent actionEvent) {
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
    void txtunitpriceOnKeyReleased(KeyEvent event) {

    }

}


