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
import sample.Main;
import sample.POJO.ManagerPOJO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Adddeliveryman {

    Connection conn = new DataBase().getConn();

    @FXML
    private TableView<ManagerPOJO> Adddeliverymantable;

    @FXML
    private TableColumn<ManagerPOJO, String> addproduct;

    @FXML
    private TableColumn<ManagerPOJO, Integer> addorder;

    @FXML
    private TableColumn<ManagerPOJO, Integer> number2;

    @FXML
    private TableColumn<ManagerPOJO, Integer> quantity;

    @FXML
    private TableColumn<ManagerPOJO, Integer> totalprice;

    ObservableList<ManagerPOJO> adddeliverymanlist = FXCollections.observableArrayList();

    public Adddeliveryman() throws SQLException, ClassNotFoundException {
    }

    @FXML
    void exit(ActionEvent event) {
        Stage primaryStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("fxml/Deliveryman.fxml"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        primaryStage.setTitle("Мебельная компания МИФ");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        Adddeliverymantable.getScene().getWindow().hide();
    }

    @FXML
    public void initialize() throws SQLException {
        number2.setCellValueFactory(new PropertyValueFactory<>("number2"));
        addorder.setCellValueFactory(new PropertyValueFactory<>("order_id"));
        addproduct.setCellValueFactory(new PropertyValueFactory<>("product"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("amount"));
        totalprice.setCellValueFactory(new PropertyValueFactory<>("total_price"));
        update();
    }

    public void update() throws SQLException {
        adddeliverymanlist.clear();
        PreparedStatement preparedStatement = conn.prepareStatement("select Order_part.ID, Order_part.Order_ID, Order_part.Amount, Order_part.Total_Price, Products.Name from Order_part, Products WHERE Products.ID = Order_part.Products_ID AND Order_part.Order_ID = ?");
        preparedStatement.setInt(1, Main.orderid );
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            adddeliverymanlist.add(new ManagerPOJO(resultSet.getInt("ID"), resultSet.getInt("Order_ID"), resultSet.getString("Name"), resultSet.getInt("Amount"), resultSet.getInt("Total_Price"), adddeliverymanlist.size() + 1));
        }
        Adddeliverymantable.setItems(adddeliverymanlist);
    }

}
