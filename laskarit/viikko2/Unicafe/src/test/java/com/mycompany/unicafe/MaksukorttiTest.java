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
    public void saldoAluksiOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void kortinMetodiSaldoPalauttaaOikeanMaaran() {
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void kortilleVoiLisataRahaa() {
        kortti.lataaRahaa(100);
        assertEquals("saldo: 1.10", kortti.toString());
    }
    
    @Test
    public void kortiltaVoiVahentaaRahaa() {
        kortti.otaRahaa(5);
        assertEquals("saldo: 0.05", kortti.toString());
    }
    
    @Test
    public void kortiltaEiVoiOttaaSaldoaEnempaaRahaa() {
        kortti.otaRahaa(11);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void kortiltaOnnistunutOttoPalauttaaTrue() {
        assertTrue(kortti.otaRahaa(10));
    }
    
    @Test
    public void kortiltaEpaonnistunutOttoPalauttaaFalse() {
        assertFalse(kortti.otaRahaa(11));
    }
}
