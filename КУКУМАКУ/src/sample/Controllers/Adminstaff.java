package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.POJO.AdminstaffPOJO;
import sample.DataBase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Adminstaff {

    Connection conn = new DataBase().getConn();

    @FXML
    private TableColumn<AdminstaffPOJO, String> Login_staff;

    @FXML
    private TableColumn<AdminstaffPOJO, String> Name_staff;

    @FXML
    private TableColumn<AdminstaffPOJO, String> Password_staff;

    @FXML
    private TextField fiotf;

    @FXML
    private TextField logintf;

    @FXML
    private TextField passwordtf;

    @FXML
    private ComboBox<String> staffcb;

    @FXML
    private TableView<AdminstaffPOJO> stafftv;

    public Adminstaff() throws SQLException, ClassNotFoundException {
    }

    ObservableList<AdminstaffPOJO> stafflist = FXCollections.observableArrayList();

    ObservableList<String> stafflistcb = FXCollections.observableArrayList();


    @FXML
    void addstaff() throws SQLException {

        if (staffcb.getValue().equals("Менеджер")) {
                PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Manager VALUES (?,?,?,?)");
                preparedStatement.setString(1,fiotf.getText().trim());
                preparedStatement.setString(2,logintf.getText().trim());
                preparedStatement.setString(3,passwordtf.getText().trim());
                preparedStatement.setInt(4,Controller.userid);
                preparedStatement.executeUpdate();

        }

        if (staffcb.getValue().equals("Бухгалтер")) {
                PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Accountant VALUES (?,?,?,?)");
                preparedStatement.setString(1,fiotf.getText().trim());
                preparedStatement.setString(2,logintf.getText().trim());
                preparedStatement.setString(3,passwordtf.getText().trim());
                preparedStatement.setInt(4,Controller.userid);
                preparedStatement.executeUpdate();

        }

        if (staffcb.getValue().equals("Доставщик")) {
                PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO Deliveryman VALUES (?,?,?,?,?)");
                preparedStatement.setString(1,fiotf.getText().trim());
                preparedStatement.setString(2,logintf.getText().trim());
                preparedStatement.setString(3,passwordtf.getText().trim());
                preparedStatement.setInt(4,Controller.userid);
                preparedStatement.setInt(5,1);
                preparedStatement.executeUpdate();

        }
        update();

    }


    @FXML
    void delete() throws SQLException {
        if (staffcb.getValue().equals("Менеджер")) {
            if (stafftv.getSelectionModel().getSelectedItem() != null) {
                PreparedStatement preparedStatement = conn.prepareStatement("Delete FROM Manager WHERE ID = ?");
                preparedStatement.setInt(1, stafftv.getSelectionModel().getSelectedItem().getId());
                preparedStatement.executeUpdate();
            }
        }

        if (staffcb.getValue().equals("Бухгалтер")) {
            if (stafftv.getSelectionModel().getSelectedItem() != null) {
                PreparedStatement preparedStatement = conn.prepareStatement("Delete FROM Accountant WHERE ID = ?");
                preparedStatement.setInt(1, stafftv.getSelectionModel().getSelectedItem().getId());
                preparedStatement.executeUpdate();
            }
        }

        if (staffcb.getValue().equals("Доставщик")) {
            if (stafftv.getSelectionModel().getSelectedItem() != null) {
                PreparedStatement preparedStatement = conn.prepareStatement("Delete FROM Deliveryman WHERE ID = ?");
                preparedStatement.setInt(1, stafftv.getSelectionModel().getSelectedItem().getId());
                preparedStatement.executeUpdate();
            }
        }
        update();
    }

    @FXML
    void exit() {

        Stage primaryStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("fxml/Administrator.fxml"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        primaryStage.setTitle("Мебельная компания МИФ");
        primaryStage.setScene(new Scene(root, 568, 368));
        primaryStage.show();
        stafftv.getScene().getWindow().hide();

    }

    @FXML
    void zamena() throws SQLException {

        if (staffcb.getValue().equals("Менеджер")) {
            if (stafftv.getSelectionModel().getSelectedItem() != null) {
                PreparedStatement preparedStatement = conn.prepareStatement("UPDATE Manager SET Name = ?, Login = ?, Password = ? WHERE ID = ?");
                preparedStatement.setString(1,fiotf.getText().trim());
                preparedStatement.setString(2,logintf.getText().trim());
                preparedStatement.setString(3,passwordtf.getText().trim());
                preparedStatement.setInt(4, stafftv.getSelectionModel().getSelectedItem().getId());
                preparedStatement.executeUpdate();
            }
        }

        if (staffcb.getValue().equals("Бухгалтер")) {
            if (stafftv.getSelectionModel().getSelectedItem() != null) {
                PreparedStatement preparedStatement = conn.prepareStatement("UPDATE Accountant SET Name = ?, Login = ?, Password = ? WHERE ID = ?");
                preparedStatement.setString(1,fiotf.getText().trim());
                preparedStatement.setString(2,logintf.getText().trim());
                preparedStatement.setString(3,passwordtf.getText().trim());
                preparedStatement.setInt(4, stafftv.getSelectionModel().getSelectedItem().getId());
                preparedStatement.executeUpdate();
            }
        }

        if (staffcb.getValue().equals("Доставщик")) {
            if (stafftv.getSelectionModel().getSelectedItem() != null) {
                PreparedStatement preparedStatement = conn.prepareStatement("UPDATE Deliveryman SET Name = ?, Login = ?, Password = ? WHERE ID = ?");
                preparedStatement.setString(1,fiotf.getText().trim());
                preparedStatement.setString(2,logintf.getText().trim());
                preparedStatement.setString(3,passwordtf.getText().trim());
                preparedStatement.setInt(4, stafftv.getSelectionModel().getSelectedItem().getId());
                preparedStatement.executeUpdate();
            }
        }
        update();

    }

    @FXML
    public void initialize() throws SQLException {
        Name_staff.setCellValueFactory(new PropertyValueFactory<>("name"));
        Login_staff.setCellValueFactory(new PropertyValueFactory<>("login"));
        Password_staff.setCellValueFactory(new PropertyValueFactory<>("password"));

        stafflistcb.add("Менеджер");
        stafflistcb.add("Бухгалтер");
        stafflistcb.add("Доставщик");
        staffcb.setItems(stafflistcb);

        staffcb.setOnAction(e -> {
            try {
                update();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

    }
    public void update() throws SQLException {
        if (staffcb.getValue().equals("Менеджер")) {
            stafflist.clear();
            PreparedStatement preparedStatement = conn.prepareStatement("select * from Manager");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                stafflist.add(new AdminstaffPOJO(resultSet.getInt("ID"),resultSet.getString("Name"), resultSet.getString("Login"), resultSet.getString("Password")));
            }
            stafftv.setItems(stafflist);
        }

        if (staffcb.getValue().equals("Бухгалтер")) {
            stafflist.clear();
            PreparedStatement preparedStatement = conn.prepareStatement("select * from Accountant");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                stafflist.add(new AdminstaffPOJO(resultSet.getInt("ID"),resultSet.getString("Name"), resultSet.getString("Login"), resultSet.getString("Password")));
            }
            stafftv.setItems(stafflist);
        }

        if (staffcb.getValue().equals("Доставщик")) {
            stafflist.clear();
            PreparedStatement preparedStatement = conn.prepareStatement("select * from Deliveryman");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                stafflist.add(new AdminstaffPOJO(resultSet.getInt("ID"),resultSet.getString("Name"), resultSet.getString("Login"), resultSet.getString("Password")));
            }
            stafftv.setItems(stafflist);
        }
    }



}
