package it.polito.po.test;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Autostop.Autostop;
import Autostop.AutostopException;

public class TestR3 {
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
		
		as.bookTravel("lindarusso@gmail.com", "lucaverdi@gmail.com", "Torino");
		as.bookTravel("simonerossi@gmail.com", "marcorossi@gmail.com", "Milano");
		as.bookTravel("simonerossi@gmail.com", "giovannirusso@gmail.com", "Milano");
		
		as.travelDone("lindarusso@gmail.com");
		
		as.bookTravel("simonerossi@gmail.com", "paolabianchi@gmail.com", "Milano");
		as.bookTravel("simonerossi@gmail.com", "lucaverdi@gmail.com", "Milano");
		as.travelDone("simonerossi@gmail.com");
		
		as.bookTravel("marcoverdi@gmail.com", "marcorossi@gmail.com", "Roma");
		as.bookTravel("marcoverdi@gmail.com", "simonaneri@gmail.com", "Napoli");
		as.travelReady("marcoverdi@gmail.com");
		as.travelDone("marcoverdi@gmail.com");
		
		as.openTravelSession("simonerossi@gmail.com", "2023-08-26'T'12:30", "Milano", "Torino", "Cuneo");
		as.bookTravel("simonerossi@gmail.com", "marcoverdi@gmail.com", "Milano");
		as.bookTravel("simonerossi@gmail.com", "lucaverdi@gmail.com", "Milano");
		as.travelReady("simonerossi@gmail.com");
		as.travelDone("simonerossi@gmail.com");
		
		as.bookTravel("giovanniesposito@gmail.com", "andreafranchi@gmail.com", "Venezia");
		
		as.openTravelSession("lindarusso@gmail.com", "2023-09-26'T'12:30", "Torino", "Milano", "Venezia");
		as.bookTravel("lindarusso@gmail.com", "francescabianchi@gmail.com", "Torino");
		
	}
	@Test
	public void prova() {
		as.numTravelforPeople();
		as.travelByEmail("lindarusso@gmail.com");
		as.topDrivers();
	}
	
	@Test
	public void testNumTravelForPeopleAsUser() {
		List<String> list = as.numTravelforPeople();
		assertNotEquals("wrong ordering","lucaverdi@gmail.com",list.get(list.size()-1));
		assertEquals("wrong first place","lucaverdi@gmail.com",list.get(0));
		assertEquals("wrong seconde place","marcorossi@gmail.com",list.get(1));
		assertFalse("Only DONE travel, no open",list.contains("andreafranchi@gmail.com"));
		assertFalse("Only DONE travel, no ready", list.contains("franscescabianchi@gmail.com"));
	}
	
	
	@Test
	public void testTopDrivers() {
		//[simonerossi@gmail.com AA222EF 2:5, lindarusso@gmail.com DD789IJ 1:3, marcoverdi@gmail.com CC456GH 1:3]
		
		List<String> list = as.topDrivers();
		assertEquals("",3, list.size());
		assertTrue("Wrong string", list.contains("simonerossi@gmail.com AA222EF 2:5"));
		assertTrue("Wrong string", list.contains("lindarusso@gmail.com DD789IJ 1:3"));
		assertTrue("Wrong string", list.contains("marcoverdi@gmail.com CC456GH 1:3"));
	}
	
	@Test
	public void testTravelByEmail() {
		List<String> list = as.travelByEmail("marcoverdi@gmail.com");
		
		assertEquals(2, list.size());
	}
	
}
