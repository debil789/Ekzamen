package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.*;
import java.util.*;

import static javax.swing.text.html.HTML.Tag.SELECT;


public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        Connection con = null;
        ResultSet rset = null;
        String url = "jdbc:mysql://localhost:3306/javafx?useTimezone=true&serverTimezone=GMT";
        String user = "root";
        String password = "12345";
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Label name = new Label("Имя");
        TextField textField = new TextField();
        Label parol = new Label("Пароль");
        PasswordField passwordField = new PasswordField();
        Button login = new Button("login");
        Button udalit = new Button("udalit");
        Label nadpis_udalit =new Label("udalit po id");
        TextField delete = new TextField();

        Connection finalCon = con;
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String zapros = "SELECT * FROM polzovateli where Username='" + textField.getText() + "' and Parol='" + passwordField.getText() + "'";
                try {
                    Statement statement = finalCon.createStatement();
                    ResultSet resultSet = statement.executeQuery(zapros);
                    boolean esli_polzovatel = false;
                    int id_polzovatelya = 0;
                    while (resultSet.next()) {
                        esli_polzovatel = true;
                        id_polzovatelya = resultSet.getInt(1);
                    }
                    if (esli_polzovatel) {
                        QlawnayaStranica test = new QlawnayaStranica();
                        test.qlawstranica(id_polzovatelya);
                        stage.hide();
                        finalCon.close();
                    } else {
                        System.out.println("Ne pravilno");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        udalit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String sqlcommand = "DELETE FROM polzovateli WHERE ID = ?";
                PreparedStatement stmt = null;
                try {
                    stmt = finalCon.prepareStatement(sqlcommand, Statement.RETURN_GENERATED_KEYS);
                    stmt.setString(1, String.valueOf(delete.getText()));
                    stmt.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });

        Button sozdat = new Button("sozdat");
        sozdat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                register test = new register();
                test.registtaciya(10);

            }
        });
        FlowPane flowPane = new FlowPane(Orientation.VERTICAL, 10, 10);
        flowPane.getChildren().addAll(name, textField, parol, passwordField, login, sozdat,nadpis_udalit,delete,udalit);
        flowPane.setPadding(new Insets(10));
        Scene scene = new Scene(flowPane, 250, 200);
        stage.setScene(scene);
        stage.setTitle("Radio button in JavaFx");
        stage.show();
    }

    Stage stage = new Stage();

    public void pokazatokno() {
        start(stage);
    }

}