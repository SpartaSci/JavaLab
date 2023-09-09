package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Pokemon.Pokedex;

public class TestR2 {
	Pokedex px;
	@Before
	public void setUp() {
		px = Pokedex.fromFile("pokedex.csv", "type.csv");
	}
	@Test
	public void testGetListPokemon() {
		List<String> lista = px.getListPokemon();
		assertEquals("size", 151, lista.size());
		assertFalse("No mega on differen line", lista.size()>151);
		assertTrue("Simple Mega", lista.contains("Venusaur[Mega Venusaur]"));
		assertTrue("Double Mega", lista.contains("Charizard[Mega Charizard X, Mega Charizard Y]"));
	}
	
	@Test
	public void testGetListPokemonType() {
		List<String> lista = px.getListPokemon("Fire");
		assertEquals("size", 12, lista.size());
		assertFalse("", lista.contains("Venusaur[Mega Venusaur]"));
		assertTrue("", lista.contains("Charizard[Mega Charizard X, Mega Charizard Y]"));
	}

	@Test
	public void testGetListPokemonStat() {
		List<String> lista = px.getListPokemonByStat("Atk");
		assertEquals("size", 151, lista.size());
		assertEquals("first", "Dragonite 134", lista.get(0));
		lista = px.getListPokemonByStat("Hp");
		assertEquals("size", 151, lista.size());
		assertEquals("first", "Chansey 250", lista.get(0));
		lista = px.getListPokemonByStat("Speed");
		assertEquals("size", 151, lista.size());
		assertEquals("first", "Electrode 140", lista.get(0));
		lista = px.getListPokemonByStat("Total");
		assertEquals("size", 151, lista.size());
		assertEquals("first", "Mewtwo 680", lista.get(0));
		lista = px.getListPokemonByStat("Sp.Def");
		assertEquals("size", 151, lista.size());
		assertEquals("first", "Articuno 125", lista.get(0));
	}
}
