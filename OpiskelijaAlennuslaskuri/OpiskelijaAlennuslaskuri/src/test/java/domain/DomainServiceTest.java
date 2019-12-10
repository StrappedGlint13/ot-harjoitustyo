package domain;


import database.DBcoordinator;
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
    DBcoordinator dbcoordinator = new DBcoordinator("test");
    User user;
   
    @Before
    public void setUp() {
        domainService = new DomainService(dbcoordinator);
        user = new User("","");
    }
    @Test
    public void constructorWorks() {
        assertEquals(dbcoordinator, dbcoordinator);
    }
    @Test
    public void checkIfUserExistsWorkd() {
        boolean test = domainService.checkIfuserExist(user.getUserName(), user.getPassword());
        assertFalse("", test);
        
        dbcoordinator.createUser(user);
        user = dbcoordinator.findTheUser(user.getUserName(), user.getPassword());
        test = domainService.checkIfuserExist(user.getUserName(), user.getPassword());
        assertEquals(test, test);
        
        user.setUserName(null);
        user.setPassword(null);
        
        test = domainService.checkIfuserExist(user.getUserName(), user.getPassword());
        
    }
  
}
