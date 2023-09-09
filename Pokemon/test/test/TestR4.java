package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import Pokemon.Pokedex;
import Pokemon.Trainer;

public class TestR4 {

	Pokedex px;
	@Before
	public void setUp() {
		px = Pokedex.fromFile("pokedex.csv", "type.csv");
		px.addTrainer("Ash");
		px.addPokemon("Ash", 6);
		px.addPokemon("Ash", 3);
		px.addPokemon("Ash", 56, 144, 96, 11);
		px.addTrainer("Brock");
		px.addPokemon("Brock", 0,0,0,0,0,0,0,0);
		px.addTrainer("Misty");
		px.addPokemon("Misty", 1,1,1,1,1,1,1,1);
		px.addTrainer("Blu");
		px.addPokemon("Blu", 150,150,150,150,150,150,150);
	}
	
	@Test
	public void testGetMega() {
		List<String> lista = px.getMega();
		assertEquals("Size", 15, lista.size());
		assertTrue("", lista.stream().allMatch(p -> p.contains("Mega")));
	}
	@Test
	public void testGetMapType() {
		Map<String, List<String>> mappa = px.getMapType();
		assertEquals("Size", 18, mappa.size());
		assertTrue("",mappa.keySet().containsAll(px.getType().stream().map(p->p.getName()).collect(Collectors.toList())));
		List<String> lista = mappa.get("Fire");
		assertEquals(12, lista.size());
		assertTrue(lista.get(0).equals("Charmander Fire"));
		assertTrue(lista.get(11).equals("Moltres Fire Flying"));
		
		lista = mappa.get("Grass");
		assertEquals(14, lista.size());
		assertTrue(lista.get(0).equals("Tangela Grass"));
		assertTrue(lista.get(13).equals("Exeggutor Grass Psychic"));
	}
	
	
	@Test
	public void testTrainerByStat() {
		List<String> lista = px.trainerByStat().stream().map(p -> p.getName()).collect(Collectors.toList());
		assertEquals(4, lista.size());
		assertTrue(lista.get(0).equals("Brock"));
		assertTrue(lista.get(1).equals("Misty"));
		assertTrue(lista.get(2).equals("Ash"));
		assertTrue(lista.get(3).equals("Blu"));
		
	}
	
}
