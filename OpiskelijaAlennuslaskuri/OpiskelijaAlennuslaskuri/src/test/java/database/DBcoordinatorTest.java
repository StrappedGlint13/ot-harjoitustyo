package database;


import database.DBcoordinator;
import domain.DomainService;
import domain.Product;
import domain.User;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import junit.framework.TestCase;
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
public class DBcoordinatorTest{
   
    DBcoordinator testdb;
    DomainService domainservice;
    
    @Before
    public void setUp() throws Exception {

        try {
            Connection connection = connect();
            testdb = new DBcoordinator("test.db");
            PreparedStatement setDatabase = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Users(id INTEGER AUTO_INCREMENT PRIMARY KEY, userName VARCHAR(200), email VARCHAR(200), studentNumber VARHAR(200), passWord VARCHAR (200));");
            setDatabase.execute();
            setDatabase.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBcoordinatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        testdb.setDatabase();
    }
    
    @After
    public void tearDown() {
        File file = new File(":memory:");
        file.delete();
    }
    
    @Test
    public void createUserWorks() {
       User user = new User ("Jaska","jokunen@populus","10292","kaikkea");
       testdb.createUser(user);
       
       user = testdb.findTheUser(user.getUserName(), user.getPassword());
    }
    
    @Test
    public void createProductWorks() {
       Product product = new Product("Jaska",0.0,0.0,0.0);
       testdb.createProduct(product);
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
    //In progress
    /*
    @Test
    public void listAllTheUsers() {
        User testuser1 = new User("","","","");
        User testuser2 = new User("","","","");
        User testuser3 = new User("","","","");
        List testusers = new ArrayList<>();
        
        try {
            Connection connection = connect();
            testdb.createUser(testuser1);
            testdb.createUser(testuser2);
            testdb.createUser(testuser3);
            
            testusers = testdb.listAlltheUsers();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBcoordinatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertTrue(testuser1.equals(testusers.get(0)));
       
        
    }
    */
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
