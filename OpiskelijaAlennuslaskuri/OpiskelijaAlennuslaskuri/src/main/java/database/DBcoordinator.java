package database;

import domain.User;
import ui.Main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBcoordinator {

    private String dataBase;

    public DBcoordinator(String dataBase) {

        this.dataBase = dataBase;
    }

    public void setDatabase() {
        try {
            Connection connection = connect();
            PreparedStatement setDatabase = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Users(id INTEGER AUTO_INCREMENT PRIMARY KEY, userName VARCHAR(200), email VARCHAR(200), studentNumber VARHAR(200), passWord VARCHAR (200));");
            setDatabase.execute();
            setDatabase.close();

            PreparedStatement setDatabase1 = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Products(id INTEGER AUTO_INCREMENT PRIMARY KEY, name VARCHAR(200), discountPercentage INTEGER, discount INTEGER);");
            setDatabase1.execute();
            setDatabase1.close();
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(Main.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createUser(User user) {
        Connection connection = connect();

        try {
            PreparedStatement newUser = connection.prepareStatement("INSERT INTO Users(userName, email, studentNumber, passWord)"
                    + " VALUES (?, ?, ?, ?);");

            newUser.setString(1, user.getUserName());
            newUser.setString(2, user.getEmail());
            newUser.setString(3, user.getStudentNumber());
            newUser.setString(4, user.getPassword());

            newUser.executeUpdate();
            newUser.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBcoordinator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<User> getAllUsers() throws SQLException {
        Connection connection = connect();

        try {
            PreparedStatement newUser = connection.prepareStatement("INSERT INTO Users(userName, email, studentNumber, passWord)"
                    + " VALUES (?, ?, ?, ?);");

            newUser.executeUpdate();
            newUser.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBcoordinator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Connection connect() {
        Connection connection = null;
        try {
            String url = "jdbc:sqlite:" + dataBase;
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

}
