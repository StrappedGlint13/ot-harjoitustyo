package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
    }

    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(2);
        assertEquals("Kortilla on rahaa 12.0 euroa", kortti.toString());
    }
    @Test
    public void ottaminenToimiiOikein() {
    kortti.lataaRahaa(300);
        Kassapaate kassapaate = new Kassapaate();
        int kortillaRahaa = kortti.saldo();
        int edullisiaMyyty = kassapaate.edullisiaLounaitaMyyty();
        boolean ostosTapahtui = kassapaate.syoEdullisesti(kortti);
        
        assertTrue(ostosTapahtui);
        assertTrue(kortillaRahaa == 0);  
        assertTrue(edullisiaMyyty == 1);
    }
}
