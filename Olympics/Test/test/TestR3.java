package test;

import org.junit.Before;
import org.junit.Test;

import Olympics.Olympics;
import Sports.Corsa;
import Sports.Nuoto;
import Sports.Salto;

public class TestR3 {
	Olympics ol;
	
	@Before
	public void setUp() throws Exception{
		ol = new Olympics();
		ol.addSport(new Salto("Salto in alto"));
		ol.addSport(new Corsa("Corsa 100 metri"));
		ol.addSport(new Nuoto("Nuoto 50 metri"));
		ol.addSport(new Corsa("Corsa 400 metri"));
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
	public void testAddResult() throws Exception{
		ol.addResult("Salto in alto", "ITA1", "200");
		ol.addResult("Nuoto 50 metri", "ITA2", "12.2"); 
		ol.addResult("Corsa 100 metri", "ITA3", "9.99");
		ol.addResult("Nuoto 50 metri", "ITA4", "10.01");
		ol.addResult("Corsa 400 metri", "ENG1", "54.02");
		ol.addResult("Salto in alto", "ENG2", "185");
		ol.addResult("Corsa 100 metri", "FRA1", "13.02");
		ol.addResult("Salto in alto", "FRA2", "190");
	}
	
	@Test (expected = Exception.class)
	public void testAddResult1() throws Exception{
		ol.addResult("Calcio", "ITA1", "200"); //sport
	}
	@Test (expected = Exception.class)
	public void testAddResult2() throws Exception{
		ol.addResult("Salto in alto", "GER2", "200"); // atleta
	}
	@Test (expected = Exception.class)
	public void testAddResult3() throws Exception{
		ol.addResult("Salto in alto", "ITA1", "20:55"); //formato
	}
	@Test (expected = Exception.class)
	public void testAddResult4() throws Exception{
		ol.addResult("Salto in alto", "ITA2", "203"); // atleta e sport sbagliati
	}
	
}
