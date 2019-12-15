package ui;

import database.ProductDao;
import database.UserDao;
import domain.DomainService;
import domain.Product;
import domain.User;
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
    
    private UserDao userDao = new UserDao("db");
    private ProductDao productDao = new ProductDao("db");
    private DomainService dService = new DomainService(userDao, productDao);

    public static final Font ITALIC_FONT
            = Font.font(
                    "Verdana",
                    FontPosture.ITALIC,
                    Font.getDefault().getSize());

    private ObservableList<Product> data
            = FXCollections.observableArrayList();
    
    @Override 
    public void init() throws Exception {
        userDao.setDatabase();
    }


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
        VBox errorBox1 = new VBox(20);
        errorBox1.setPadding(new Insets(9, 9, 9, 5));
        Button backToLogin = new Button("OK");

        backToLogin.setOnAction(e -> {
            window.setScene(loginView);
        });

        errorBox1.getChildren().addAll(errorMessage1, backToLogin);
        errorPane1.setCenter(errorBox1);
        Scene errorScene1 = new Scene(errorPane1, 300, 100);

        loginButton.setOnAction(e -> {
            String checkUsername = usernameField.getText();
            String checkPassword = passwordField.getText();

            if (dService.checkIfuserExist(checkUsername, checkPassword) == true) {
                window.setScene(calculatorView);
            } else {
                window.setScene(errorScene1);
            }

        });

        regButton.setOnAction(e -> {
            window.setScene(regView);
        });

        // newUserLayout
        BorderPane newUserLayout = new BorderPane();
        VBox regLabels = new VBox(10);
        VBox regTextFields = new VBox(10);
        regLabels.setPadding(new Insets(9, 9, 9, 5));
        regLabels.setSpacing(24);

        Label setUsername = new Label("Set username\n *min two(2) characters");
        Label email = new Label("Insert email address\n\n");
        Label studentNumber = new Label("Insert student number\n");
        Label setPassword = new Label("Set password\n *Don't use scandinavian\n characters(ä,ö,å)");

        TextField newUser = new TextField();
        TextField newPassword = new TextField();
        TextField newEmail = new TextField();
        TextField newStudentnumber = new TextField();

        regTextFields.setPadding(new Insets(20, 20, 20, 20));
        regTextFields.setSpacing(24);

        Button addUserButton = new Button("Create new user");
        Button backButton = new Button("Back");

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
        Button backToCreation = new Button("OK");

        backToCreation.setOnAction(e -> {
            window.setScene(regView);
        });

        errorBox2.getChildren().addAll(errorMessage2, backToCreation);
        errorPane2.setTop(errorBox2);
        Scene errorScene = new Scene(errorPane2, 200, 200);

        addUserButton.setOnAction(e -> {
            String checkUsername = newUser.getText();
            String checkPassword = newPassword.getText();
            String checkEmail = newEmail.getText();
            String checkStudentNumber = newStudentnumber.getText();
            User testUser = new User(checkUsername, checkPassword, checkEmail, checkStudentNumber);

            if (checkUsername.isEmpty() || checkPassword.isEmpty() || checkEmail.isEmpty() || checkStudentNumber.isEmpty()) {
                errorMessage2.setText("Fill all the fields!");
                window.setScene(errorScene);
            } else if (checkPassword.contains("ä") || checkPassword.contains("å") || checkPassword.contains("ö")) {
                errorMessage2.setText("Password does not meet requirements");
                window.setScene(errorScene);
            } else if (checkUsername.length() < 2 || checkUsername.length() > 15) {
                errorMessage2.setText("Username is too short or too long.");
                window.setScene(errorScene);
            } else {
                dService.createUser(testUser);
                window.setScene(loginView);
            }
        });

        // CalculatorView
        BorderPane calculatorPane = new BorderPane();

        HBox topCalPanel = new HBox(850);
        Button logoutButton = new Button("Logout");
        logoutButton.setFont(ITALIC_FONT);
        logoutButton.setOnAction(e -> {
            window.setScene(loginView);
        });
        topCalPanel.setAlignment(Pos.BOTTOM_RIGHT);
        topCalPanel.getChildren().addAll(logoutButton);
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

        TableViewSelectionModel selectionModel = tableView.getSelectionModel();
        tableView.setPlaceholder(new Label("You have not made any purchases"));
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setPrefWidth(600);
        tableView.setPrefHeight(500);
        selectionModel.setSelectionMode(SelectionMode.MULTIPLE);

        //adding the product to the tracker
        HBox addingBox = new HBox();

        final TextField addProductName = new TextField();
        addProductName.setPromptText("Product name");
        addProductName.setMaxWidth(column1.getPrefWidth());
        final TextField addNormalPrice = new TextField();
        addNormalPrice.setMaxWidth(column2.getPrefWidth());
        addNormalPrice.setPromptText("Normal price");
        final TextField addStudentPrice = new TextField();
        addStudentPrice.setMaxWidth(column3.getPrefWidth());
        addStudentPrice.setPromptText("Student price");
        final TextField addDiscoutedPercentage = new TextField();
        addDiscoutedPercentage.setMaxWidth(column3.getPrefWidth());
        addDiscoutedPercentage.setPromptText("Discounted percentage");

        tableView.setItems(data);

        final Button addStudentProduct = new Button("Add");
        addStudentProduct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                double addNP = 0.0;
                addNP = Double.parseDouble(addNormalPrice.getText());
                double addSP = 0.0;
                addSP = Double.parseDouble(addStudentPrice.getText());
                double addDP = 0.0;
                addDP = Double.parseDouble(addDiscoutedPercentage.getText());

                data.add(new Product(
                        addProductName.getText(),
                        addNP,
                        addSP,
                        addDP
                ));

                dService.addProductDB(data.get(data.size() - 1));
                addProductName.clear();
                addNormalPrice.clear();
                addStudentPrice.clear();
                addDiscoutedPercentage.clear();
            }
        });

        addingBox.getChildren().addAll(addProductName, addNormalPrice, addStudentPrice, addDiscoutedPercentage, addStudentProduct);
        addingBox.setSpacing(3);

        VBox tableViewBox = new VBox(tableView, addingBox);

        calculatorPane.setCenter(tableViewBox);

        //Scenes
        calculatorView = new Scene(calculatorPane, 1000, 600);
        regView = new Scene(newUserLayout, 400, 300);
        loginView = new Scene(loginLayout, 300, 300);
        window.setScene(loginView);
        window.show();
    }

    @Override
    public void stop() {

    }
}
