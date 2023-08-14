package it.polito.po.test;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Autostop.Autostop;
import Autostop.AutostopException;

public class TestR2 {
	Autostop as;
	
	
	@Before
	public void setUp() throws AutostopException {
		as = new Autostop();
		
		as.addDrivers("Simone Rossi", "simonerossi@gmail.com", 4, "AA222EF");
		as.addDrivers("Francesca Bianchi", "francescabianchi@gmail.com", 2, "BB123CD");
		as.addDrivers("Marco Verdi", "marcoverdi@gmail.com", 3, "CC456GH");
		as.addDrivers("Linda Russo", "lindarusso@gmail.com", 1, "DD789IJ");
		as.addDrivers("Giovanni Esposito", "giovanniesposito@gmail.com", 5, "EE987KL");
		
		as.addUsers("Andrea Franchi", "andreafranchi@gmail.com");
		as.addUsers("Marco Rossi", "marcorossi@gmail.com");
		as.addUsers("Paola Bianchi", "paolabianchi@gmail.com");
		as.addUsers("Luca Verdi", "lucaverdi@gmail.com");
		as.addUsers("Simona Neri", "simonaneri@gmail.com");
		as.addUsers("Giovanni Russo", "giovannirusso@gmail.com");
		
		as.addCar("andreafranchi@gmail.com", 4, "AA345AD");
		
		as.openTravelSession("giovanniesposito@gmail.com", "2023-07-26'T'12:30", "Venezia", "Rimini");
		as.openTravelSession("lindarusso@gmail.com", "2023-07-26'T'12:30", "Torino", "Milano", "Venezia");
		as.openTravelSession("marcoverdi@gmail.com", "2023-07-26'T'12:30", "Roma", "Napoli", "Bari");
		as.openTravelSession("simonerossi@gmail.com", "2023-07-26'T'12:30", "Milano", "Genova");
		
		try {
			as.bookTravel("simonerossi@gmail.com", "marcorossi@gmail.com", "Milano");
			as.bookTravel("simonerossi@gmail.com", "giovannirusso@gmail.com", "Milano");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	@Test(expected = AutostopException.class)
	public void testOpenTravel_driver() throws AutostopException {
		as.openTravelSession("marcorossi@gmail.com", null, null); //non driver
	}
	
	@Test(expected = AutostopException.class)
	public void testOpenTravel_alreadyOpen	() throws AutostopException {
		as.openTravelSession("simonerossi@gmail.com", null, null); // sessione già aperta
	}
	
	@Test
	public void testGetTravel() {
		List<String> travels = as.getTravel();
		assertEquals(4,travels.size());
		assertTrue("", travels.contains("giovanniesposito@gmail.com - 5 - Venezia - Rimini"));
		assertTrue("", travels.contains("lindarusso@gmail.com - 1 - Torino - Milano - Venezia"));
		assertTrue("", travels.contains("marcoverdi@gmail.com - 3 - Roma - Napoli - Bari"));
		assertTrue("", travels.contains("simonerossi@gmail.com - 2 - Milano - Genova"));
		
		try{
			as.travelReady("simonerossi@gmail.com");
		} catch (Exception e) {
			// TODO: handle exception
		}
		travels = as.getTravel();
		assertFalse("only OPEN", travels.contains("simonerossi@gmail.com - 2 - Milano - Genova"));
	}
	
	@Test
	public void testBookTravel() throws AutostopException {
		
		try {
			as.bookTravel("lindarusso@gmail.com", "lucaverdi@gmail.com", "Torino");
			as.bookTravel("lindarusso@gmail.com", "simonaneri@gmail.com", "Torino"); //deve esserci un eccezione, non ha piu posti
			fail();
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	@Test 
	public void testGetUserByTravel() throws AutostopException {
		
		try {
			List<String> u = as.getUsersByTravel("simonerossi@gmail.com");
			assertTrue("",u.contains("marcorossi@gmail.com"));
			assertTrue("",u.contains("giovannirusso@gmail.com"));
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	@Test
	public void testTravelReady() throws AutostopException {
		as.bookTravel("lindarusso@gmail.com", "lucaverdi@gmail.com", "Torino");
		try {
			as.travelReady("lindarusso@gmail.com");
			fail(); //eccezione perché già ready
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	@Test
	public void testTravelDone() throws AutostopException {
		as.bookTravel("lindarusso@gmail.com", "lucaverdi@gmail.com", "Torino");
		try {
			as.travelDone("lindarusso@gmail.com"); // passa da ready a done
			as.travelDone("lindarusso@gmail.com"); // errore da done a done
			fail(); //eccezione perché già done
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
}
