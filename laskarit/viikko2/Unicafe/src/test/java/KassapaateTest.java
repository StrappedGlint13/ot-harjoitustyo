/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.unicafe.Kassapaate;
import com.mycompany.unicafe.Maksukortti;
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
    Maksukortti kortti;

    public KassapaateTest() {
    }

    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }

    @Test
    public void maksukortinSaldoAlussaOikein() {
        assertEquals("kassapäätteellä on rahaa 100000 euroa", kassapaate.toString());
    }

    @Test
    public void kassapaatteenSaldoAlussaOikein() {
        assertEquals("Kortilla on rahaa 1000.0 euroa", kortti.toString());
    }

    @Test
    public void lounaidenMaaraOnNollaAlussa() {
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void edullistenLounaidenostaminenToimiiOikein() {
        kassapaate.syoEdullisesti(500);
        assertEquals(100240, kassapaate.kassassaRahaa());
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void edullistenLounaidenOstaminenLiianPienellaSummalla() {
        kassapaate.syoEdullisesti(50);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }

    @Test
    public void maukkaidenLounaidenOstaminenToimiiOikein() {
        kassapaate.syoMaukkaasti(500);
        assertEquals(100400, kassapaate.kassassaRahaa());
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void maukkaidenLounaidenOstaminenLiianPienellaSummalla() {
        kassapaate.syoMaukkaasti(200);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());

    }

    @Test
    public void kortillaOnRahaaEdullistenLounaidenMaaraKasvaa() {
        boolean ostosTapahtui = kassapaate.syoEdullisesti(kortti);
        if (kortti.saldo() >= 240) {
            assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
        } else {
            assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
        }

    }

    @Test
    public void kortillaOstaminenToimiiEdullisiinLounaisiin() {
        boolean ostosTapahtui = kassapaate.syoEdullisesti(kortti);
        if (kortti.saldo() >= 240) {
            assertTrue(ostosTapahtui);
        } else {
            assertFalse(ostosTapahtui);
        }

    }

    @Test
    public void kortillaOstaminenMeneeNegatiiviseksi() {
        boolean ostosTapahtui = kassapaate.syoEdullisesti(kortti);
        if (kortti.saldo() < 240) {
            assertFalse(ostosTapahtui);
        }

    }

    @Test
    public void kortillaOstaminenToimiiMaukkaisiinLounaisiin() {
        boolean ostosTapahtui = kassapaate.syoMaukkaasti(kortti);
        if (kortti.saldo() >= 400) {
            assertTrue(ostosTapahtui);
        } else {
            assertFalse(ostosTapahtui);
        }
    }

    public void kortillaOnRahaaMaukkaidenLounaidenMaaraKasvaa() {
        boolean ostosTapahtui = kassapaate.syoMaukkaasti(kortti);
        if (kortti.saldo() >= 400) {
            assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
        } else {
            assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
        }
    }

    @Test
    public void kassassaOlevaRahamaaraEiMuutuKortillaOstettaessa() {
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }

    @Test
    public void kortilleRahaaLadattaessaKortinSaldoMuuttuu() {
        kassapaate.lataaRahaaKortille(kortti, 1000);
        int saldo = (int) kortti.saldo();
        assertEquals(101000, kassapaate.kassassaRahaa());
        assertEquals(2000, saldo);
    }

}
