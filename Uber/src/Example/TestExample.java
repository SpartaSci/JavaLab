package Example;

import Autostop.*;
import static org.junit.Assert.*;

import java.util.Calendar.*;
import java.util.List;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class TestExample {

	public static void main(String[] args) throws AutostopException {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm");
		
		
		Autostop as = new Autostop();
		
		as.addDrivers("Simone Rossi", "simonerossi@gmail.com", 4, "AA222EF");
		as.addDrivers("Francesca Bianchi", "francescabianchi@gmail.com", 2, "BB123CD");
		as.addDrivers("Marco Verdi", "marcoverdi@gmail.com", 3, "CC456GH");
		as.addDrivers("Linda Russo", "lindarusso@gmail.com", 1, "DD789IJ");
		as.addDrivers("Giovanni Esposito", "giovanniesposito@gmail.com", 5, "EE987KL");
		try {
			as.addDrivers("Sara Monti", "saramonti@gmail.com", 2, "DD789IJ");
			fail("Duplicated plate not detected");
		} catch(Exception e) {}
		
		as.addUsers("Andrea Franchi", "andreafranchi@gmail.com");
		as.addUsers("Marco Rossi", "marcorossi@gmail.com");
		as.addUsers("Paola Bianchi", "paolabianchi@gmail.com");
		as.addUsers("Luca Verdi", "lucaverdi@gmail.com");
		as.addUsers("Simona Neri", "simonaneri@gmail.com");
		as.addUsers("Giovanni Russo", "giovannirusso@gmail.com");
		
		as.addCar("andreafranchi@gmail.com", 4, "AA345AD");
		
		// R2 Viaggi
		
		LocalDateTime loc = LocalDateTime.of(2023, 6, 15, 10, 0);
		String data = loc.format(formatter);
		as.openTravelSession("simonerossi@gmail.com", data, "Torino", "Milano", "Venezia");
		as.openTravelSession("lindarusso@gmail.com", data, "Roma", "Napoli", "Bari");
		as.openTravelSession("giovanniesposito@gmail.com", "2023-07-26'T'12:30", "Venezia", "Rimini");
		
		
		List<String> travels = as.getTravel();		
		
		as.bookTravel("simonerossi@gmail.com", "andreafranchi@gmail.com", "Milano");
		as.bookTravel("simonerossi@gmail.com", "marcorossi@gmail.com", "Torino");
		as.bookTravel("simonerossi@gmail.com", "lucaverdi@gmail.com", "Torino");
		
		//as.bookTravel("simonerossi@gmail.com", "simonaneri@gmail.com", "Venezia");
		as.bookTravel("simonerossi@gmail.com", "lindarusso@gmail.com", "Torino");


		
		List<String> travelpeople = as.numTravelforPeople();
		List<String> topDrivers = as.topDrivers();
		
		

	}

}
