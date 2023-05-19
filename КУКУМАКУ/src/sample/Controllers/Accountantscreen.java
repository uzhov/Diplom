package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DataBase;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Accountantscreen {

    Connection conn = new DataBase().getConn();

    @FXML
    private Label label;

    @FXML
    private ComboBox<String> monthcb;

    ObservableList<String> monthlist = FXCollections.observableArrayList();

    @FXML
    private TextField yeartf;

    @FXML
    private ComboBox<String> monthcb1;

    @FXML
    private TextField yeartf1;


    public Accountantscreen() throws SQLException, ClassNotFoundException {
    }

    @FXML
    public void formreport() {
        int count = 0;
        String outputFileName = "Report.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            String line;
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT Orders.ID, Orders.Data_order, \n" +
                    "SUM(CASE WHEN Order_Part.Order_ID = Orders.ID THEN Order_Part.Total_Price ELSE 0 END) \n" +
                    "AS Price FROM Orders, Order_Part \n" +
                    "WHERE Orders.Data_order BETWEEN'" + yeartf.getText().trim() + "-" + monthcb.getValue().substring(monthcb.getValue().indexOf("_") + 1) + "-01' AND '" + yeartf1.getText().trim() + "-" + monthcb1.getValue().substring(monthcb1.getValue().indexOf("_") + 1) + "-28' \n" +
                    "GROUP BY Orders.ID, Orders.Data_order");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                line = resultSet.getInt("ID") + " " + resultSet.getString("Data_order") + " Прибыль: " + resultSet.getInt("Price")/2 + "\n";
                writer.write(line);
                count += resultSet.getInt("Price")/2;
            }
            writer.write("Итоговая прибыль: " + count);
            label.setText("Отчет сформирован");
        }
        catch (IOException | SQLException e) {
            e.printStackTrace();
            label.setText("Ошибка");
        }
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
        monthcb.getScene().getWindow().hide();

    }

    @FXML
    public void initialize(){

        monthlist.add("Январь_01");
        monthlist.add("Февраль_02");
        monthlist.add("Март_03");
        monthlist.add("Апрель_04");
        monthlist.add("Май_05");
        monthlist.add("Июнь_06");
        monthlist.add("Июль_07");
        monthlist.add("Август_08");
        monthlist.add("Сентябрь_09");
        monthlist.add("Октябрь_10");
        monthlist.add("Ноябрь_11");
        monthlist.add("Декабрь_12");
        monthcb.setItems(monthlist);
        monthcb1.setItems(monthlist);
    }

}
