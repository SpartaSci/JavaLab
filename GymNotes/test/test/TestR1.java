package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import GymNotes.GymNotes;
import GymNotes.GymNotesException;

public class TestR1 {
	GymNotes g;

	@Before
	public void loadData() throws GymNotesException{
		g = new GymNotes("exercise.csv");
		g.newClient("Simone", "Poli", "sim@gmail.com");
		g.newClient("Andrea", "Bob", "and@gmail.com");
		g.newClient("Franco", "Mollica", "fra@gmail.com");
		g.newClient("Giorno", "Pescheria", "gio@gmail.com");
	}
	
	@Test(expected = GymNotesException.class)
	public void testNewClientDuplicated() throws GymNotesException{
		g.newClient("Simone", "Fiffi", "sim@gmail.com");
	}
	@Test(expected = GymNotesException.class)
	public void testPersonalDataNoEmail() throws GymNotesException{
		g.personalData("giov@gmail.com", 88, 178, "M");
	}
	@Test(expected = GymNotesException.class)
	public void testPersonalDataGender() throws GymNotesException{
		g.personalData("sim@gmail.com", 88, 178, "R");
	}
	@Test(expected = GymNotesException.class)
	public void testDataSharingNeedPersonalData() throws GymNotesException{
		g.dataSharing("sim@gmail.com");
	}
	@Test(expected = GymNotesException.class)
	public void testDataSharingNoEmail() throws GymNotesException{
		g.dataSharing("mazz@gmail.com");
	}
	
	
	
}
