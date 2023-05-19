package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.DataBase;
import sample.POJO.OrdersPOJO;
import sample.POJO.ProductsPOJO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Managerscreen {

    Connection conn = new DataBase().getConn();

    @FXML
    private Button addbt;

    @FXML
    private Button aftorizationbt;

    @FXML
    private TextField amoanttf;

    @FXML
    private TableColumn<OrdersPOJO, Integer> amountcl_o;

    @FXML
    private TableView<ProductsPOJO> cataloguetv;

    ObservableList<ProductsPOJO> cataloguelist = FXCollections.observableArrayList();

    @FXML
    private Button deletebt;

    @FXML
    private TableColumn<ProductsPOJO, String> namecl_c;

    @FXML
    private TableColumn<OrdersPOJO, String> namecl_o;

    @FXML
    private ComboBox<String> numbercb;

    ObservableList<String> numberlist = FXCollections.observableArrayList();

    @FXML
    private TableColumn<ProductsPOJO, Integer> numbercl_c;

    @FXML
    private TableColumn<OrdersPOJO, Integer> numbercl_o;

    @FXML
    private TableView<OrdersPOJO> ordertv;

    ObservableList<OrdersPOJO> orderlist = FXCollections.observableArrayList();

    @FXML
    private TableColumn<ProductsPOJO, Integer> pricecl_c;

    @FXML
    private TableColumn<OrdersPOJO, Integer> pricecl_o;

    @FXML
    private Button savebt;

    @FXML
    private ComboBox<String> typecb;

    @FXML
    private ComboBox<String> delivercb;

    @FXML
    private TextField addresstf;

    @FXML
    private TextField phonetf;

    @FXML
    private TextField fiotf;

    ObservableList<String> typelist = FXCollections.observableArrayList();

    ObservableList<String> deliverlist = FXCollections.observableArrayList();

    public Managerscreen() throws SQLException, ClassNotFoundException {
    }

    public void delete() throws SQLException {
        if (ordertv.getSelectionModel().getSelectedItem() != null) {
            orderlist.remove(ordertv.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    public void initialize() throws SQLException {
        savebt.setOnAction(e -> {
            try {
                PreparedStatement preparedStatement = conn.prepareStatement("insert into Orders (Data_order, Buyer_ID, Deliveryman_ID, Delivered) values (GETDATE(),0,0,0) ");
                preparedStatement.executeUpdate();
                int id = 0;
                preparedStatement = conn.prepareStatement("select * from Orders");
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) id = resultSet.getInt("ID");
                for (OrdersPOJO order : orderlist) {
                    preparedStatement = conn.prepareStatement("insert into Order_Part values(" + id + ", " + order.getId() + ", " + order.getAmount() + ", " + order.getPrice() + ")");
                    preparedStatement.executeUpdate();
                }
                preparedStatement = conn.prepareStatement("insert into Deliveryman_screen (Number, Order_number, Buer, Telephone, Address, Manager_ID, Deliveryman_ID) values (?,?,?,?,?,1,?)");
                preparedStatement.setInt(1, 1);
                preparedStatement.setInt(2, id);
                preparedStatement.setString(3, fiotf.getText().trim());
                preparedStatement.setString(4, phonetf.getText().trim());
                preparedStatement.setString(5, addresstf.getText().trim());
                PreparedStatement preparedStatement2 = conn.prepareStatement("SELECT * FROM Deliveryman WHERE Name=?");
                preparedStatement2.setString(1, delivercb.getValue().trim());
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                resultSet2.next();
                preparedStatement.setInt(6, resultSet2.getInt("ID"));
                preparedStatement.executeUpdate();
                orderlist.clear();
                cataloguelist.clear();
                fiotf.clear();
                phonetf.clear();
                addresstf.clear();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        deletebt.setOnAction(e -> {
            try {
                delete();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        namecl_c.setCellValueFactory(new PropertyValueFactory<>("name"));
        pricecl_c.setCellValueFactory(new PropertyValueFactory<>("price"));
        numbercl_c.setCellValueFactory(new PropertyValueFactory<>("id"));
        namecl_o.setCellValueFactory(new PropertyValueFactory<>("name"));
        pricecl_o.setCellValueFactory(new PropertyValueFactory<>("price"));
        numbercl_o.setCellValueFactory(new PropertyValueFactory<>("id"));
        amountcl_o.setCellValueFactory(new PropertyValueFactory<>("amount"));
        aftorizationbt.setOnAction(e -> {
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
            aftorizationbt.getScene().getWindow().hide();
        });
        PreparedStatement preparedStatement = conn.prepareStatement("select * from Products");

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            if (!typelist.contains(resultSet.getString("Category").trim()))
                typelist.add(resultSet.getString("Category").trim());
        }
        typecb.setItems(typelist);

        preparedStatement = conn.prepareStatement("select * from Deliveryman WHERE Manager_ID = ?");
        preparedStatement.setInt(1, Controller.userid);

        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            if (!deliverlist.contains(resultSet.getString("Name").trim()))
                deliverlist.add(resultSet.getString("Name").trim());
        }
        delivercb.setItems(deliverlist);

        typecb.setOnAction(e -> {
            try {
                updatecataloge(typecb.getValue());
                numberlist.clear();
                for (ProductsPOJO product : cataloguelist) {
                    numberlist.add(String.valueOf(product.getId()));
                }
                numbercb.setItems(numberlist);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        addbt.setOnAction(e -> {
            int id = Integer.parseInt(numbercb.getValue());
            ProductsPOJO tempproduct = null;
            for (ProductsPOJO product : cataloguelist) {
                if (product.getId() == id) {
                    tempproduct = product;
                    break;
                }
            }
            orderlist.add(new OrdersPOJO(id, tempproduct.getName(), tempproduct.getPrice() * Integer.parseInt(amoanttf.getText().trim()), Integer.parseInt(amoanttf.getText().trim())));
            ordertv.setItems(orderlist);
        });
    }


    public void updatecataloge(String category) throws SQLException {

        cataloguelist.clear();
        PreparedStatement preparedStatement = conn.prepareStatement("select * from Products where Category = '" + category + "'");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            cataloguelist.add(new ProductsPOJO(resultSet.getInt("ID"), resultSet.getString("Name"), resultSet.getInt("Price"), resultSet.getString("Category")));
        }
        cataloguetv.setItems(cataloguelist);
    }


    public void orderbt(ActionEvent event) {
        Stage primaryStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("fxml/Addmanager.fxml"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        primaryStage.setTitle("Мебельная компания МИФ");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        aftorizationbt.getScene().getWindow().hide();
    }
}