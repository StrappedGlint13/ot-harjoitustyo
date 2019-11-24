package Ui;

import Dao.DBcoordinator;
import StudentDiscountCalculator.UserRegister;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.sqlite.core.DB;

public class Main extends Application {

    private UserRegister users;

    private Scene loginView;
    private Scene calculatorView;
    private Scene regView;

    public static void main(String[] args) {
        launch(Main.class);
        
    }

    @Override
    public void start(Stage ikkuna) throws Exception {
        DBcoordinator DB = new DBcoordinator("DB");
        DB.setDatabase();
        
        HBox putUsername = new HBox(10);
        HBox putPassword = new HBox(10);
        Label usernameText = new Label("Username");
        TextField usernameField = new TextField();
        Label password = new Label("Password");
        TextField Password = new TextField();

        putUsername.getChildren().addAll(usernameText, usernameField);
        putPassword.getChildren().addAll(password, Password);

        Button loginButton = new Button();
        Button regButton = new Button();

        loginButton.setText("Login");
        regButton.setText("New user?");

        GridPane asettelu = new GridPane();

        asettelu.setAlignment(Pos.CENTER);
        asettelu.setVgap(10);
        asettelu.setHgap(10);
        asettelu.setPadding(new Insets(10, 10, 10, 10));
        asettelu.add(putUsername, 3, 3);
        asettelu.add(putPassword, 3, 5);
        asettelu.add(loginButton, 3, 7);
        asettelu.add(regButton, 3, 9);

        loginButton.setOnAction(e -> {
            ikkuna.setScene(loginView);
        });

        regButton.setOnAction(e -> {
            ikkuna.setScene(regView);
        });

        loginView = new Scene(asettelu, 400, 300);
        ikkuna.setScene(loginView);
        ikkuna.show();

    }



}

