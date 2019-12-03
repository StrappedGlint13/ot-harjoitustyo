/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

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
public class ProductTest {
    
    Product product;
  
    @Before
    public void setUp() {
        product = new Product("Coffee", 1.8, 0.25, 1.2);
    }
    
    @Test
    public void constructorWorks() {
        assertEquals(product, product);
    }
}
