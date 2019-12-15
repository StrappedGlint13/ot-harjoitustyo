package domain;


import database.ProductDao;
import database.UserDao;
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
   
    DomainService domainService;
    UserDao userDao = new UserDao("testUserDao");
    ProductDao productDao = new ProductDao("testUserDao");
    User user;
   
    @Before
    public void setUp() {
        domainService = new DomainService(userDao, productDao);
        user = new User("","");
    }
    @Test
    public void constructorWorks() {
        assertEquals(userDao, userDao);
        assertEquals(productDao, productDao);
    }
    @Test
    public void checkIfUserExistsWorkd() {
        boolean test = domainService.checkIfuserExist(user.getUserName(), user.getPassword());
        assertFalse("", test);
        
        userDao.create(user);
        user = userDao.findTheUser(user.getUserName(), user.getPassword());
        test = domainService.checkIfuserExist(user.getUserName(), user.getPassword());
        assertEquals(test, test);
        
        user.setUserName(null);
        user.setPassword(null);
        
        test = domainService.checkIfuserExist(user.getUserName(), user.getPassword());
        
    }
  
}
