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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ui.CalculatorUi;

/**
 *
 * @author matibrax
 */
public class ProductDao implements Dao<Product, Integer> {
    
    private String dataBase;

    public ProductDao(String dataBase) {
        
        this.dataBase = dataBase;
    }
    
    /**
     * Luo uuden tuotteen tietokantaan
     *
     * @param product product
     *
     */
    
    @Override
    public void create(Product product) {
        Connection connection = connect();

        try {
            PreparedStatement newProduct = connection.prepareStatement("INSERT INTO Products(studentNumber_id, name, normalPrice, studentPrice, discountPercentage)"
                    + " VALUES (?, ?, ?, ?, ?);");
            
            newProduct.setInt(1, product.getStudentNumberID());
            newProduct.setString(2, product.getName());
            newProduct.setDouble(3, product.getNormalPrice());
            newProduct.setDouble(4, product.getStudentPrice());
            newProduct.setDouble(5, product.getDiscountPercentage());

            newProduct.executeUpdate();
            newProduct.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

     /**
     * Palauttaa parametrina annetun käyttäjän tuotteet tietokannasta
     *
     * @param user käyttäjä
     *
     * @return List<Product>
     */
    
    public List<Product> returnProducts(User user) {
        List<Product> products = new ArrayList(); 
        Connection connection = connect();
        
        try {
            PreparedStatement getProducts = connection.prepareStatement("SELECT * FROM Products WHERE studentNumber_id = ?;");
            getProducts.setInt(1, user.getStudentNumber());
            ResultSet resultSet = getProducts.executeQuery();
            while (resultSet.next()) {
                
                Product product = new Product(user.getStudentNumber(),"lol", 0.0, 0.0, 0.0);
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
    
     /**
     * Luo yhteyden tietokantaan
     *
     *
     * @return connection
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
    
     /**
     * Hakee käyttäjän normaalihintaiset tuotteet 
     *
     * @param key avain
     *
     * @return totalNormal
     */
    
  
    public double getNormalPrice(Integer key) { 
        Connection connection = connect();
        double totalNormal = 0.0;

        try {
            PreparedStatement getProducts = connection.prepareStatement("SELECT normalPrice FROM Products WHERE studentNumber_id = ?;");
            getProducts.setInt(1, key);
            ResultSet resultSet = getProducts.executeQuery();

            while (resultSet.next()) {
                totalNormal = totalNormal + resultSet.getDouble("normalPrice");
            }
            getProducts.close();
            resultSet.close();
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalNormal;
    }
    
      /**
     * Hakee käyttäjän opiskelija-alennettujen tuotteiden  
     *
     * @param key avain
     *
     * @return studentPrice
     */
    public double getStudentPrice(Integer key) { 
        Connection connection = connect();
        double studentPrice = 0.0;

        try {
            PreparedStatement getProducts = connection.prepareStatement("SELECT studentPrice FROM Products WHERE studentNumber_id = ?;");
            getProducts.setInt(1, key);
            ResultSet resultSet = getProducts.executeQuery();

            while (resultSet.next()) {
                studentPrice = studentPrice + resultSet.getDouble("studentPrice");
            }
            getProducts.close();
            resultSet.close();
            connection.close();

        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return studentPrice;
    }

}
