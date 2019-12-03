package domain;

import domain.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    
    User jaakko = new User("Jaakko", "jaakko@helsinki.fi", "123456", "password");
    
    @Test
    public void addingSameUser() {
        User jaakko = new User("Jaakko", "jaakko@helsinki.fi","123456", "password");
        assertFalse(jaakko.equals(this.jaakko));
    }
    @Test
    public void getNameTest() {
        String name = jaakko.getUserName();
        assertTrue(jaakko.getUserName().equals(name));
    }
    @Test
    public void getEmailTest() {
        String name = jaakko.getEmail();
        assertTrue(jaakko.getEmail().equals(name));
    }
    @Test
    public void getPasswordTest() {
        String password = jaakko.getPassword();
        assertTrue(jaakko.getPassword().equals(password));
    }
    @Test
    public void getStudentNumberTest() {
        String studentnumber = jaakko.getStudentNumber();
        assertTrue(jaakko.getStudentNumber().equals(studentnumber));
    }
   
}
