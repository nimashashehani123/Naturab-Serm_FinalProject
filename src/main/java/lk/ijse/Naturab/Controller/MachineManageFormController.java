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
import lk.ijse.Naturab.Model.MachineModel;
import lk.ijse.Naturab.Model.Tm.MachineTm;
import lk.ijse.Naturab.Repositry.MachineRepo;
import lk.ijse.Naturab.Repositry.OperatorRepo;
import lk.ijse.Naturab.Util.Regex;

import javax.mail.MessagingException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MachineManageFormController {

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
    private TableColumn<?, ?> colstatus;

    @FXML
    private TableColumn<?, ?> coltype;

    @FXML
    private TableView<MachineTm> tblmachine;

    @FXML
    private TextField txtcapacity;

    @FXML
    private TextField txtdescription;

    @FXML
    private TextField txtid;

    @FXML
    private TextField txtsearchid;

    @FXML
    private ChoiceBox<String> txtstatus;

    @FXML
    private TextField txttype;


    @FXML
    void btnbackOnAction(ActionEvent event) {

    }

    @FXML
    void btncloseOnAction(ActionEvent event) {
        txtsearchid.clear();
        Clear();
        loadAllMachine();

    }

    @FXML
    void btnsaveOnAction(ActionEvent event) {
        if (!isValied()){
            new Alert(Alert.AlertType.ERROR,"Pleace Check TextFilds !").show();
            return;
        }
        String MaId = txtid.getText();
        String Description = txtdescription.getText();
        String Capacity = txtcapacity.getText();
        String Type = txttype.getText();
        String Status = txtstatus.getValue();

        MachineModel machineModel = new MachineModel(MaId,Description,Capacity,Type,Status);

        boolean x = false;
        try {
            x = MachineRepo.saveMachine(machineModel);
            if(x){
                new Alert(Alert.AlertType.CONFIRMATION,"Machine saved").show();
                Clear();}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Clear();
        loadAllMachine();
    }

    @FXML
    void btnsearchOnAction(ActionEvent event) {
        if (!isValied1()){
            new Alert(Alert.AlertType.ERROR,"Pleace Check TextFilds !").show();
            return;
        }
        String id = txtsearchid.getText();
        ObservableList<MachineTm> obList = FXCollections.observableArrayList();

        MachineModel machineModel ;

        try {
            machineModel  = MachineRepo.searchById(id);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (machineModel != null) {
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
                        x = MachineRepo.deleteMachine(id1);
                    } catch (SQLException e1) {
                        throw new RuntimeException(e1);
                    }
                    if(x){
                        new Alert(Alert.AlertType.CONFIRMATION,"Machine deleted").show();
                        Clear();
                    }
                    Clear();
                    loadAllMachine();
                }
            });


            JFXButton btnedit = new JFXButton(" ",imageView2);
            btnedit.setCursor(Cursor.HAND);
            btnedit.setOnAction((e) -> {
                if (!isValied()){
                    new Alert(Alert.AlertType.ERROR,"Pleace Check TextFilds !").show();
                    return;
                }
                String MaId = txtid.getText();
                String Description = txtdescription.getText();
                String Capacity = txtcapacity.getText();
                String Type = txttype.getText();
                String Status = String.valueOf(txtstatus.getValue());

                MachineModel machineModel1 = new MachineModel(MaId,Description,Capacity,Type,Status);


                try {
                    boolean isUpdated = MachineRepo.updateMachine(machineModel1);
                    if(isUpdated) {
                        new Alert(Alert.AlertType.CONFIRMATION, "machine updated!").show();
                        Clear();
                    }
                } catch (SQLException e1) {
                    new Alert(Alert.AlertType.ERROR, e1.getMessage()).show();
                }
                Clear();
                loadAllMachine();
            });
            MachineTm tm = new MachineTm(
                   machineModel.getMaId(),
                    machineModel.getDescription(),
                    machineModel.getType(),
                    machineModel.getStatus(),
                    btnedit,
                    btndelete
            );

            obList.add(tm);


            tblmachine.setItems(obList);

        } else {
            new Alert(Alert.AlertType.INFORMATION, "machine not found!").show();
        }
        Clear();
    }
    void Clear() {
        txtid.clear();
        txtdescription.clear();
        txttype.clear();
        txtcapacity.clear();
        txtstatus.setValue(null);

    }
    public void initialize() throws MessagingException {
        txtstatus.getItems().addAll("Active","Broken", "On Repair");
        //Mail.sendMail("chithrawanshaliyanage@gmail.com");
        setCellValueFactory();
        loadAllMachine();
    }

    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("MaId"));
        coldescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("Type"));
        colstatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        coledit.setCellValueFactory(new PropertyValueFactory<>("btnedit"));
        coldelete.setCellValueFactory(new PropertyValueFactory<>("btndelete"));
    }

    private void loadAllMachine() {
        ObservableList<MachineTm> obList = FXCollections.observableArrayList();


        try {
            List<MachineModel> machineList = MachineRepo.getAll();
            for (MachineModel machineModel : machineList) {
                if(machineList != null){

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
                                x = MachineRepo.deleteMachine(id);
                            } catch (SQLException e1) {
                                throw new RuntimeException(e1);
                            }
                            if(x){
                                new Alert(Alert.AlertType.CONFIRMATION,"Machine deleted").show();
                                Clear();
                            }
                            Clear();
                            loadAllMachine();
                        }
                    });

                    JFXButton btnedit = new JFXButton(" ",imageView2);
                    btnedit.setCursor(Cursor.HAND);

                    btnedit.setOnAction((e) -> {
                        if (!isValied()){
                            new Alert(Alert.AlertType.ERROR,"Pleace Check TextFilds !").show();
                            return;
                        }
                        String MaId = txtid.getText();
                        String Description = txtdescription.getText();
                        String Capacity = txtcapacity.getText();
                        String Type = txttype.getText();
                        String Status = String.valueOf(txtstatus.getValue());

                        MachineModel machineModel1 = new MachineModel(MaId,Description,Capacity,Type,Status);


                        try {
                            boolean isUpdated = MachineRepo.updateMachine(machineModel1);
                            if(isUpdated) {
                                if (Status=="Broken"){

                                    try {
                                        Mail.sendMail(OperatorRepo.getEmail(MaId),MaId);
                                    } catch (MessagingException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }
                                new Alert(Alert.AlertType.CONFIRMATION, "machine updated!").show();
                                Clear();
                            }
                        } catch (SQLException e1) {
                            new Alert(Alert.AlertType.ERROR, e1.getMessage()).show();
                        }
                        Clear();
                        loadAllMachine();
                    });

                    MachineTm tm = new MachineTm(
                            machineModel.getMaId(),
                            machineModel.getDescription(),
                            machineModel.getType(),
                            machineModel.getStatus(),
                            btnedit,
                            btndelete
                    );


                    obList.add(tm);
                }}

            tblmachine.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void tblOnMouseClick(MouseEvent event) {
        if (!tblmachine.getSelectionModel().isEmpty()) {
            MachineTm machine = tblmachine.getSelectionModel().getSelectedItem();
            MachineModel machine1;
            try {
                machine1 = MachineRepo.searchById(machine.getMaId());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            txtid.setText(machine1.getMaId());
            txtdescription.setText(machine1.getDescription());
            txttype.setText(machine1.getType());
            txtcapacity.setText(machine1.getCapacity());
            txtstatus.setValue(machine1.getStatus());
        }
    }

    @FXML
    void tblOnMouseEntered(MouseEvent event) {

    }

    @FXML
    void tblOnMouseExited(MouseEvent event) {

    }


    @FXML
    void txtcapacityOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.Naturab.Util.TextField.ADDRESS,txtcapacity);

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
    void txtsearchidOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.Naturab.Util.TextField.ID,txtsearchid);

    }

    @FXML
    void txttypeOnKeyReleased(KeyEvent event) {
        Regex.setTextColor(lk.ijse.Naturab.Util.TextField.ADDRESS,txttype);

    }
    public boolean isValied(){
        if (!Regex.setTextColor(lk.ijse.Naturab.Util.TextField.ID,txtid)) return false;
        if (!Regex.setTextColor(lk.ijse.Naturab.Util.TextField.ADDRESS,txtdescription)) return false;
        if (!Regex.setTextColor(lk.ijse.Naturab.Util.TextField.ADDRESS,txtcapacity)) return false;
        if (!Regex.setTextColor(lk.ijse.Naturab.Util.TextField.ADDRESS,txttype)) return false;

        return true;
    }
    public boolean isValied1(){
        if (!Regex.setTextColor(lk.ijse.Naturab.Util.TextField.ID,txtsearchid)) return false;
        return true;
    }
}
