package it.polito.po.test;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Autostop.*;
public class TestR1 {
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
		
	}
	@Test(expected = AutostopException.class)
	public void testAddDriversEmail() throws AutostopException {
		as.addDrivers("Simone Rossi", "simonerossi@gmail.com", 2, "CCCCCC");
	}
	@Test(expected = AutostopException.class)
	public void testAddDriversPlate() throws AutostopException {
		as.addDrivers("Simone Rossi", "simonerossi11@gmail.com", 2, "AA222EF");
	}
	@Test(expected = AutostopException.class)
	public void testAddUsersEmail() throws AutostopException {
		as.addUsers("Marco Rossi", "marcorossi@gmail.com");
	}
	
	@Test(expected = AutostopException.class)
	public void testEmail() throws AutostopException {
		as.addUsers("Simone Rossi", "simonerossi@gmail.com");
	}
	@Test(expected = AutostopException.class)
	public void testEmail2() throws AutostopException {
		as.addDrivers("Marco Rossi", "marcorossi@gmail.com",3,"SS234ER");
	}
	
	@Test
	public void testAddCar() throws AutostopException {
		as.addCar("marcorossi@gmail.com", 4, "CA232RR");
		assertTrue("Not added to drivers", as.getDrivers().contains("marcorossi@gmail.com"));
		try {
			as.addCar("ciccio@gmail.com", 3, "DD222EE");
			as.addCar("lucaverdi@gmail.com", 0, "AA345AD");
			fail();
		} catch (AutostopException e) {
			
		}
	}
	
	@Test 
	public void testGetDrivers() {
		List<String> drivers = as.getDrivers();
		assertEquals("6 drivers",6, drivers.size());
		assertTrue("Missing driver", drivers.contains("andreafranchi@gmail.com"));
		
	}
	
	
	
	
}
