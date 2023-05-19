package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.POJO.AdminclassPOJO;
import sample.DataBase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Adminscreen {

    Connection conn = new DataBase().getConn();

    @FXML
    private TableView<AdminclassPOJO> admintable;

    @FXML
    private TableColumn<AdminclassPOJO, String> category;

    @FXML
    private TableColumn<AdminclassPOJO, String> nameproducts;

    @FXML
    private TableColumn<AdminclassPOJO, Integer> price;

    ObservableList<AdminclassPOJO> productslist = FXCollections.observableArrayList();

    public Adminscreen() throws SQLException, ClassNotFoundException {
    }

    @FXML
    public void delete() throws SQLException {

        if (admintable.getSelectionModel().getSelectedItem() != null){
            PreparedStatement preparedStatement = conn.prepareStatement("Delete FROM Products WHERE Name = ?");
            preparedStatement.setString(1, admintable.getSelectionModel().getSelectedItem().getName());
            preparedStatement.executeUpdate();
        }

    }

    @FXML
    public void staff() {

        Stage primaryStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("fxml/Adminstaff.fxml"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        primaryStage.setTitle("Мебельная компания МИФ");
        primaryStage.setScene(new Scene(root, 648, 392));
        primaryStage.show();
        admintable.getScene().getWindow().hide();

    }

    @FXML
    public void mainscreen() {

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
            admintable.getScene().getWindow().hide();

    }

    @FXML
    public void zamena() {

        Stage primaryStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("fxml/Addcatalog.fxml"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        primaryStage.setTitle("Мебельная компания МИФ");
        primaryStage.setScene(new Scene(root, 854, 183));
        primaryStage.show();
        admintable.getScene().getWindow().hide();
    }
    @FXML
    public void initialize() throws SQLException {
        nameproducts.setCellValueFactory(new PropertyValueFactory<>("name"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        update();
    }
    public void update() throws SQLException {
        productslist.clear();
        PreparedStatement preparedStatement = conn.prepareStatement("select * from Products");

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            productslist.add(new AdminclassPOJO(resultSet.getString("Name"), resultSet.getInt("Price"), resultSet.getString("Category")));
        }
        admintable.setItems(productslist);
    }
}
