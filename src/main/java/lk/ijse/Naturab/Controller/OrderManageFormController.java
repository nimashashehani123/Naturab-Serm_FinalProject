package lk.ijse.Naturab.Controller;

import com.jfoenix.controls.JFXButton;
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
import lk.ijse.Naturab.Model.ClientModel;
import lk.ijse.Naturab.Model.OrderModel;
import lk.ijse.Naturab.Model.Tm.ClientTm;
import lk.ijse.Naturab.Model.Tm.OrderTm;
import lk.ijse.Naturab.Repositry.ClientRepo;
import lk.ijse.Naturab.Repositry.OrderRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class OrderManageFormController {

    @FXML
    private JFXButton btnaddorder;

    @FXML
    private JFXButton btnback;

    @FXML
    private JFXButton btnclose;

    @FXML
    private JFXButton btnsearch;

    @FXML
    private TableColumn<?, ?> coldelete;

    @FXML
    private TableColumn<?, ?> coledit;

    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private TableColumn<?, ?> colpaymentamount;

    @FXML
    private TableColumn<?, ?> colplaceddate;

    @FXML
    private TableColumn<?, ?> colstatus;

    @FXML
    private TableColumn<?, ?> colcid;

    @FXML
    private AnchorPane order;

    @FXML
    private TableView<OrderTm> tblorder;

    @FXML
    private TextField txtsearchid;

    @FXML
    private ChoiceBox<String> txtstatus;



    @FXML
    private TextField txtid;

    @FXML
    void btnaddorderOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/PlacedOrderForm.fxml"));
        order.getChildren().clear();
        order.getChildren().add(anchorPane);

    }

    @FXML
    void btnbackOnAction(ActionEvent event) {

    }

    @FXML
    void btncloseOnAction(ActionEvent event) {
        txtsearchid.clear();
        Clear();
        loadAllOrders();

    }

    @FXML
    void btnsearchOnAction(ActionEvent event) {
        String id = txtsearchid.getText();
        ObservableList<OrderTm> obList = FXCollections.observableArrayList();

        OrderModel orderModel;

        try {
            orderModel  = OrderRepo.searchById(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (orderModel != null) {
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

            btndelete.setOnAction((e) -> {
                ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

                if(type.orElse(no) == yes) {
                    String id1 = txtid.getText();
                    boolean x = false;
                    try {
                        x = OrderRepo.deleteOrder(id1);
                    } catch (SQLException e1) {
                        throw new RuntimeException(e1);
                    }
                    if(x){
                        new Alert(Alert.AlertType.CONFIRMATION,"Order deleted").show();
                        Clear();
                    }
                    Clear();
                    loadAllOrders();
                }
            });



            JFXButton btnedit = new JFXButton(" ",imageView2);
            btnedit.setCursor(Cursor.HAND);
            btnedit.setCursor(Cursor.HAND);
            btnedit.setOnAction((e) -> {
                String id2 = txtid.getText();
                OrderModel order;
                try {
                    order = OrderRepo.searchById(id);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                Date placedDate = order.getPlacedDate();
                String cId = order.getCId();
                double paymentAmount = order.getPaymentAmount();
                String status = txtstatus.getValue();


                OrderModel orderModel1 = new OrderModel(id2, placedDate,paymentAmount,status, cId);

                try {
                    boolean isUpdated = OrderRepo.updateOrder(orderModel1);
                    if(isUpdated) {
                        new Alert(Alert.AlertType.CONFIRMATION, "order updated!").show();
                        Clear();
                    }
                } catch (SQLException e1) {
                    new Alert(Alert.AlertType.ERROR, e1.getMessage()).show();
                }
                Clear();
                loadAllOrders();
            });






            OrderTm tm = new OrderTm(
                    orderModel.getOId(),
                    orderModel.getPlacedDate(),
                    orderModel.getPaymentAmount(),
                    orderModel.getStatus(),
                    orderModel.getCId(),
                    btnedit,
                    btndelete
            );

            obList.add(tm);


            tblorder.setItems(obList);

        } else {
            new Alert(Alert.AlertType.INFORMATION, "order not found!").show();
        }
        Clear();


    }

    public void initialize() {
        txtstatus.getItems().addAll("Completed","Not Completed", "On Prepare");
        setCellValueFactory();
        loadAllOrders();
    }

    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("OId"));
        colplaceddate.setCellValueFactory(new PropertyValueFactory<>("PlacedDate"));
        colpaymentamount.setCellValueFactory(new PropertyValueFactory<>("PaymentAmount"));
        colstatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        colcid.setCellValueFactory(new PropertyValueFactory<>("CId"));
        coledit.setCellValueFactory(new PropertyValueFactory<>("btnedit"));
        coldelete.setCellValueFactory(new PropertyValueFactory<>("btndelete"));
    }


    private void loadAllOrders() {
        ObservableList<OrderTm> obList = FXCollections.observableArrayList();


        try {
            List<OrderModel> orderList = OrderRepo.getAll();
            for (  OrderModel orderModel: orderList) {
                if(orderList != null){

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
                                x = OrderRepo.deleteOrder(id);
                            } catch (SQLException e1) {
                                throw new RuntimeException(e1);
                            }
                            if(x){
                                new Alert(Alert.AlertType.CONFIRMATION,"Order deleted").show();
                                Clear();
                            }
                            Clear();
                            loadAllOrders();
                        }
                    });

                    JFXButton btnedit = new JFXButton(" ",imageView2);
                    btnedit.setCursor(Cursor.HAND);
                    btnedit.setOnAction((e) -> {
                        String id = txtid.getText();
                        OrderModel order;
                        try {
                            order = OrderRepo.searchById(id);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        Date placedDate = order.getPlacedDate();
                        String cId = order.getCId();
                        double paymentAmount = order.getPaymentAmount();
                        String status = txtstatus.getValue();


                        OrderModel orderModel1 = new OrderModel(id, placedDate,paymentAmount,status, cId);

                        try {
                            boolean isUpdated = OrderRepo.updateOrder(orderModel1);
                            if(isUpdated) {
                                new Alert(Alert.AlertType.CONFIRMATION, "order updated!").show();
                                Clear();
                            }
                        } catch (SQLException e1) {
                            new Alert(Alert.AlertType.ERROR, e1.getMessage()).show();
                        }
                        Clear();
                        loadAllOrders();
                    });



                    OrderTm tm = new OrderTm(
                            orderModel.getOId(),
                            orderModel.getPlacedDate(),
                            orderModel.getPaymentAmount(),
                            orderModel.getStatus(),
                            orderModel.getCId(),
                            btnedit,
                            btndelete
                    );

                    obList.add(tm);
                }}

            tblorder.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    void Clear() {
        txtid.clear();
        txtstatus.setValue(null);

    }

    @FXML
    void tblOnMouseClick(MouseEvent event) {
        if (!tblorder.getSelectionModel().isEmpty()) {
            OrderTm order = tblorder.getSelectionModel().getSelectedItem();
            OrderModel order1;
            try {
                order1 = OrderRepo.searchById(order.getOId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            txtid.setText(order1.getOId());
            txtstatus.setValue(order1.getStatus());
        }
    }

    @FXML
    void tblOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void tblOnMouseExited(MouseEvent event) {

    }

    @FXML
    void txtsearchidOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtsidOnKeyReleased(KeyEvent event) {

    }


}
