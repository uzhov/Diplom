package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DataBase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Addmanagerchange {

    Connection conn = new DataBase().getConn();

    @FXML
    private TextField idnumber1;

    @FXML
    private TextField nuber4tf;

    @FXML
    private TextField product3tf;

    @FXML
    private TextField quantity3tf;

    @FXML
    private TextField totalprice3tf;

    public Addmanagerchange() throws SQLException, ClassNotFoundException {
    }

    @FXML
    void changebt(ActionEvent event) throws SQLException {

        PreparedStatement statement = conn.prepareStatement("UPDATE Adddeliveryman SET Number2 = ?, Product = ?, Quantity = ?, Totalprice = ? WHERE ID = ?");
        statement.setString(1, nuber4tf.getText().trim());
        statement.setString(2, product3tf.getText().trim());
        statement.setString(3, quantity3tf.getText().trim());
        statement.setString(4, totalprice3tf.getText().trim());
        statement.setInt(5,Integer.parseInt(idnumber1.getText().trim()));
        statement.executeUpdate();
        nuber4tf.setText(""); product3tf.setText(""); quantity3tf.setText(""); totalprice3tf.setText(""); idnumber1.setText("");

    }

    @FXML
    public void initialize() {

        idnumber1.setOnAction(e -> {
            try {
                PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Adddeliveryman WHERE ID = ?");
                preparedStatement.setInt(1, Integer.parseInt(idnumber1.getText().trim()));
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    nuber4tf.setText(resultSet.getString("Number2"));
                    product3tf.setText(resultSet.getString("Product"));
                    quantity3tf.setText(resultSet.getString("Quantity"));
                    totalprice3tf.setText(resultSet.getString("Totalprice"));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } );

    }

    @FXML
    void exet(ActionEvent event) {
        Stage primaryStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("fxml/Addmanager.fxml"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        primaryStage.setTitle("Мебельная компания МИР");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        totalprice3tf.getScene().getWindow().hide();
    }

}
