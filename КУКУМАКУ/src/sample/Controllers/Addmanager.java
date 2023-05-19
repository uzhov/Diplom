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
import sample.POJO.ManagerPOJO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Addmanager {

    Connection conn = new DataBase().getConn();

    @FXML
    private TableView<ManagerPOJO> Addmanagertable;

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

    ObservableList<ManagerPOJO> addmanagerlist = FXCollections.observableArrayList();

    public Addmanager() throws SQLException, ClassNotFoundException {
    }

    @FXML
    void changebt(ActionEvent event) {
        Stage primaryStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("fxml/Addmanagerchange.fxml"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        primaryStage.setTitle("Мебельная компания МИФ");
        primaryStage.setScene(new Scene(root, 649, 201));
        primaryStage.show();
        Addmanagertable.getScene().getWindow().hide();
    }

    @FXML
    public void delitbt(ActionEvent event) throws SQLException {
        if (Addmanagertable.getSelectionModel().getSelectedItem() != null){
            addmanagerlist.remove(Addmanagertable.getSelectionModel().getSelectedItem());
            PreparedStatement preparedStatement = conn.prepareStatement("Delete FROM Order_Part WHERE ID = ?");
            preparedStatement.setInt(1, Addmanagertable.getSelectionModel().getSelectedItem().getId());
            preparedStatement.executeUpdate();
        }


    }

    @FXML
    void exitbt(ActionEvent event) {
        Stage primaryStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("fxml/Manager.fxml"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        primaryStage.setTitle("Мебельная компания МИФ");
        primaryStage.setScene(new Scene(root, 633, 477));
        primaryStage.show();
        Addmanagertable.getScene().getWindow().hide();
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
        addmanagerlist.clear();
        PreparedStatement preparedStatement = conn.prepareStatement("select Order_part.ID, Order_part.Order_ID, Order_part.Amount, Order_part.Total_Price, Products.Name from Order_part, Products WHERE Products.ID = Order_part.Products_ID");

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            addmanagerlist.add(new ManagerPOJO(resultSet.getInt("ID"), resultSet.getInt("Order_ID"), resultSet.getString("Name"), resultSet.getInt("Amount"), resultSet.getInt("Total_Price"), addmanagerlist.size() + 1));
        }
        Addmanagertable.setItems(addmanagerlist);
    }

}
