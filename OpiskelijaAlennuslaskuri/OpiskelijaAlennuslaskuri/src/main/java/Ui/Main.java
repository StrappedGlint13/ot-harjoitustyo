package Ui;

import Dao.DBcoordinator;
import StudentDiscountCalculator.SDCService;
import StudentDiscountCalculator.User;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import org.sqlite.core.DB;

public class Main extends Application {

    private Scene loginView;
    private Scene calculatorView;
    private Scene regView;
    private Scene userView;
    private SDCService SDC;

    public static final Font ITALIC_FONT
            = Font.font(
                    "Verdana",
                    FontPosture.ITALIC,
                    Font.getDefault().getSize());

    public static void main(String[] args) {
        launch(Main.class);

    }

    @Override
    public void start(Stage window) throws Exception {
        DBcoordinator DB = new DBcoordinator("DB");
        DB.setDatabase();

        HBox putUsername = new HBox(10);
        HBox putPassword = new HBox(10);
        Label title = new Label("Login SDC");
        title.setStyle("-fx-font-weight: bold;");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        Label usernameText = new Label("Username");
        TextField usernameField = new TextField();
        Label password = new Label("Password");
        PasswordField Password = new PasswordField();

        usernameText.setFont(ITALIC_FONT);
        title.setFont(ITALIC_FONT);
        password.setFont(ITALIC_FONT);

        putUsername.getChildren().addAll(usernameText, usernameField);
        putPassword.getChildren().addAll(password, Password);

        Button loginButton = new Button();
        Button regButton = new Button();
        loginButton.setFont(ITALIC_FONT);
        regButton.setFont(ITALIC_FONT);

        loginButton.setText("Login");
        regButton.setText("New user?");

        GridPane layout = new GridPane();

        layout.setAlignment(Pos.CENTER);
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.add(title, 3, 0);
        layout.add(putUsername, 3, 3);
        layout.add(putPassword, 3, 5);
        layout.add(loginButton, 3, 7);
        layout.add(regButton, 3, 9);

        loginButton.setOnAction(e -> {
            window.setScene(loginView);
        });


        //New user layout
        BorderPane newUserLayout = new BorderPane();
        VBox labels = new VBox(10);
        labels.setPadding(new Insets(20,20,20,20));
        labels.setSpacing(20);
       
        Label setUsername = new Label("Set username");
        Label setPassword = new Label("Set password");
        Label email = new Label("Insert email address");
        Label studentNumber = new Label("Insert student number");
        
        VBox regTextFields = new VBox(10);
        
        TextField newUser = new TextField();
        TextField newPassword = new TextField();
        TextField newEmail = new TextField();
        TextField newStudentnumber = new TextField();
        
        regTextFields.setPadding(new Insets(20,20,0,0));
        regTextFields.setSpacing(20);

        Button addUser = new Button("Create new user");
        
        labels.getChildren().addAll(setUsername,setPassword,email,studentNumber);
        regTextFields.getChildren().addAll(newUser,newPassword,newEmail,newStudentnumber);
        
        newUserLayout.setTop(labels);
        newUserLayout.setRight(regTextFields);
        newUserLayout.setCenter(addUser);
 
        
        regButton.setOnAction(e -> {
            window.setScene(regView);
        });
        
        // User creation failure
        Label checkMessage = new Label();
        BorderPane checkPane = new BorderPane();
        Button backToCreation = new Button("OK");
        
        backToCreation.setOnAction(e -> {
            window.setScene(regView);
        });
       
        
        checkPane.setCenter(checkMessage);
        checkPane.setBottom(backToCreation);
   
        Scene bTC = new Scene(checkPane, 300, 300);
        
        // Adding new user
        addUser.setOnAction(e -> {
            String checkUsername = newUser.getText();
            String checkPassword = newPassword.getText();
            String checkEmail = newEmail.getText();
            String checkStudentNumber = newStudentnumber.getText();
            
            if (checkUsername.isEmpty() || checkPassword.isEmpty() || checkEmail.isEmpty() || checkStudentNumber.isEmpty()){
                checkMessage.setText("Fill all the fields!");
                checkMessage.setTextFill(Color.RED);
                window.setScene(bTC);
            }else if(checkPassword.contains("ä") || checkPassword.contains("å") || checkPassword.contains("ö")) {
                checkMessage.setText("Password does not meet requirements");
                checkMessage.setTextFill(Color.RED);
                window.setScene(bTC);
            }else if(checkUsername.length()<2|| checkUsername.length()>15) {
                checkMessage.setText("Username is too short or too long.");
                checkMessage.setTextFill(Color.RED);
                window.setScene(bTC);
            } else {
                DB.createUser(new User(newUser.getText(), newPassword.getText(), newEmail.getText(), newStudentnumber.getText()));
                window.setScene(loginView);
            }
        });

        //Different views
        regView = new Scene(newUserLayout, 400, 400);
        loginView = new Scene(layout, 300, 300);
        window.setScene(loginView);
        window.show();

    }

}
