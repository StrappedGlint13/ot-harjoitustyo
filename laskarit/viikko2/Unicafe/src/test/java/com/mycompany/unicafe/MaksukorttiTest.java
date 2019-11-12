package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;
    Kassapaate kassapaate;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10.0);
        kassapaate = new Kassapaate();
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
    }
    @Test
    public void rahaaEiVoiOttaaEnempaaKuinSitaOn() {
        kortti.otaRahaa(11.0);
        assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
    }

    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(2.0);
        assertEquals("Kortilla on rahaa 12.0 euroa", kortti.toString());
    }

    @Test
    public void onnistuukoOstaminenEdulliessti() {
        boolean ostosTapahtui = kassapaate.syoMaukkaasti(kortti);
        if (kortti.saldo() >= 400) {
            assertTrue(ostosTapahtui);
        } else {
            assertFalse(ostosTapahtui);
        }
    }
    
    @Test
    public void onnistuukoOstaminenMaukkaasti() {
        boolean ostosTapahtui = kassapaate.syoMaukkaasti(kortti);
        if (kortti.saldo() >= 400) {
            assertTrue(ostosTapahtui);
        } else {
            assertFalse(ostosTapahtui);
        }
    }
    

}
