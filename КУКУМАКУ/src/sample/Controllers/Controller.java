package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {
    public static int userid;
    Connection conn = new DataBase().getConn();

    @FXML
    private TextField login;

    @FXML
    private TextField password;

    @FXML
    private ComboBox<String> rolecb;
    ObservableList<String> rolelist = FXCollections.observableArrayList();

    public Controller() throws SQLException, ClassNotFoundException {
    }

    @FXML
    public void input() throws SQLException {
        int width = 0;
        int height = 0;
        PreparedStatement preparedStatement = conn.prepareStatement("");
        try {
            switch (rolecb.getValue().trim()) {
                case "Administrator": width = 568; height = 368;
                    preparedStatement = conn.prepareStatement("select ID from Administrator where Login = ? AND Password = ?");
                    break;
                case "Manager": width = 633; height = 477;
                    preparedStatement = conn.prepareStatement("select ID from Manager where Login = ? AND Password = ?");
                    break;
                case "Accountant": width = 428; height = 213;
                    preparedStatement = conn.prepareStatement("select ID from Accountant where Login = ? AND Password = ?");
                    break;
                case "Deliveryman": width = 600; height = 400;
                    preparedStatement = conn.prepareStatement("select ID from Deliveryman where Login = ? AND Password = ?");
                    break;
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(("Не выбрана роль"));
        }

        try {
            preparedStatement.setString(1, login.getText().trim());
            preparedStatement.setString(2, password.getText().trim());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                userid = resultSet.getInt("ID");
                Stage primaryStage = new Stage();
                Parent root = null;
                    root = FXMLLoader.load(getClass().getResource("fxml/" + rolecb.getValue().trim() + ".fxml"));
                primaryStage.setTitle("Мебельная компания МИФ");
                primaryStage.setScene(new Scene(root, width, height));
                primaryStage.show();
                password.getScene().getWindow().hide();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(("Некорректно заполнены поля"));
            alert.showAndWait();
        }
    }

    @FXML
    public void initialize() {
        rolelist.add("Administrator");
        rolelist.add("Manager");
        rolelist.add("Accountant");
        rolelist.add("Deliveryman");
        rolecb.setItems(rolelist);

    }

}
