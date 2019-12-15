/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import domain.Product;
import domain.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ui.CalculatorUi;

/**
 *
 * @author matibrax
 */
public class ProductDao {
    
    private String dataBase;

    public ProductDao(String dataBase) {
        
        this.dataBase = dataBase;
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
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
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
}
