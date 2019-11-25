/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import StudentDiscountCalculator.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    
    User jaakko = new User("Jaakko", "jaakko@helsinki.fi", 123456, "jotain");
    
    
    public UserTest() {
        
    }
    @Test
    public void addingSameUser() {
        User jaakko = new User("Jaakko", "jaakko@helsinki.fi",123456, "jotain");
        assertTrue(jaakko.equals(this.jaakko));
    }
   
}
