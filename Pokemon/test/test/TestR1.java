package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import Pokemon.Pokedex;
import Pokemon.Type;

public class TestR1 {

	Pokedex px;
	@Before
	public void setUp() {
		px = Pokedex.fromFile("pokedex.csv", "type.csv");
	}
	
	@Test
	public void testLoadAndGetType() {
		List<Type> lista = px.getType();
		assertEquals("size",18,lista.size());
	}
	@Test
	public void testGetProperties() {
		Map<String, Map<String, List<String>>> mappa = px.getProperties("Flying");
		
		assertEquals("size",2, mappa.size());
		assertTrue("Offense", mappa.containsKey("Offense"));
		assertTrue("Defense", mappa.containsKey("Defense"));
		
		assertEquals("size", 3,mappa.get("Offense").size());
		assertTrue("eff", mappa.get("Offense").containsKey("x2"));
		assertTrue("eff", mappa.get("Offense").containsKey("x0.5"));
		assertTrue("eff", mappa.get("Offense").containsKey("x0"));
		
		assertEquals("size", 3,mappa.get("Defense").size());
		assertTrue("eff", mappa.get("Defense").containsKey("x2"));
		assertTrue("eff", mappa.get("Defense").containsKey("x0.5"));
		assertTrue("eff", mappa.get("Defense").containsKey("x0"));
		
		assertTrue("", mappa.get("Offense").get("x0.5").contains("Steel"));
		
		assertFalse("no x1", mappa.values().stream().flatMap( m -> m.values().stream()).flatMap( m -> m.stream()).anyMatch(p -> p.equals("Fire")));
		
		assertEquals("{Defense={x0=[Ground], x0.5=[Grass, Fighting, Bug], x2=[Electric, Ice, Rock]}, Offense={x0=[], x0.5=[Rock, Electric, Steel], x2=[Fighting, Grass, Bug]}}",mappa.toString());
	}
	
	
	
	
	
}
