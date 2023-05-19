package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.DataBase;
import sample.POJO.DeliverymanPOJO;
import sample.Main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Deliverymanscreen {

    Connection conn = new DataBase().getConn();

    @FXML
    private TableView<DeliverymanPOJO> deliverymantableS;

    @FXML
    private TableColumn<DeliverymanPOJO, String> address;


    @FXML
    private TableColumn<DeliverymanPOJO, String> buyer;

    @FXML
    private TableColumn<DeliverymanPOJO,String> number;

    @FXML
    private TableColumn<DeliverymanPOJO, Integer> ordernumber;

    @FXML
    private TableColumn<DeliverymanPOJO, String> telephone;

    ObservableList<DeliverymanPOJO> deliverymanlist = FXCollections.observableArrayList();

    public Deliverymanscreen() throws SQLException, ClassNotFoundException {
    }

    @FXML
    void PODTVERDIT() throws SQLException {

        if (deliverymantableS.getSelectionModel().getSelectedItem() != null) {
            PreparedStatement preparedStatement = conn.prepareStatement("Delete FROM Deliveryman_screen WHERE Order_number = ?");
            preparedStatement.setInt(1, deliverymantableS.getSelectionModel().getSelectedItem().getOrder_number());
            preparedStatement.executeUpdate();
        }
        update();
    }

    @FXML
    void mainscreen(ActionEvent event) {

        Stage primaryStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("fxml/Aftorizacia.fxml"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        primaryStage.setTitle("Мебельная компания МИФ");
        primaryStage.setScene(new Scene(root, 556, 366));
        primaryStage.show();
        deliverymantableS.getScene().getWindow().hide();
    }

    @FXML
    public void open() {
        Main.orderid = deliverymantableS.getSelectionModel().getSelectedItem().Order_number;
        Stage primaryStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("fxml/Adddeliveryman.fxml"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        primaryStage.setTitle("Мебельная компания МИФ");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        deliverymantableS.getScene().getWindow().hide();
    }

    @FXML
    public void initialize() throws SQLException {
        number.setCellValueFactory(new PropertyValueFactory<>("Number"));
        ordernumber.setCellValueFactory(new PropertyValueFactory<>("Order_number"));
        buyer.setCellValueFactory(new PropertyValueFactory<>("Buer"));
        telephone.setCellValueFactory(new PropertyValueFactory<>("Telephone"));
        address.setCellValueFactory(new PropertyValueFactory<>("Address"));
        update();
    }

    public void update() throws SQLException {
        deliverymanlist.clear();
        PreparedStatement preparedStatement = conn.prepareStatement("select * from Deliveryman_screen WHERE Deliveryman_ID = ?");
        preparedStatement.setInt(1, Controller.userid);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            deliverymanlist.add(new DeliverymanPOJO(resultSet.getString("Number"), resultSet.getInt("Order_number"), resultSet.getString("Buer"), resultSet.getString("Telephone"), resultSet.getString("Address")));
        }
        deliverymantableS.setItems(deliverymanlist);
    }
}

