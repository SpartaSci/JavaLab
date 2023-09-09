package it.polito.po.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import Pomodoro.Course;
import Pomodoro.File;
import Pomodoro.Pomodoro;
import Pomodoro.PomodoroException;
import Pomodoro.Topic;

public class TestR3 {
	
	Pomodoro p;
	Course oop, bsd, chi;
	Topic stream_t, liste_t;
	
	@Before
	public void setUp() throws PomodoroException {
		p = new Pomodoro();
		
		p.addStudent("AA11", "Simone", "Rossi");
		p.addStudent("BB11", "Giorno", "Giovanna");
		p.addStudent("CC11", "Davide", "Fo");
		bsd = p.addCourse("InfOrmatica", "Basi di dati", 2);
		oop = p.addCourse("Informatica", "programmazione a Oggetti", 2);
		chi = p.addCourse("Biomedica", "Chimica avanzata", 3);
		
		p.addTopic("Informatica2ProgrammazioneAOggetti", "Stream");
		p.addTopic("Informatica2ProgrammazioneAOggetti", "Liste");
		p.addTopic("Biomedica3ChimicaAvanzata", "Cellule");
		p.addTopic("Informatica2BasiDiDati", "Sql");
		
		File file = new File("Appunti vari","testo");
		stream_t = oop.getTopic("stream");
		liste_t = oop.getTopic("liste");
		stream_t.addFile(file, "AA11");
		liste_t.addFile(file, "AA11");
	}
	
	@Test (expected = PomodoroException.class) 
	public void testStudentRatingDown() throws PomodoroException{
		File f = stream_t.getFile("Appunti vari");
		p.ratingDown(f, "FF21");
	}
	@Test (expected = PomodoroException.class) 
	public void testStudentRatingUp() throws PomodoroException{
		File f = stream_t.getFile("Appunti vari");
		p.ratingUp(f, "FF21");
	}
	@Test
	public void testRatingUp() throws PomodoroException{
		File f = stream_t.getFile("Appunti vari");
		p.ratingUp(f, "AA11");
		p.ratingUp(f, "BB11");
		assertEquals("Normal rating",2,f.getRating());
		p.ratingUp(f, "AA11");
		assertEquals("Rating again",2, f.getRating());
	}
	@Test
	public void testRatingDown() throws PomodoroException{
		File f = stream_t.getFile("Appunti vari");
		p.ratingDown(f, "AA11");
		p.ratingDown(f, "BB11");
		assertEquals("Normal rating",-2,f.getRating());
		p.ratingDown(f, "AA11");
		assertEquals("Rating again",-2, f.getRating());
	}
	
	@Test
	public void testRating() throws PomodoroException{
		File f = stream_t.getFile("Appunti vari");
		p.ratingUp(f, "AA11");
		p.ratingUp(f, "BB11");
		p.ratingDown(f, "AA11");
		assertEquals("Change rating", 0, f.getRating());
	}
	
}
