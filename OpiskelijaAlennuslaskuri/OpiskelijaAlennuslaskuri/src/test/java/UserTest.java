/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import domain.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    
    User jaakko = new User("Jaakko", "jaakko@helsinki.fi", "jotain", "jotain");
    
    
    public UserTest() {
        
    }
    @Test
    public void addingSameUser() {
        User jaakko = new User("Jaakko", "jaakko@helsinki.fi","jotain", "jotain");
        assertFalse(jaakko.equals(this.jaakko));
    }
   
}
