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


public class register extends Application {

    public static void start(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
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
        final Connection finalcon = con;

        Label name = new Label("name");
        Label surname = new Label("surname");
        Label username = new Label("username");
        Label parol = new Label("password");

        TextField name_text = new TextField();
        TextField surname_text = new TextField();
        TextField username_text = new TextField();
        TextField parol_text = new TextField();
        Button registraciya = new Button("zaregistrirovatsya0");
        registraciya.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String zapros = "insert into polzovateli(Username,Parol,Imya,Familiya)values(?,?,?,?)";
                try {
                    PreparedStatement stmt = finalcon.prepareStatement(zapros, Statement.RETURN_GENERATED_KEYS);
                    stmt.setString(1, username_text.getText());
                    stmt.setString(2, parol_text.getText());
                    stmt.setString(3, name_text.getText());
                    stmt.setString(4, surname_text.getText());
                    stmt.executeUpdate();
                    Main test = new Main();
                    test.pokazatokno();
                    stage.hide();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });
        FlowPane flowPane = new FlowPane(Orientation.VERTICAL, 10, 10);
        flowPane.getChildren().addAll(name, name_text, username, username_text, parol, parol_text, surname, surname_text, registraciya);
        flowPane.setPadding(new Insets(10));
        Scene scene = new Scene(flowPane, 250, 200);
        stage.setScene(scene);
        stage.setTitle("Radio button in JavaFx");
        stage.show();
    }

    Stage stage = new Stage();
    int id_p = 0;

    public void registtaciya(int id_polzovatelya) {
        try {
            start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

