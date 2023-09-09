package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Olympics.Olympics;
import Olympics.Athlete;
import Sports.Corsa;
import Sports.Nuoto;
import Sports.Salto;

public class TestR2 {

Olympics ol;
	
	@Before
	public void setUp() throws Exception{
		ol = new Olympics();
		ol.addSport(new Salto("Salto in alto"));
		ol.addSport(new Corsa("Corsa 100 metri"));
		ol.addSport(new Nuoto("Nuoto 50 metri"));
	
		ol.addAthlete("Macio Capatonda", "ITA", "Salto in alto");
		ol.addAthlete("Corrado Guzzanti", "ITA", "Nuoto 50 metri");		
		ol.addAthlete("Paolo Maldini", "ITA", "Corsa 100 metri");
		ol.addAthlete("Alessandro DelPiero", "ITA", "Nuoto 50 metri");
		ol.addAthlete("Harry Kane", "ENG", "Corsa 400 metri");
		ol.addAthlete("Principe Harry", "ENG", "Salto in alto");
		ol.addAthlete("Adrien Rabiot", "FRA", "Corsa 100 metri");
		ol.addAthlete("Olivier Giroud", "FRA", "Salto in alto");
		
	}
	
	@Test
	public void testAddGetAthlete() {
		assertNotNull("Correct name",ol.getAhlete("ITA1"));
		assertNotNull("Correct number",ol.getAhlete("FRA2"));
		assertNull("Wrong code",ol.getAhlete("GER2"));
	}
	@Test 
	public void testAddAthleteNationalityFormat() {
		assertEquals("No capital letter", "", ol.addAthlete("Simone", "ita", "Salto in alto"));
		assertEquals("need 3 char", "", ol.addAthlete("Simone", "GERM", "Salto in alto"));
		
	}
	@Test
	public void testAddAthlete() {
		assertEquals("Wrong sport","",ol.addAthlete("Ciccio", "FRA", "Skateboard"));
		assertEquals("return right String", "ITA5", ol.addAthlete("Ciccio","ITA", "Salto in alto"));
	}
	
	@Test
	public void testGetListAthletes() {
		List<Athlete> lista = ol.getListAthletesBySport("Salto in alto");
		assertEquals("size",3, lista.size());
		assertEquals("toString", lista.get(0).toString(),lista.get(0).getId()+":"+lista.get(0).getName());
	}
	@Test
	public void testGetListAthletesByNat() {
		List<Athlete> lista = ol.getListAthletesByNationality("ITA");
		assertEquals("size",4, lista.size());
		
		
	}
	
}
