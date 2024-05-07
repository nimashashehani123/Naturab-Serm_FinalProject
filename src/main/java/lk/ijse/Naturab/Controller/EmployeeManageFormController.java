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
import lk.ijse.Naturab.Model.ClientModel;
import lk.ijse.Naturab.Model.EmployeeModel;
import lk.ijse.Naturab.Model.Tm.ClientTm;
import lk.ijse.Naturab.Model.Tm.EmployeeTm;
import lk.ijse.Naturab.Repositry.ClientRepo;
import lk.ijse.Naturab.Repositry.EmployeeRepo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lk.ijse.Naturab.Util.Regex;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class EmployeeManageFormController {

    @FXML
    private JFXButton btnback;

    @FXML
    private JFXButton btnsave;

    @FXML
    private JFXButton btnsearch;

    @FXML
    private JFXButton btnclose;

    @FXML
    private TableColumn<?, ?> coladdress;

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
    private TableView<EmployeeTm> tblemployee;

    @FXML
    private TextField txtaddress;

    @FXML
    private TextField txtexperience;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtname;

    @FXML
    private TextField txtsalary;

    @FXML
    private TextField txttel;

    @FXML
    private TextField txtsearchid;


    @FXML
    void btnsaveOnAction(ActionEvent event) {
        if (!isValied()){
            new Alert(Alert.AlertType.ERROR,"Pleace Check TextFilds !").show();
            return;
        }
        String EmId = txtid.getText();
        String Name = txtname.getText();
        String Address = txtaddress.getText();
        String Tel = txttel.getText();
        double salary = Double.parseDouble(txtsalary.getText());
        int yrex = Integer.parseInt(txtexperience.getText());

        EmployeeModel employeeModel = new EmployeeModel(EmId,Name,Address,Tel,salary,yrex,"");

        boolean x = false;
        try {
            x = EmployeeRepo.saveEmp(employeeModel);
            if(x){
                new Alert(Alert.AlertType.CONFIRMATION,"Employee saved").show();
                Clear();}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Clear();
        loadAllEmp();

    }

    @FXML
    void btnsearchOnAction(ActionEvent event) {
        if (!isValied1()){
            new Alert(Alert.AlertType.ERROR,"Pleace Check TextFilds !").show();
            return;
        }
        String id = txtsearchid.getText();
        ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();
        EmployeeModel employeeModel;

        try {
            employeeModel  = EmployeeRepo.searchById(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (employeeModel != null) {
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
                        x = EmployeeRepo.deleteEmp(id1);
                    } catch (SQLException e1) {
                        throw new RuntimeException(e1);
                    }
                    if(x){
                        new Alert(Alert.AlertType.CONFIRMATION,"Employee deleted").show();
                        Clear();
                    }
                    Clear();
                    loadAllEmp();
                }
            });

            JFXButton btnedit = new JFXButton(" ",imageView2);
            btnedit.setCursor(Cursor.HAND);
            btnedit.setOnAction((e) -> {
                if (!isValied()){
                    new Alert(Alert.AlertType.ERROR,"Pleace Check TextFilds !").show();
                    return;
                }
                String EmId = txtid.getText();
                String Name = txtname.getText();
                String Address = txtaddress.getText();
                String Tel = txttel.getText();
                double salary = Double.parseDouble(txtsalary.getText());
                int yrex = Integer.parseInt(txtexperience.getText());

                EmployeeModel employeeModel1 = new EmployeeModel(EmId,Name,Address,Tel,salary,yrex,"");

                try {
                    boolean isUpdated = EmployeeRepo.updateEmp(employeeModel1);
                    if(isUpdated) {
                        new Alert(Alert.AlertType.CONFIRMATION, "employee updated!").show();
                        Clear();
                    }
                } catch (SQLException e1) {
                    new Alert(Alert.AlertType.ERROR, e1.getMessage()).show();
                }
                Clear();
                loadAllEmp();
            });
            EmployeeTm tm = new EmployeeTm(
                    employeeModel.getEId(),
                    employeeModel.getName(),
                    employeeModel.getAddress(),
                    employeeModel.getTel(),
                    btndelete,
                    btnedit
            );

            obList.add(tm);


            tblemployee.setItems(obList);

        } else {
            new Alert(Alert.AlertType.INFORMATION, "employee not found!").show();
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

    }
    public void initialize() {
        setCellValueFactory();
        loadAllEmp();
    }

    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("EId"));
        colname.setCellValueFactory(new PropertyValueFactory<>("Name"));
        coltel.setCellValueFactory(new PropertyValueFactory<>("Tel"));
        coladdress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        coledit.setCellValueFactory(new PropertyValueFactory<>("btnedit"));
        coldelete.setCellValueFactory(new PropertyValueFactory<>("btndelete"));
    }

    private void loadAllEmp() {
        ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();


        try {
            List<EmployeeModel> empList = EmployeeRepo.getAll();
            for (EmployeeModel employeeModel : empList) {
                if(empList != null){

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
                                x = EmployeeRepo.deleteEmp(id);
                            } catch (SQLException e1) {
                                throw new RuntimeException(e1);
                            }
                            if(x){
                                new Alert(Alert.AlertType.CONFIRMATION,"Employee deleted").show();
                                Clear();
                            }
                            Clear();
                            loadAllEmp();
                        }
                    });

                    JFXButton btnedit = new JFXButton(" ",imageView2);
                    btnedit.setCursor(Cursor.HAND);

                    btnedit.setOnAction((e) -> {
                        if (!isValied()){
                            new Alert(Alert.AlertType.ERROR,"Pleace Check TextFilds !").show();
                            return;
                        }
                        String EmId = txtid.getText();
                        String Name = txtname.getText();
                        String Address = txtaddress.getText();
                        String Tel = txttel.getText();
                        double salary = Double.parseDouble(txtsalary.getText());
                        int yrex = Integer.parseInt(txtexperience.getText());

                        EmployeeModel employeeModel1 = new EmployeeModel(EmId,Name,Address,Tel,salary,yrex,"");

                        try {
                            boolean isUpdated = EmployeeRepo.updateEmp(employeeModel1);
                            if(isUpdated) {
                                new Alert(Alert.AlertType.CONFIRMATION, "employee updated!").show();
                                Clear();
                            }
                        } catch (SQLException e1) {
                            new Alert(Alert.AlertType.ERROR, e1.getMessage()).show();
                        }
                        Clear();
                        loadAllEmp();
                    });

                    EmployeeTm tm = new EmployeeTm(
                            employeeModel.getEId(),
                            employeeModel.getName(),
                            employeeModel.getAddress(),
                            employeeModel.getTel(),
                            btndelete,
                            btnedit
                    );

                    obList.add(tm);
                }}

            tblemployee.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnbackOnAction(ActionEvent event) {

    }

    @FXML
    void btncloseOnAction(ActionEvent event) {

    }
    @FXML
    void tblOnMouseClick(MouseEvent event) {
        if (!tblemployee.getSelectionModel().isEmpty()) {
            EmployeeTm employee = tblemployee.getSelectionModel().getSelectedItem();
            EmployeeModel employee1;
            try {
                employee1 = EmployeeRepo.searchById(employee.getEId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            txtid.setText(employee1.getEId());
            txtname.setText(employee1.getName());
            txtaddress.setText(employee1.getAddress());
            txttel.setText(employee1.getTel());
            txtsalary.setText(String.valueOf(employee1.getSalary()));
            txtexperience.setText(String.valueOf(employee1.getYrOfExperience()));
        }
    }
    @FXML
    void txtaddressOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.Naturab.Util.TextField.ADDRESS,txtaddress);

    }

    @FXML
    void txtexperienceOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.Naturab.Util.TextField.ADDRESS,txtaddress);

    }

    @FXML
    void txtidOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.Naturab.Util.TextField.ADDRESS,txtaddress);

    }

    @FXML
    void txtnameOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.Naturab.Util.TextField.ADDRESS,txtaddress);

    }

    @FXML
    void txtsalaryOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.Naturab.Util.TextField.ADDRESS,txtaddress);

    }

    @FXML
    void txtsearchidOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.Naturab.Util.TextField.ADDRESS,txtaddress);

    }

    @FXML
    void txttelOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.Naturab.Util.TextField.ADDRESS,txtaddress);

    }
    public boolean isValied(){
        if (!Regex.setTextColor(lk.ijse.Naturab.Util.TextField.ID,txtid)) return false;
        if (!Regex.setTextColor(lk.ijse.Naturab.Util.TextField.NAME,txtname)) return false;
        if (!Regex.setTextColor(lk.ijse.Naturab.Util.TextField.ADDRESS,txtaddress)) return false;
        if (!Regex.setTextColor(lk.ijse.Naturab.Util.TextField.TEL,txttel)) return false;
        if (!Regex.setTextColor(lk.ijse.Naturab.Util.TextField.SALARY,txtsalary)) return false;
        if (!Regex.setTextColor(lk.ijse.Naturab.Util.TextField.QTY,txtexperience)) return false;

        return true;
    }
    public boolean isValied1(){
        if (!Regex.setTextColor(lk.ijse.Naturab.Util.TextField.ID,txtsearchid)) return false;
        return true;
    }

}
