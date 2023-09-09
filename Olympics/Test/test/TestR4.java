package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import Olympics.Olympics;
import Sports.Corsa;
import Sports.Nuoto;
import Sports.Salto;

public class TestR4 {

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

		ol.addResult("Salto in alto", "ITA1", "200");
		ol.addResult("Nuoto 50 metri", "ITA2", "12.2"); 
		ol.addResult("Corsa 100 metri", "ITA3", "9.99");
		ol.addResult("Nuoto 50 metri", "ITA4", "10.01");
		ol.addResult("Corsa 400 metri", "ENG1", "54.02");
		ol.addResult("Salto in alto", "ENG2", "185");
		ol.addResult("Corsa 100 metri", "FRA1", "13.02");
		ol.addResult("Salto in alto", "FRA2", "190");
	}
	
	
	
	@Test
	public void testBestResult() {
		List<String> lista = ol.bestResult("Salto in alto");
		assertEquals("Correct size",3, lista.size());
		assertTrue("Correct Order", lista.get(0).equals("ITA1:200"));
		assertTrue("Correct order", lista.get(2).equals("ENG2:185"));
		
		lista = ol.bestResult("Corsa 100 metri");
		assertEquals("Correct size",2, lista.size());
		assertTrue("Correct Order", lista.get(0).equals("ITA3:9.99"));
		assertTrue("Correct order", lista.get(1).equals("FRA1:13.02"));
		
		lista = ol.bestResult("Nuoto 50 metri");
		assertEquals("Correct size",2, lista.size());
		assertTrue("Correct Order", lista.get(0).equals("ITA4:10.01"));
		assertTrue("Correct order", lista.get(1).equals("ITA2:12.2"));
		
	}
	
	@Test
	public void testMappaNazionali() {
		Map<String, Map<String,List<String>>> mappa = ol.mappaNazionali_sport_atleti();
		assertEquals("size",3, mappa.size());
		assertTrue("Eng", mappa.containsKey("ENG"));
		assertTrue("Ita", mappa.containsKey("ITA"));
		assertTrue("Fra", mappa.containsKey("FRA"));
		
		assertEquals("Ita sport size",3,mappa.get("ITA").size());
		assertEquals("Fra sport size",2,mappa.get("FRA").size());
		assertEquals("Eng sport size",2,mappa.get("ENG").size());
		
		assertEquals("Ita corsa size",1,mappa.get("ITA").get("Corsa 100 metri").size());
	}
	
	
	@Test
	public void testResultByNazionali() {
		List<String> lista = ol.resultByNazionality();
		
		assertEquals("Size",8,lista.size());
		assertEquals("Order","ENG1 Corsa 400 metri 54.02",lista.get(0));
		assertEquals("Order","ITA4 Nuoto 50 metri 10.01",lista.get(7));
	}
	
	@Test 
	public void testAvgNation() throws Exception {
		
		ol.addAthlete("Gino", "FRA", "Salto in alto");
		ol.addAthlete("Paolo", "FRA", "Salto in alto");
		ol.addAthlete("Aldo", "ENG", "Salto in alto");
		ol.addAthlete("Giacomo", "ITA", "Salto in alto");
		ol.addResult("Salto in alto", "FRA3", "211");
		ol.addResult("Salto in alto", "FRA4", "170");
		ol.addResult("Salto in alto", "ENG3", "198");
		ol.addResult("Salto in alto", "ITA5", "232");
		Map<String, Double> mappa = ol.avgNationBySport("Salto in alto");
		
		assertTrue("Code",mappa.containsKey("ENG"));
		assertEquals("correct avg", (Object)191.5, mappa.get("ENG"));
		assertEquals("correct avg", (Object)216.0,mappa.get("ITA"));
		assertEquals("correct avg", (Object)190.33, mappa.get("FRA"));
		
		
	}
}




