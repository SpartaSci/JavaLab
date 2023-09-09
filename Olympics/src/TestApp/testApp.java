package TestApp;
import java.util.List;

import Olympics.*;
import Sports.*;

public class testApp {
	
	public static void main(String[] args) throws Exception{
		
		Olympics ol = new Olympics();
		
		//R1 
		ol.addSport(new Salto("Salto in alto"));
		ol.addSport(new Corsa("Corsa 100 metri"));
		ol.addSport(new Nuoto("Nuoto 50 metri"));
		ol.addSport(new Corsa("Corsa 400 metri"));
		System.out.println(ol.getListofSport());
		
		
		//R2
		ol.addAthlete("Macio Capatonda", "ITA", "Salto in alto");
		ol.addAthlete("Corrado Guzzanti", "ITA", "Nuoto 50 metri");		
		ol.addAthlete("Paolo Maldini", "ITA", "Corsa 100 metri");
		ol.addAthlete("Alessandro DelPiero", "ITA", "Nuoto 50 metri");
		
		ol.addAthlete("Harry Kane", "ENG", "Corsa 400 metri");
		ol.addAthlete("Principe Harry", "ENG", "Salto in alto");
		ol.addAthlete("Adrien Rabiot", "FRA", "Corsa 100 metri");
		ol.addAthlete("Olivier Giroud", "FRA", "Salto in alto");
		
		System.out.println(ol.getListAthletesByNationality("ITA"));
		System.out.println(ol.getListAthletesBySport("Salto in alto"));
		
		
		ol.addResult("Salto in alto", "ITA1", "200");
		ol.addResult("Nuoto 50 metri", "ITA2", "12.2"); 
		ol.addResult("Corsa 100 metri", "ITA3", "9.99");
		ol.addResult("Nuoto 50 metri", "ITA4", "10.01");
		ol.addResult("Corsa 400 metri", "ENG1", "54.02");
		ol.addResult("Salto in alto", "ENG2", "185");
		ol.addResult("Corsa 100 metri", "FRA1", "13.02");
		ol.addResult("Salto in alto", "FRA2", "190");
		

		ol.bestResult("Corsa 100 metri");
		ol.bestResult("Salto in alto");
		ol.mappaNazionali_sport_atleti();
		
		ol.resultByNazionality();
		
		
		ol.avgNationBySport("Salto in alto");
		ol.avgNationBySport("Nuoto 50 metri");
	}
}
