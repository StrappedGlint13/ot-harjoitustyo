package domain;

import database.ProductDao;
import database.UserDao;
import java.util.ArrayList;
import java.util.List;
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
public class DomainServiceTest {

    DomainService testDservice;
    Product product1;
    User user;

    @Before
    public void setUp() {
        testDservice = new DomainService("test.db");
        user = new User(1,"jaska","passu","email");
        product1 = new Product(1, "kaffe", 2.0, 3.0, 4.0);
        testDservice.addProductDB(product1);
        
    }

    @Test
    public void checkIfUserExistsWorkd() {
        testDservice.createUser(user);
        testDservice.checkIfuserExist(user.getUserName(), user.getPassword());
        User testUser = new User(1, "jaska", "passu", "email");
        assertEquals(testUser, user);

    }
    
    @Test
    public void returningUserProductsWorks() {
        List<Product> testList = new ArrayList();
        Product product2 = new Product(1, "kaffe", 2.0, 3.0, 4.0);
        testDservice.addProductDB(product2);
        testList = testDservice.logIn(user.getUserName(), user.getPassword());
        assertEquals(testList.get(testList.size()-1), product1);
        
    }
    
    @Test
    public void getTotalStudentWorks() {
        List<Product> testList = new ArrayList();
        testList = testDservice.logIn(user.getUserName(), user.getPassword());
        
        double totalAmount = 0.0;
        double realAmount = testDservice.getTotalStudent(1);
        
        for (int i = 0; i < testList.size(); i++) {
            totalAmount += testList.get(0).getStudentPrice();
        }

        assertEquals(Double.hashCode(totalAmount), Double.hashCode(realAmount));
    }
    @Test
    public void getTotalNormalWorks() {
        List<Product> testList = new ArrayList();
        testList = testDservice.logIn(user.getUserName(), user.getPassword());
        
        double totalAmount = 0.0;
        double realAmount = testDservice.getTotalNormal(1);
        
        for (int i = 0; i < testList.size(); i++) {
            totalAmount += testList.get(0).getNormalPrice();
        }
 
        assertEquals(Double.hashCode(totalAmount), Double.hashCode(realAmount));
    }
    @Test
    public void getAverageWorks() {
        List<Product> testList = new ArrayList();
        testList = testDservice.logIn(user.getUserName(), user.getPassword());
        double totalStudent = 0.0;
        double totalNormal = 0.0;
        for (int i = 0; i < testList.size(); i++) {
            totalStudent += testList.get(0).getStudentPrice();
            totalNormal += testList.get(0).getNormalPrice();
        }
        double average = 1-totalStudent/totalNormal;
        double methodAverage = testDservice.getAverage(totalNormal, totalStudent);
        
        assertEquals(Double.hashCode(average), Double.hashCode(methodAverage));
    }
    @Test
    public void iSuniqueWorks() {
        User user = new User(1, "jaska", "passu", "email");
        assertFalse(testDservice.iSunique(1));
        assertTrue(testDservice.iSunique(66));
        
  
    }
    
}
