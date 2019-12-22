package ui;


import domain.DomainService;
import domain.Product;
import domain.User;
import java.text.DecimalFormat;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class CalculatorUi extends Application {

    private Scene loginView;
    private Scene calculatorView;
    private Scene regView;

    private int loggedUser;
    private double normalPrice;
    private double studentPrice;
    private double average;

    private ObservableList<Product> data = FXCollections.observableArrayList();

    private DomainService dService = new DomainService("db");

    public static final Font ITALIC_FONT
            = Font.font(
                    "Verdana",
                    FontPosture.ITALIC,
                    Font.getDefault().getSize());

    public static void main(String[] args) {
        launch(args);

    }

    @Override

    public void start(Stage window) throws Exception {
        HBox putUsername = new HBox(10);
        HBox putPassword = new HBox(10);
        Label title = new Label("Login SDC");
        title.setStyle("-fx-font-weight: bold;");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        Label usernameText = new Label("Username");
        TextField usernameField = new TextField();
        Label password = new Label("Password");
        PasswordField passwordField = new PasswordField();
        
        usernameText.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        password.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

        usernameText.setFont(ITALIC_FONT);
        title.setFont(ITALIC_FONT);
        password.setFont(ITALIC_FONT);

        putUsername.getChildren().addAll(usernameText, usernameField);
        putPassword.getChildren().addAll(password, passwordField);

        Button loginButton = new Button();
        loginButton.setFont(ITALIC_FONT);
        Button regButton = new Button();
        regButton.setFont(ITALIC_FONT);

        loginButton.setText("Login");
        regButton.setText("New user?");

        GridPane loginLayout = new GridPane();
        loginLayout.setStyle("-fx-background-color: linear-gradient(#E4EAA2, #9CD672)");
        loginLayout.setAlignment(Pos.CENTER);
        loginLayout.setVgap(10);
        loginLayout.setHgap(10);
        loginLayout.setPadding(new Insets(10, 10, 10, 10));
        loginLayout.add(title, 3, 0);
        loginLayout.add(putUsername, 3, 3);
        loginLayout.add(putPassword, 3, 5);
        loginLayout.add(loginButton, 3, 7);
        loginLayout.add(regButton, 3, 9);

        //errorScene 1
        Label errorMessage1 = new Label("User does not exists or password is wrong");
        errorMessage1.setTextFill(Color.RED);
        BorderPane errorPane1 = new BorderPane();
        errorPane1.setStyle("-fx-background-color:POWDERBLUE");
        VBox errorBox1 = new VBox(20);
        errorBox1.setPadding(new Insets(9, 9, 9, 5));
        Button backToLogin = new Button("OK");

        backToLogin.setOnAction(e -> {
            window.setScene(loginView);
        });

        errorBox1.getChildren().addAll(errorMessage1, backToLogin);
        errorPane1.setCenter(errorBox1);
        Scene errorScene1 = new Scene(errorPane1, 300, 100);

        // CalculatorView
        BorderPane calculatorPane = new BorderPane();
        Label loggedInUser = new Label();
        loggedInUser.setFont(ITALIC_FONT);
        loggedInUser.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        HBox topCalPanel = new HBox(850);
        Button logoutButton = new Button("Logout");
        logoutButton.setFont(ITALIC_FONT);
        logoutButton.setOnAction(e -> {
            window.setScene(loginView);
        });
        topCalPanel.setAlignment(Pos.BOTTOM_RIGHT);
        topCalPanel.getChildren().addAll(loggedInUser, logoutButton);
        calculatorPane.setTop(topCalPanel);

        TableView tableView = new TableView();

        TableColumn<String, Product> column1 = new TableColumn<>("Product name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<String, Product> column2 = new TableColumn<>("Normal price");
        column2.setCellValueFactory(new PropertyValueFactory<>("normalPrice"));
        TableColumn<String, Product> column3 = new TableColumn<>("Student price");
        column3.setCellValueFactory(new PropertyValueFactory<>("studentPrice"));
        TableColumn<String, Product> column4 = new TableColumn<>("Discount percentage");
        column4.setCellValueFactory(new PropertyValueFactory<>("discountPercentage"));
        tableView.getColumns().addAll(column1, column2, column3, column4);
        
        tableView.setStyle("-fx-background-color:POWDERBLUE");

        TableViewSelectionModel selectionModel = tableView.getSelectionModel();
        tableView.setPlaceholder(new Label("You have not made any purchases"));
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setPrefWidth(600);
        tableView.setPrefHeight(500);
        selectionModel.setSelectionMode(SelectionMode.MULTIPLE);

        //adding the product to the tracker
        HBox addingBox = new HBox(40);
        addingBox.setPadding(new Insets(30, 30, 30, 30));

        final TextField addProductName = new TextField();
        addProductName.setPromptText("Name");
        addProductName.setMaxWidth(column1.getPrefWidth());
        final TextField addNormalPrice = new TextField();
        addNormalPrice.setMaxWidth(column2.getPrefWidth());
        addNormalPrice.setPromptText("Normal €");
        final TextField addStudentPrice = new TextField();
        addStudentPrice.setMaxWidth(column3.getPrefWidth());
        addStudentPrice.setPromptText("Student €");

        DecimalFormat df = new DecimalFormat("#.##");
        VBox total = new VBox(10);
        total.setSpacing(20);
        VBox calculationsBox = new VBox(10);
        Label totalNormalPrice = new Label();
        Label totalStudentPrice = new Label();
        Label averageDiscountpercentage = new Label();
        totalNormalPrice.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        totalStudentPrice.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        averageDiscountpercentage.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

        calculationsBox.getChildren().addAll(totalNormalPrice, totalStudentPrice, averageDiscountpercentage);
        total.getChildren().addAll(calculationsBox);

        loginButton.setOnAction(e -> {
            String checkUsername = usernameField.getText();
            String checkPassword = passwordField.getText();
            if (dService.checkIfuserExist(checkUsername, checkPassword) == true) {
                data = FXCollections.observableArrayList(dService.logIn(checkUsername, checkPassword));
                loggedUser = dService.getStudentNumber(checkUsername, checkPassword);
                loggedInUser.setText(checkUsername);
                normalPrice = dService.getTotalNormal(loggedUser);
                totalNormalPrice.setText("Total sum of paid normal priced products: " + Double.toString(normalPrice) + " €");
                studentPrice = dService.getTotalStudent(loggedUser);
                totalStudentPrice.setText("Total sum of paid student discounted products: " + Double.toString(studentPrice) + " €");
                average = dService.getAverage(normalPrice, studentPrice);
                averageDiscountpercentage.setText("Average student discount percentage: " + df.format(average) + " %");
                tableView.setItems(data);
                window.setScene(calculatorView);
            } else {
                window.setScene(errorScene1);
            }

        });

        final Button addStudentProduct = new Button("Add");
        addStudentProduct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                double addNP = 0.0;
                addNP = Double.parseDouble(addNormalPrice.getText());
                double addSP = 0.0;
                addSP = Double.parseDouble(addStudentPrice.getText());
                double addDP = dService.getAverage(addNP, addSP);
                df.format(addNP);
                df.format(addSP);
                df.format(addDP);
                data.add(new Product(
                        loggedUser,
                        addProductName.getText(),
                        addNP,
                        addSP,
                        addDP
                ));

                dService.addProductDB(data.get(data.size() - 1));
                addProductName.clear();
                addNormalPrice.clear();
                addStudentPrice.clear();

                normalPrice = dService.getTotalNormal(loggedUser);
                totalNormalPrice.setText("Total sum of paid normal priced products: " + Double.toString(normalPrice) + " €");
                studentPrice = dService.getTotalStudent(loggedUser);
                totalStudentPrice.setText("Total sum of paid student discounted products: " + Double.toString(studentPrice) + " €");
                average = dService.getAverage(normalPrice, studentPrice);
                averageDiscountpercentage.setText("Average student discount percentage: " + df.format(average) + " %");
                ;
            }
        });

        addingBox.getChildren().addAll(addProductName, addNormalPrice, addStudentPrice, addStudentProduct, total);
        addingBox.setSpacing(20);

        VBox tableViewBox = new VBox(tableView, addingBox);
        calculatorPane.setStyle("-fx-background-color: linear-gradient(#E4EAA2, #9CD672)");
        calculatorPane.setCenter(tableViewBox);

        regButton.setOnAction(e -> {
            window.setScene(regView);
        });

        // newUserLayout
        BorderPane newUserLayout = new BorderPane();
        newUserLayout.setStyle("-fx-background-color: linear-gradient(#E4EAA2, #9CD672)");
        VBox regLabels = new VBox(10);
        VBox regTextFields = new VBox(10);
        regLabels.setPadding(new Insets(9, 9, 9, 5));
        regLabels.setSpacing(24);

        Label setUsername = new Label("Set username\n *min two(2) characters");
        Label email = new Label("Insert email address\n\n");
        Label studentNumber = new Label("Insert student number\n");
        Label setPassword = new Label("Set password\n *Don't use scandinavian\n characters(ä,ö,å)");

        setUsername.setFont(ITALIC_FONT);
        email.setFont(ITALIC_FONT);
        studentNumber.setFont(ITALIC_FONT);
        setPassword.setFont(ITALIC_FONT);
        
        TextField newUser = new TextField();
        TextField newPassword = new TextField();
        TextField newEmail = new TextField();
        TextField newStudentnumber = new TextField();

        regTextFields.setPadding(new Insets(20, 20, 20, 20));
        regTextFields.setSpacing(24);

        Button addUserButton = new Button("Create new user");
        Button backButton = new Button("Back");
        
        addUserButton.setFont(ITALIC_FONT);
        backButton.setFont(ITALIC_FONT);

        backButton.setOnAction(e -> {
            window.setScene(loginView);
        });
        regLabels.getChildren().addAll(setUsername, email, studentNumber, setPassword);
        regTextFields.getChildren().addAll(newUser, newEmail, newStudentnumber, newPassword, addUserButton, backButton);

        HBox settings = new HBox(10);
        settings.getChildren().addAll(regLabels, regTextFields);

        newUserLayout.setCenter(settings);

        //errorScene 2
        Label errorMessage2 = new Label();
        errorMessage2.setTextFill(Color.RED);
        BorderPane errorPane2 = new BorderPane();
        VBox errorBox2 = new VBox(20);
        errorBox2.setPadding(new Insets(40, 20, 20, 20));
        Button backToCreation = new Button("OK");
        
        errorPane2.setStyle("-fx-background-color:POWDERBLUE");

        backToCreation.setOnAction(e -> {
            window.setScene(regView);
        });

        errorBox2.getChildren().addAll(errorMessage2, backToCreation);
        errorPane2.setCenter(errorBox2);
        Scene errorScene = new Scene(errorPane2, 400, 300);

        addUserButton.setOnAction(e -> {
            String userName = newUser.getText();
            String passWord = newPassword.getText();
            String checkEmail = newEmail.getText();
            int studentNumberLength = newStudentnumber.getText().length();
            int checkStudentNumber = Integer.parseInt(newStudentnumber.getText());
            User testUser = new User(checkStudentNumber, userName, passWord, checkEmail);

            if (userName.isEmpty() || passWord.isEmpty() || checkEmail.isEmpty() || studentNumberLength == 0) {
                errorMessage2.setText("Fill all the fields!");
                window.setScene(errorScene);
            } else if (passWord.contains("ä") || passWord.contains("å") || passWord.contains("ö")) {
                errorMessage2.setText("Password does not meet requirements");
                window.setScene(errorScene);
            } else if (userName.length() < 2 || userName.length() > 15) {
                errorMessage2.setText("Username is too short or too long.");
                window.setScene(errorScene);
            } else if (dService.iSunique(checkStudentNumber) == false) {
                errorMessage2.setText("There is already user\n\nwith this student number.\n\n"
                        + " Insert your student number again \n\n or contact administrator");
                window.setScene(errorScene);
            } else if (studentNumberLength != 9) {
                errorMessage2.setText("Student number is invalid.\n\n"
                        + "Insert valid student number\n\n"
                        + "Student number should contains 9 numbers\n\n"
                        + "Example: 014681075");
                window.setScene(errorScene);
            } else {
                dService.createUser(testUser);
                window.setScene(loginView);
            }
        });

        //Scenes
        calculatorView = new Scene(calculatorPane, 1000, 500);
        regView = new Scene(newUserLayout, 400, 300);
        loginView = new Scene(loginLayout, 400, 400);
        window.setScene(loginView);
        window.show();
    }

    @Override
    public void stop() {

    }
}
