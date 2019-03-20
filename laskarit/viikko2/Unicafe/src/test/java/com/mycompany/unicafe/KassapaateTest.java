/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vikke
 */
public class KassapaateTest {
    Kassapaate kassapaate;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    
    @Test
    public void kassapaatteenAlustusOikein() {
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiKunTasaraha() {
        kassapaate.syoEdullisesti(240);
        assertEquals(100240, kassapaate.kassassaRahaa());
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiKunTasaraha() {
        kassapaate.syoMaukkaasti(400);
        assertEquals(100400, kassapaate.kassassaRahaa());
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiKunRiittavastiRahaa() {
        assertEquals(60, kassapaate.syoEdullisesti(300));
        assertEquals(100240, kassapaate.kassassaRahaa());
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiKunRiittavastiRahaa() {
        assertEquals(100, kassapaate.syoMaukkaasti(500));
        assertEquals(100400, kassapaate.kassassaRahaa());
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiEpaonnistuuKunRahaaEiRiittavasti() {
        assertEquals(239, kassapaate.syoEdullisesti(239));
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiEpaonnistuuKunRahaaEiRiittavasti() {
        assertEquals(399, kassapaate.syoMaukkaasti(399));
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void syoEdullisestiKunKortillaRahaa() {
        assertTrue(kassapaate.syoEdullisesti(kortti));
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiKunKortillaRahaa() {
        assertTrue(kassapaate.syoMaukkaasti(kortti));
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiEpaonnistuuKunKortillaEiRahaaRiittavasti() {
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        assertFalse(kassapaate.syoEdullisesti(kortti));
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiEpaonnistuuKunKortillaEiRahaaRiittavasti() {
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        assertFalse(kassapaate.syoMaukkaasti(kortti));
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(2, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void lataaRahaaKortille() {
        kassapaate.lataaRahaaKortille(kortti, 20);
        assertEquals(1020, kortti.saldo());
        assertEquals(100020, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kortilleEiVoiLadataNegatiivistaArvoa() {
        kassapaate.lataaRahaaKortille(kortti, -20);
        assertEquals(1000, kortti.saldo());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
}
