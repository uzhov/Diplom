package sample.Controllers;

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

public class Addcatalog {

    Connection conn = new DataBase().getConn();

    @FXML
    private TextField ctegorytf;

    @FXML
    private TextField nametf;

    @FXML
    private TextField numbertf;

    @FXML
    private TextField pricetf;

    public Addcatalog() throws SQLException, ClassNotFoundException {
    }

    @FXML
    void addbt() throws SQLException {

        PreparedStatement statement = conn.prepareStatement("INSERT INTO Products VALUES(?,?,?)");
            statement.setString(1, nametf.getText().trim());
            statement.setInt(2, Integer.parseInt(pricetf.getText().trim()));
        statement.setString(3, ctegorytf.getText().trim());
            statement.executeUpdate();
            nametf.setText(""); pricetf.setText(""); ctegorytf.setText("");

    }

    @FXML
    void changebt() throws SQLException {

        PreparedStatement statement = conn.prepareStatement("UPDATE Products SET Name = ?, Price = ?, Category = ? WHERE ID = ?");
         statement.setString(1, nametf.getText().trim());
         statement.setInt(2, Integer.parseInt(pricetf.getText().trim()));
        statement.setString(3, ctegorytf.getText().trim());
        statement.setInt(4, Integer.parseInt(numbertf.getText().trim()));
         statement.executeUpdate();
         nametf.setText(""); pricetf.setText(""); numbertf.setText(""); ctegorytf.setText("");
    }

    @FXML
    public void initialize() {

        numbertf.setOnAction(e -> {
            try {
                PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Products WHERE ID = ?");
                preparedStatement.setInt(1, Integer.parseInt(numbertf.getText().trim()));
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                    nametf.setText(resultSet.getString("Name"));
                    pricetf.setText(String.valueOf(resultSet.getInt("Price")));
                    ctegorytf.setText(resultSet.getString("Category"));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        } );

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
        ctegorytf.getScene().getWindow().hide();

    }

}
