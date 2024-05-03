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
import javafx.scene.input.MouseEvent;
import lk.ijse.Naturab.Model.*;
import lk.ijse.Naturab.Model.Tm.CartTm;
import lk.ijse.Naturab.Repositry.ClientRepo;
import lk.ijse.Naturab.Repositry.OrderRepo;
import lk.ijse.Naturab.Repositry.PlaceOrderRepo;
import lk.ijse.Naturab.Repositry.ProductRepo;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class PlacedOrderFormController {

    @FXML
    private JFXButton btnaddclient;

    @FXML
    private JFXButton btnaddtocart;

    @FXML
    private JFXButton btnback;

    @FXML
    private JFXButton btnclose;

    @FXML
    private ImageView imageviewdesign;

    @FXML
    private Label lbldesc;

    @FXML
    private JFXComboBox<String> lblid;

    @FXML
    private Label lblprice;

    @FXML
    private Label lblqtyonhand;

    @FXML
    private JFXComboBox<String> txtclientid;

    @FXML
    private TextField txtclientname;

    @FXML
    private TextField txtdate;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtqty;

    @FXML
    private JFXButton btnplaceorder;

    @FXML
    private TableColumn<?, ?> coldelete;

    @FXML
    private TableColumn<?, ?> coldescription;

    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private TableColumn<?, ?> colqty;

    @FXML
    private TableColumn<?, ?> coltotal;

    @FXML
    private TableColumn<?, ?> colunitprice;

    @FXML
    private Label lbltotal;

    @FXML
    private TableView<CartTm> tblcartview;

    private ObservableList<CartTm> obList = FXCollections.observableArrayList();

    @FXML
    void btnplaceorderOnAction(ActionEvent event) {

        String orderId = txtid.getText();
        String cusId = txtclientid.getValue();
        Date  date = Date.valueOf(LocalDate.now());
        Double total = Double.valueOf(lbltotal.getText());

        var order = new OrderModel(orderId,date,total,"Not Completed",cusId);

        List<OrderDetailModel> odList = new ArrayList<>();

        for (int i = 0; i < tblcartview.getItems().size(); i++) {
            CartTm tm = obList.get(i);

            OrderDetailModel od = new OrderDetailModel(
                    orderId,
                    tm.getId(),
                    tm.getQty()
            );

            odList.add(od);
        }

        PlaceOrderModel po = new PlaceOrderModel(order, odList);
        try {
            boolean isPlaced = PlaceOrderRepo.placeOrder(po);
            if(isPlaced) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order Placed!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Order Placed Unsuccessfully!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnaddclientOnAction(ActionEvent event) {

    }

    @FXML
    void btnbackOnAction(ActionEvent event) {

    }

    @FXML
    void btncloseOnAction(ActionEvent event) {

    }


    @FXML
    void btnaddtocartOnAction(ActionEvent event) {
        String id = lblid.getValue();
        String description = lbldesc.getText();
        int qty = Integer.parseInt(txtqty.getText());
        double unitPrice = Double.parseDouble(lblprice.getText());
        double total = qty * unitPrice;

        JFXButton btnRemove = new JFXButton("Remove");
        btnRemove.setCursor(Cursor.HAND);

        btnRemove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if(type.orElse(no) == yes) {
                int selectedIndex = tblcartview.getSelectionModel().getSelectedIndex();
                obList.remove(selectedIndex);

                tblcartview.refresh();
                calculateNetTotal();
            }
        });

        for (int i = 0; i < tblcartview.getItems().size(); i++) {
            if(id.equals(colid.getCellData(i))) {

                CartTm tm = obList.get(i);
                qty += tm.getQty();
                total = qty * unitPrice;

                tm.setQty(qty);
                tm.setTotal(total);

                tblcartview.refresh();

                calculateNetTotal();
                return;
            }
        }

        CartTm tm = new CartTm(id, description, unitPrice,  qty, total, btnRemove);
        obList.add(tm);

        tblcartview.setItems(obList);
        calculateNetTotal();
        txtqty.setText("");

    }

    private void calculateNetTotal() {
        int netTotal = 0;
        for (int i = 0; i < tblcartview.getItems().size(); i++) {
            netTotal += (double) coltotal.getCellData(i);
        }
        lbltotal.setText(String.valueOf(netTotal));
    }
    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colunitprice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        coltotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        coldelete.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));
    }

    @FXML
    void tblOnMouseClick(MouseEvent event) {

    }

    @FXML
    void txtclientidOnAction(ActionEvent event) {
        String id = txtclientid.getValue();
        try {
            ClientModel client = ClientRepo.searchById(id);

            txtclientname.setText(client.getName());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void lblidOnAction(ActionEvent event) {
        String code = lblid.getValue();

        try {
            ProductModel productModel = ProductRepo.searchById(code);
            if(productModel != null) {
                lbldesc.setText(productModel.getDescription());
                imageviewdesign.setImage(new Image(productModel.getDesign().toString()));
                lblprice.setText(String.valueOf(productModel.getUnitPrice()));
                lblqtyonhand.setText(String.valueOf(productModel.getQtyOnHand()));
            }

            txtqty.requestFocus();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void initialize() {
        setDate();
        getCurrentOrderId();
        getClientIds();
        getProductId();
        setCellValueFactory();
    }


    private void getClientIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = ClientRepo.getIds();

            for(String id : idList) {
                obList.add(id);
            }

            txtclientid.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void getProductId() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> codeList = ProductRepo.getIds();

            for (String code : codeList) {
                obList.add(code);
            }
            lblid.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getCurrentOrderId() {
        try {
            String currentId = OrderRepo.getCurrentId();

            String nextOrderId = generateNextOrderId(currentId);
            txtid.setText(nextOrderId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextOrderId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("O");
            int idNum = Integer.parseInt(split[1]);
            return "O" + ++idNum;
        }
        return "O1";
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        txtdate.setText(String.valueOf(now));
    }


}
