package lk.ijse.Naturab.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import lk.ijse.Naturab.Bo.BoFactory;
import lk.ijse.Naturab.Bo.custom.DashboardBo;
import lk.ijse.Naturab.Db.DbConnection;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.chart.XYChart;
import javafx.scene.chart.BarChart;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;


public class DashboardFormController implements Initializable {

    @FXML
    private BorderPane paneview;

    @FXML
    private PieChart pieChart;

    @FXML
    private AnchorPane root;

    @FXML
    private Label active;

    @FXML
    private Label broken;

    @FXML
    private Label complete;

    @FXML
    private Label notcomplete;
    @FXML
    private BorderPane paneview1;

    @FXML
    private BarChart<String, Number> payment;
    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @FXML
    private JFXButton btnMaterialRepo;

    @FXML
    private JFXButton btnProductionRepo;

    @FXML
    private JFXButton btnemplyeerepo;

    DashboardBo dashboardBo = (DashboardBo) BoFactory.getBoFactory().getBO(BoFactory.BOTypes.DASHBOARD);
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            getCompleteOrderCount();
            getNotCompleteOrderCount();
            getActiveMachineCount();
            getBrokenMachineCount();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            LoadData2();
            LoadData();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
         /*  XYChart.Series set1 = new XYChart.Series<String,Number>();



        set1.getData().add(new XYChart.Data(oid.get(0),payment1.get(0)));
        set1.getData().add(new XYChart.Data(oid.get(1),payment1.get(1)));
        set1.getData().add(new XYChart.Data(oid.get(2),payment1.get(2)));
        set1.getData().add(new XYChart.Data(oid.get(3),payment1.get(3)));*/

    @FXML
    void btnMaterialRepoOnAction(ActionEvent event) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/Report/MaterialReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);


        Map<String, Object> data = null;
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);

    }

    @FXML
    void btnProductionRepoOnAction(ActionEvent event) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/Report/ProductionReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);


        Map<String,Object> data = null;

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);

    }

    @FXML
    void btnemplyeerepoOnAction(ActionEvent event) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/Report/EmployeeReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);


        Map<String,Object> data = null;

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);

    }

    private void LoadData2() throws SQLException, ClassNotFoundException {
        if (payment == null) {
            System.err.println("Payment chart is not initialized.");
            return;
        }
        //List<Double> payment1 = dashboardBo.getOrderPayment();


       // List<String> oid = dashboardBo.getOId();

        XYChart.Series<String, Number> series = new XYChart.Series<>();

            //series.getData().add(new XYChart.Data<>(oid.get(0), payment1.get(0)));
        series.getData().add(new XYChart.Data<>("O001", 20000));
            //series.getData().add(new XYChart.Data<>(oid.get(1), payment1.get(1)));
        series.getData().add(new XYChart.Data<>("O003", 3000));
           // series.getData().add(new XYChart.Data<>(oid.get(2), payment1.get(2)));
        series.getData().add(new XYChart.Data<>("O004", 60000));
           // series.getData().add(new XYChart.Data<>(oid.get(3), payment1.get(3)));
        series.getData().add(new XYChart.Data<>("O002", 36000));

        payment.getData().add(series);
        for (XYChart.Data<String, Number> data : series.getData()) {
            data.getNode().setStyle("-fx-bar-fill: #4E3283;");
        }

    }

    private void LoadData() throws SQLException {
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
        //List<String> name = OrderDetailRepo.get1productname();
      //  List<Integer> count = OrderDetailRepo.get1productcount();
        list.add(new PieChart.Data("P001",120));
        list.add(new PieChart.Data("P003", 75));
        list.add(new PieChart.Data("P002", 50));


        pieChart.setData(list);
        list.get(0).getNode().setStyle("-fx-pie-color: #ba9ad0;");

        list.get(1).getNode().setStyle("-fx-pie-color: #4E3283;");

        list.get(2).getNode().setStyle("-fx-pie-color: #aeabab;");


        pieChart.setTitle("BEST SELL PRODUCTS");

    }

    public void getCompleteOrderCount() throws SQLException, ClassNotFoundException {
      String completed  = String.valueOf(dashboardBo.getOrdercount());
        complete.setText(completed);
    }
    public void getNotCompleteOrderCount() throws SQLException, ClassNotFoundException {
        String completed  = String.valueOf(dashboardBo.getOrdercount1());
        notcomplete.setText(completed);
    }
    public void getActiveMachineCount() throws SQLException, ClassNotFoundException {
        String completed  = String.valueOf(dashboardBo.getMachinecount());
        active.setText(completed);
    }

    public void getBrokenMachineCount() throws SQLException, ClassNotFoundException {
        String completed  = String.valueOf(dashboardBo.getMachinecount1());
        broken.setText(completed);
    }

}
