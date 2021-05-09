package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;


public class QlawnayaStranica extends Application {

    public static void start(String[] args) {
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
        final Connection finalcon = con;
        MySQLFX1 obekt = new MySQLFX1();
        Label zadanie_l = new Label("zadanie:");
        TableView<UserData> tablici = new TableView<>();
        TableColumn ID = new TableColumn("ID");
        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        TableColumn infotask = new TableColumn("infotask");
        infotask.setCellValueFactory(new PropertyValueFactory<>("infotask"));
        TableColumn daytask = new TableColumn("daytask");
        daytask.setCellValueFactory(new PropertyValueFactory<>("daytask"));
        TableColumn datetask = new TableColumn("datetask");
        datetask.setCellValueFactory(new PropertyValueFactory<>("datetask"));
        TableColumn category = new TableColumn("category");
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        TableColumn solvedornot = new TableColumn("solvedornot");
        solvedornot.setCellValueFactory(new PropertyValueFactory<>("solvedornot"));
        tablici.setPrefWidth(500);
        tablici.setItems(obekt.fill_table("SELECT * FROM javafx.tasks"));
        tablici.getColumns().addAll(ID, infotask, daytask, datetask, category, solvedornot);
        TextField zadanie_t = new TextField();
        TextField obnovit = new TextField();


        Label den_l = new Label("den:");
        Label dobavit_kategoriyu = new Label();
        TextField plus = new TextField();
        TextField den_t = new TextField();
        Label kategoriya_l = new Label("kategoriya:");
        ComboBox kategoriya_c = new ComboBox();
        Button plusi = new Button("+");
        plusi.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                kategoriya_c.getItems().add(plus.getText());
            }
        });
        kategoriya_c.getItems().addAll("uroki", "otdix");
        Label poisk_l = new Label("poisk");
        TextField poisk_t = new TextField();
        Button poiski = new Button("Poisk");
        poiski.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String zapros = "SELECT * FROM javafx.tasks WHERE infotask LIKE '" + poisk_t.getText() + "%'";

                tablici.setItems(obekt.fill_table(zapros));
            }
        });
        ComboBox status = new ComboBox();
        status.getItems().addAll("gotovo", "ne gotovo");
        DatePicker datePicker = new DatePicker();
        Button sozdat = new Button("sozdat");
        sozdat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String zapros = "insert into tasks(infotask,daytask,datetask,category,solvedornot)values(?,?,?,?,?)";

                try {
                    PreparedStatement stmt = finalcon.prepareStatement(zapros, Statement.RETURN_GENERATED_KEYS);
                    stmt.setString(1, zadanie_t.getText());
                    stmt.setString(2, den_t.getText());
                    stmt.setString(3, String.valueOf(datePicker.getValue()));
                    stmt.setString(4, String.valueOf(kategoriya_c.getValue()));
                    stmt.setString(5, String.valueOf(status.getValue()));

                    stmt.executeUpdate();
                    Main test = new Main();
                    test.pokazatokno();
                    stage.hide();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });


        stage.setTitle("Radio button in JavaFx");
        FlowPane flowPane = new FlowPane(10, 10);
        flowPane.getChildren().addAll(zadanie_l, zadanie_t, den_l, den_t, datePicker, kategoriya_l, kategoriya_c, dobavit_kategoriyu, plus, plusi, poisk_l, poisk_t, poiski, status, sozdat, tablici, obnovit);
        Scene scene = new Scene(flowPane, 400, 400);
        stage.setScene(scene);
        stage.show();

    }


    Stage stage2 = new Stage();
    int id_p = 0;

    public void qlawstranica(int id_polzovatelya) {
        try {
            id_p = id_polzovatelya;
            start(stage2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MySQLFX1 {
    ResultSet rset;
    Connection con = null;
    String url = "jdbc:mysql://localhost:3306/javafx?useTimezone=true&serverTimezone=GMT";
    String user = "root";
    String password = "12345";

    ObservableList<UserData> fill_table(String sqlzapros) {
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        final ObservableList<UserData> data = FXCollections.observableArrayList();
        try {
            Statement stmt = con.createStatement();
            String zapros = sqlzapros;
            ResultSet rset = stmt.executeQuery(zapros);
            while (rset.next()) {
                data.add(new UserData(
                        rset.getString(1),
                        rset.getString(2),
                        rset.getString(3),
                        rset.getString(4),
                        rset.getString(5),
                        rset.getString(6)
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return data;
    }

}