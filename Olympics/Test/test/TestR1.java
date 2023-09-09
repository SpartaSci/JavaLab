package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Olympics.Olympics;
import Olympics.Sport;
import Sports.Corsa;
import Sports.Nuoto;
import Sports.Salto;

public class TestR1 {

	Olympics ol;
	
	@Before
	public void setUp() throws Exception{
		ol = new Olympics();
		ol.addSport(new Salto("Salto in alto"));
		ol.addSport(new Corsa("Corsa 100 metri"));
		ol.addSport(new Nuoto("Nuoto 50 metri"));
	}
	
	@Test (expected = Exception.class)
	public void testAddSport() throws Exception{
		ol.addSport(new Salto("Nuoto 100 metri"));
	}
	@Test
	public void testAddSportCapital() throws Exception{
		new Salto("Triplo salto"); // deve accettare tutte le possibili forme, SALTO,Salto,salto,ecc
	}
	@Test
	public void testGetSports() throws Exception{
		
		List<String> lista = ol.getListofSport();
		assertTrue("Salto",lista.contains("Salto in alto"));
		assertEquals("size",3,lista.size());
		
	}
	
	@Test
	public void testSport() throws Exception{
		Sport s = new Salto("Salto in lungo");
		assertNotNull(s.comparatorResults());
		assertTrue(s.checkResult("200"));		
		
	}
}
