package Ui;

import StudentDiscountCalculator.UserRegister;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private UserRegister users;

    public static void main(String[] args) {
        setDatabase();
    }

    @Override
    public void start(Stage stage) throws Exception {

    }

    private static void setDatabase() {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:./OpiskelijaAlennuslaskuri", "sa", "")) {
            conn.prepareStatement("DROP TABLE Userregister IF EXISTS;").executeUpdate();
            conn.prepareStatement("CREATE TABLE Userregister (\n"
                    + "    id INTEGER AUTO_INCREMENT PRIMARY KEY,\n"
                    + "    username VARCHAR(200),\n"
                    + "    email VARCHAR(20),\n"
                    + "    studentnumber INTEGER,\n"
                    + ");").executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
