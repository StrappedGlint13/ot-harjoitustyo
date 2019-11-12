/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.unicafe.Kassapaate;
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
public class KassapaateTest {
    
    Kassapaate kassapaate; 
    
    public KassapaateTest() {
    }
    
    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
    }
    @Test
    public void kassapaatteenSaldoAlussaOikein() {
        assertEquals("kassapäätteellä on rahaa 100000 euroa", kassapaate.toString());
    }
    @Test
    public void lounaidenMaaraOnNollaAlussa() {
        assertEquals(0, kassapaate.getEdulliset());
        assertEquals(0, kassapaate.getMaukkaat());
    }
    

}
