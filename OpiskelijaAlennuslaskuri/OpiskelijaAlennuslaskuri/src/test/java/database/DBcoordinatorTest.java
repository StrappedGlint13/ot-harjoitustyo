package database;


import database.DBcoordinator;
import domain.DomainService;
import domain.User;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            testdb = new DBcoordinator(":memory:");    
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBcoordinatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @After
    public void tearDown() {
        File file = new File(":memory:");
        file.delete();
    }
    
    @Test
    public void createUserWorks() {
       User user = new User ("Jaska","jokunen@populus","10292","kaikkea");
       try {
            Connection connection = connect();
            testdb.createUser(user);
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBcoordinatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            String url = "jdbc:sqlite:" + testdb;
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

}
