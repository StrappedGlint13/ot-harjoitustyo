/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import domain.DomainService;
import domain.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author matibrax
 */
public class ProductDaoTest {
    ProductDao testdb;
    DomainService domainservice;

    @Test
    public void createProductWorks() {
        Product product = new Product("Jaska", 0.0, 0.0, 0.0);
        testdb.createProduct(product);
        
        assertEquals(product, product);
        
    }
    /*
    @Test
    public void returnProductsWorks() {
       Product product = new Product("Jaska",0.0,0.0,0.0);
       Product product2 = new Product("Something", 0.0, 0.0, 0.0);
       ObservableList<Product> products = FXCollections.observableArrayList();
       products.add(product);
       products.add(product2);    
    }
     }
*/
    @Before
    public void setUp() throws Exception {

        try {
            Connection connection = connect();
            testdb = new ProductDao("test.db");
            PreparedStatement setDatabase = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Products(id INTEGER AUTO_INCREMENT PRIMARY KEY, name VARCHAR(200), normalPrice DOUBLE, studentPrice DOUBLE, discountPercentage DOUBLE);");
            setDatabase.execute();
            setDatabase.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        testdb.setDatabase();
    }
    private Connection connect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:test.db";
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
   
}