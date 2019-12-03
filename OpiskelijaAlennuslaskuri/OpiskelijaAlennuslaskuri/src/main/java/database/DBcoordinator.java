package database;

import domain.Product;
import domain.User;
import ui.Main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
                    "CREATE TABLE IF NOT EXISTS Products(id INTEGER AUTO_INCREMENT PRIMARY KEY, name VARCHAR(200), INTEGER normalPrice, discountPercentage INTEGER, studentPrice INTEGER);");
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
            PreparedStatement newUser = connection.prepareStatement("INSERT INTO Users(userName, passWord, email, studentNumber)"
                    + " VALUES (?, ?, ?, ?);");

            newUser.setString(1, user.getUserName());
            newUser.setString(2, user.getPassword());
            newUser.setString(3, user.getStudentNumber());
            newUser.setString(4, user.getEmail());

            newUser.executeUpdate();
            newUser.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBcoordinator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void createProduct(Product product) {
        Connection connection = connect();

        try {
            PreparedStatement newUser = connection.prepareStatement("INSERT INTO Products(name, normalPrice, discountPercentage, studentPrice)"
                    + " VALUES (?, ?, ?, ?);");

            newUser.setString(1, product.getName());
            newUser.setDouble(2, product.getNormalPrice());
            newUser.setDouble(3, product.getDiscountPercentage());
            newUser.setDouble(4, product.getStudentPrice());

            newUser.executeUpdate();
            newUser.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBcoordinator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //In progress
    /*
    public List<User> listAlltheUsers() {
        List<User> users = new ArrayList<>();
        Connection connection = connect();
        try {
            PreparedStatement getUsers = connection.prepareStatement("SELECT * FROM Users;");
            ResultSet resultSet = getUsers.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setUserName(resultSet.getString("userName"));
                user.setEmail(resultSet.getString("email"));
                user.setStudentNumber(resultSet.getString("studentNumber"));
                user.setPassword(resultSet.getString("passWord"));
                users.add(user);

            }

            getUsers.close();
            resultSet.close();
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(DBcoordinator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
    */
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

    public boolean getProduct(String name) {
        Connection connection = connect();

        try {
            PreparedStatement newUser = connection.prepareStatement("SELECT FROM Products WHERE name=?");
            
            newUser.executeUpdate();
            newUser.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBcoordinator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    public User findTheUser(String userName, String passWord) {
        String dbUsername = null;
        String dbpassWord = null;
        User searchUser = new User(dbUsername, dbpassWord);
       
        try {
            Connection connection = connect();
            PreparedStatement findUser = connection.prepareStatement("SELECT userName, passWord FROM Users WHERE userName = ? AND passWord = ?;");
            findUser.setString(1, userName);
            findUser.setString(2, passWord);
            ResultSet rs = findUser.executeQuery();
           
            while(rs.next()) {
                String searchUN = rs.getString("userName");
                String searchPW = rs.getString("passWord");
                
                if (searchUN.equals(userName) && searchPW.equals(passWord)){
                    searchUser.setUserName(userName);
                    searchUser.setPassword(passWord);
                }
            }
            rs.close();
            connection.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(DBcoordinator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return searchUser;
    }

}
