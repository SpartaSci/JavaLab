package it.polito.po.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import Pomodoro.Course;
import Pomodoro.File;
import Pomodoro.Pomodoro;
import Pomodoro.PomodoroException;
import Pomodoro.Topic;

public class TestR5 {

	Pomodoro p;
	Course oop, bsd, chi;
	Topic stream_t, liste_t, cellule_t;
	
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
		
		stream_t = oop.getTopic("stream");
		liste_t = oop.getTopic("liste");
		cellule_t = chi.getTopic("cellule");
		
		File file = new File("Appunti vari","testo");
		stream_t.addFile(file, "AA11");
		stream_t.addFile(file, "BB11");
		stream_t.addFile(file, "CC11");
		cellule_t.addFile(file, "AA11");
		liste_t.addFile(file, "AA11");
	
		File s1 = stream_t.getFile("Appunti vari");
		File s2 = stream_t.getFile("Appunti vari(1)");
		
		File c1 = cellule_t.getFile("Appunti vari");
		p.ratingUp(s1, "AA11");
		p.ratingDown(s2, "AA11");
		p.ratingUp(s1, "BB11");
		p.ratingDown(c1, "BB11");
		p.ratingDown(c1, "CC11");
	}
	
	@Test
	public void testTopRated() {
		List<String> lista = p.topRated();
		assertEquals("size",5,lista.size());
		assertEquals("correct order", "2:Appunti vari|programmazione a oggetti" ,lista.get(0));
		assertEquals("correct","-2:Appunti vari|chimica avanzata",lista.get(4));
	}
	@Test
	public void testTopRatedSize() throws PomodoroException{
		File f = new File("Riassunto", "testo");
		stream_t.addFile(f, "AA11");
		stream_t.addFile(f, "AA11");
		stream_t.addFile(f, "AA11");
		stream_t.addFile(f, "AA11");
		stream_t.addFile(f, "AA11");
		stream_t.addFile(f, "AA11");
		List<String> lista = p.topRated();
		assertEquals("size",10,lista.size());
	}
	@Test
	public void testAllTopics() {
		Map<String,Map<String,List<String>>> mappa = p.allTopics();
		
		assertTrue("",mappa.containsKey("biomedica"));
		assertTrue("",mappa.containsKey("informatica"));
		assertTrue("",mappa.get("informatica").containsKey("basi di dati"));
		assertTrue("",mappa.get("informatica").get("basi di dati").contains("sql"));
	}
	
	@Test 
	public void testFileByStudent() {
		Map<String,List<String>> mappa = p.filesByStudent();
		assertTrue("", mappa.containsKey("AA11"));
		assertEquals("size",3, mappa.size());
		assertEquals("size list",3,mappa.get("AA11").size());
		assertTrue("All named Appunti vari",mappa.get("AA11").stream().allMatch(p -> p.equals("Appunti vari")));
	}
}
