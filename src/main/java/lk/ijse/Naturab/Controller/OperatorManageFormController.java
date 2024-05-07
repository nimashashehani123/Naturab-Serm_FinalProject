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
import lk.ijse.Naturab.Model.ClientModel;
import lk.ijse.Naturab.Model.EmployeeModel;
import lk.ijse.Naturab.Model.OperatorModel;
import lk.ijse.Naturab.Model.Tm.ClientTm;
import lk.ijse.Naturab.Model.Tm.EmployeeTm;
import lk.ijse.Naturab.Model.Tm.OperatorTm;
import lk.ijse.Naturab.Repositry.ClientRepo;
import lk.ijse.Naturab.Repositry.EmployeeRepo;
import lk.ijse.Naturab.Repositry.MachineRepo;
import lk.ijse.Naturab.Repositry.OperatorRepo;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OperatorManageFormController {

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
    private TableColumn<?, ?> colmaid;

    @FXML
    private TableColumn<?, ?> colname;

    @FXML
    private TableColumn<?, ?> coltel;

    @FXML
    private TableView<OperatorTm> tbloperator;

    @FXML
    private TextField txtaddress;

    @FXML
    private TextField txtexperience;

    @FXML
    private TextField txtid;

    @FXML
    private JFXComboBox<String> txtmachineid;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtsalary;

    @FXML
    private TextField txtsearchid;

    @FXML
    private TextField txttel;

    @FXML
    void btnbackOnAction(ActionEvent event) {

    }

    @FXML
    void btncloseOnAction(ActionEvent event) {
        txtsearchid.clear();
        Clear();
        loadAllOperator();

    }

    @FXML
    void btnsaveOnAction(ActionEvent event) {
        String opId = txtid.getText();
        String Name = txtname.getText();
        String Address = txtaddress.getText();
        String Tel = txttel.getText();
        double salary = Double.parseDouble(txtsalary.getText());
        int yrex = Integer.parseInt(txtexperience.getText());
        String maid = txtmachineid.getValue();

        OperatorModel operatorModel = new OperatorModel(opId,Name,Address,Tel,salary,yrex,"",maid);

        boolean x = false;
        try {
            x = OperatorRepo.saveOperator(operatorModel);
            if(x){
                new Alert(Alert.AlertType.CONFIRMATION,"Operator saved").show();
                Clear();}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Clear();
        loadAllOperator();


    }

    @FXML
    void btnsearchOnAction(ActionEvent event) {
        String id = txtsearchid.getText();
        ObservableList<OperatorTm> obList = FXCollections.observableArrayList();
        OperatorModel operatorModel;

        try {
            operatorModel  = OperatorRepo.searchById(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (operatorModel != null) {
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
                        x = OperatorRepo.deleteOperator(id1);
                    } catch (SQLException e1) {
                        throw new RuntimeException(e1);
                    }
                    if(x){
                        new Alert(Alert.AlertType.CONFIRMATION,"Operator deleted").show();
                        Clear();
                    }
                    Clear();
                    loadAllOperator();
                }
            });

            JFXButton btnedit = new JFXButton(" ",imageView2);
            btnedit.setCursor(Cursor.HAND);

            btnedit.setOnAction((e) -> {
                String OpId = txtid.getText();
                String Name = txtname.getText();
                String Address = txtaddress.getText();
                String Tel = txttel.getText();
                double salary = Double.parseDouble(txtsalary.getText());
                int yrex = Integer.parseInt(txtexperience.getText());
                String maid = txtmachineid.getValue();

                OperatorModel operatorModel1 = new OperatorModel(OpId,Name,Address,Tel,salary,yrex,"",maid);

                try {
                    boolean isUpdated = OperatorRepo.updateOperator(operatorModel1);
                    if(isUpdated) {
                        new Alert(Alert.AlertType.CONFIRMATION, "operator updated!").show();
                        Clear();
                    }
                } catch (SQLException e1) {
                    new Alert(Alert.AlertType.ERROR, e1.getMessage()).show();
                }
                Clear();
                loadAllOperator();
            });


            OperatorTm tm = new OperatorTm(
                    operatorModel.getOpId(),
                    operatorModel.getName(),
                    operatorModel.getAddress(),
                    operatorModel.getTel(),
                    operatorModel.getMaId(),
                    btndelete,
                    btnedit
            );

            obList.add(tm);


            tbloperator.setItems(obList);

        } else {
            new Alert(Alert.AlertType.INFORMATION, "operator not found!").show();
        }
        Clear();

    }
    void Clear() {
        txtid.clear();
        txtname.clear();
        txtaddress.clear();
        txttel.clear();
        txtsalary.clear();
        txtexperience.clear();
        txtmachineid.setValue(null);

    }
    public void initialize() {
        getMachineIds();
        setCellValueFactory();
        loadAllOperator();
    }

    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("OpId"));
        colname.setCellValueFactory(new PropertyValueFactory<>("Name"));
        coltel.setCellValueFactory(new PropertyValueFactory<>("Tel"));
        coladdress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colmaid.setCellValueFactory(new PropertyValueFactory<>("MaId"));
        coledit.setCellValueFactory(new PropertyValueFactory<>("btnedit"));
        coldelete.setCellValueFactory(new PropertyValueFactory<>("btndelete"));
    }

    private void loadAllOperator() {
        ObservableList<OperatorTm> obList = FXCollections.observableArrayList();


        try {
            List<OperatorModel> opList = OperatorRepo.getAll();
            for (OperatorModel operatorModel : opList) {
                if(opList != null){

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
                                x = OperatorRepo.deleteOperator(id);
                            } catch (SQLException e1) {
                                throw new RuntimeException(e1);
                            }
                            if(x){
                                new Alert(Alert.AlertType.CONFIRMATION,"Operator deleted").show();
                                Clear();
                            }
                            Clear();
                            loadAllOperator();
                        }
                    });

                    JFXButton btnedit = new JFXButton(" ",imageView2);
                    btnedit.setCursor(Cursor.HAND);

                    btnedit.setOnAction((e) -> {
                        String OpId = txtid.getText();
                        String Name = txtname.getText();
                        String Address = txtaddress.getText();
                        String Tel = txttel.getText();
                        double salary = Double.parseDouble(txtsalary.getText());
                        int yrex = Integer.parseInt(txtexperience.getText());
                        String maid = txtmachineid.getValue();

                        OperatorModel operatorModel1 = new OperatorModel(OpId,Name,Address,Tel,salary,yrex,"",maid);

                        try {
                            boolean isUpdated = OperatorRepo.updateOperator(operatorModel1);
                            if(isUpdated) {
                                new Alert(Alert.AlertType.CONFIRMATION, "operator updated!").show();
                                Clear();
                            }
                        } catch (SQLException e1) {
                            new Alert(Alert.AlertType.ERROR, e1.getMessage()).show();
                        }
                        Clear();
                        loadAllOperator();
                    });

                    OperatorTm tm = new OperatorTm(
                            operatorModel.getOpId(),
                            operatorModel.getName(),
                            operatorModel.getAddress(),
                            operatorModel.getTel(),
                            operatorModel.getMaId(),
                            btndelete,
                            btnedit
                    );

                    obList.add(tm);
                }}

            tbloperator.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void tblOnMouseClick(MouseEvent event) {
        if (!tbloperator.getSelectionModel().isEmpty()) {
            OperatorTm operator = tbloperator.getSelectionModel().getSelectedItem();
            OperatorModel operator1;
            try {
                operator1 = OperatorRepo.searchById(operator.getOpId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            txtid.setText(operator1.getOpId());
            txtname.setText(operator1.getName());
            txtaddress.setText(operator1.getAddress());
            txttel.setText(operator1.getTel());
            txtsalary.setText(String.valueOf(operator1.getSalary()));
            txtexperience.setText(String.valueOf(operator1.getYrOfExperience()));
            txtmachineid.setValue(operator1.getMaId());
        }

    }

    @FXML
    void tblOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void tblOnMouseExited(MouseEvent event) {

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

}
