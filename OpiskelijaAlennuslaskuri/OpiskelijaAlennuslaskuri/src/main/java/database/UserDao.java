package database;

import domain.Product;
import domain.User;
import ui.CalculatorUi;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * Tietokantaa käsittelevä luokka
 *
 *
 * @author Matias Brax
 *
 */

public class UserDao implements Dao<User, Integer> {

    private String dataBase;

    public UserDao(String dataBase) {
        
        this.dataBase = dataBase;
    }
    
    /**
     * 
     *
     * Alustaa tietokannan
     *
     * 
     */

    public void setDatabase() {
        try {
            Connection connection = connect();
            PreparedStatement setDatabase = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Users(id INTEGER AUTO_INCREMENT PRIMARY KEY, userName VARCHAR(200), email VARCHAR(200), studentNumber VARHAR(200), passWord VARCHAR (200));");
            setDatabase.execute();
            setDatabase.close();

            PreparedStatement setDatabase1 = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Products(id INTEGER AUTO_INCREMENT PRIMARY KEY, name VARCHAR(200), normalPrice DOUBLE, studentPrice DOUBLE, discountPercentage DOUBLE);");
            setDatabase1.execute();
            setDatabase1.close();
            
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(CalculatorUi.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Luo uuden käyttäjän tietokantaan
     *
     * @param user käyttäjä
     * 
     */
    
    @Override
    public void create(User user) {
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
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void createProduct(Product product) {
        Connection connection = connect();

        try {
            PreparedStatement newProduct = connection.prepareStatement("INSERT INTO Products(name, normalPrice, studentPrice, discountPercentage)"
                    + " VALUES (?, ?, ?, ?);");

            newProduct.setString(1, product.getName());
            newProduct.setDouble(2, product.getNormalPrice());
            newProduct.setDouble(3, product.getStudentPrice());
            newProduct.setDouble(4, product.getDiscountPercentage());

            newProduct.executeUpdate();
            newProduct.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    public ObservableList<Product> returnProducts(User user) {
        ObservableList<Product> products = FXCollections.observableArrayList(); 
        Connection connection = connect();
        try {
            PreparedStatement getProducts = connection.prepareStatement("SELECT * FROM Products;");
            ResultSet resultSet = getProducts.executeQuery();
            Product product = new Product("", 0.0, 0.0, 0.0);

            while (resultSet.next()) {
                product.setName(resultSet.getString("name"));
                product.setNormalPrice(resultSet.getDouble("normalPrice"));
                product.setStudentPrice(resultSet.getDouble("studentPrice"));
                product.setDiscountPercentage(resultSet.getDouble("discountPercentage"));
                products.add(product);

            }

            getProducts.close();
            resultSet.close();
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
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
    
    @Override
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
           
            while (rs.next()) {
                String searchUN = rs.getString("userName");
                String searchPW = rs.getString("passWord");
                
                if (searchUN.equals(userName) && searchPW.equals(passWord)) {
                    searchUser.setUserName(userName);
                    searchUser.setPassword(passWord);
                }
            }
            rs.close();
            connection.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return searchUser;
    }

}
