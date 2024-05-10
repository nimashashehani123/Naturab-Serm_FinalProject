package lk.ijse.Naturab.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import lk.ijse.Naturab.Repositry.MachineRepo;
import lk.ijse.Naturab.Repositry.OrderDetailRepo;
import lk.ijse.Naturab.Repositry.OrderRepo;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.BarChart;




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
    private BarChart<String, Double> payment;
    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            getCompleteOrderCount();
            getNotCompleteOrderCount();
            getActiveMachineCount();
            getBrokenMachineCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            LoadData2();
            LoadData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void LoadData2() throws SQLException {
     /*   XYChart.Series set1 = new XYChart.Series<String,Double>();
        List<Double> payment1 = OrderRepo.getPayment();


        List<String> oid = OrderRepo.getOId();


        set1.getData().add(new XYChart.Data(oid.get(0),payment1.get(0)));
        set1.getData().add(new XYChart.Data(oid.get(1),payment1.get(1)));
        set1.getData().add(new XYChart.Data(oid.get(2),payment1.get(2)));
        set1.getData().add(new XYChart.Data(oid.get(3),payment1.get(3)));
        payment.getData().addAll(set1);*/

    }
    private void LoadData() throws SQLException {
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
        List<String> name = OrderDetailRepo.get1productname();
        List<Integer> count = OrderDetailRepo.get1productcount();
        list.add(new PieChart.Data(name.get(0),count.get(0) ));
        list.add(new PieChart.Data(name.get(1), count.get(1)));
        list.add(new PieChart.Data(name.get(2), count.get(2)));


        pieChart.setData(list);
        list.get(0).getNode().setStyle("-fx-pie-color: #ba9ad0;");

        list.get(1).getNode().setStyle("-fx-pie-color: #4E3283;");

        list.get(2).getNode().setStyle("-fx-pie-color: #aeabab;");


        pieChart.setTitle("BEST SELL PRODUCTS");

    }

    public void getCompleteOrderCount() throws SQLException {
      String completed  = String.valueOf(OrderRepo.getOrderCount());
        complete.setText(completed);
    }
    public void getNotCompleteOrderCount() throws SQLException {
        String completed  = String.valueOf(OrderRepo.getOrderCount1());
        notcomplete.setText(completed);
    }
    public void getActiveMachineCount() throws SQLException {
        String completed  = String.valueOf(MachineRepo.getMachinecount());
        active.setText(completed);
    }

    public void getBrokenMachineCount() throws SQLException {
        String completed  = String.valueOf(MachineRepo.getMachinecount1());
        broken.setText(completed);
    }

}
