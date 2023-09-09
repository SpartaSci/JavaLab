package it.polito.po.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Pomodoro.Course;
import Pomodoro.File;
import Pomodoro.Pomodoro;
import Pomodoro.PomodoroException;
import Pomodoro.Topic;

public class TestR2 {
	Pomodoro p;
	Course oop, bsd, chi;
	
	
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
	}
	@Test(expected = PomodoroException.class)
	public void testAddTopic() throws PomodoroException{
		p.addTopic("Aerospaziale", "Fisica");
	}
	@Test(expected = PomodoroException.class)
	public void testAddDoubleTopic() throws PomodoroException{
		p.addTopic("Informatica2ProgrammazioneAOggetti", "stream");
	}
	@Test(expected = PomodoroException.class)
	public void testGetWrongTopic() throws PomodoroException{
		oop.getTopic("Non presente");
	}
	
	@Test
	public void testGetTopics() {
		List<String> lista = oop.getTopics();
		assertTrue("",lista.contains("stream"));
		assertFalse("",lista.contains("sql"));
	}
	
	@Test
	public void testAddFile() throws PomodoroException{
		File f = new File("Appunti vari","testo");
		Topic stream_t = oop.getTopic("stream");
		Topic liste_t = oop.getTopic("liste");
		assertTrue("", stream_t.addFile(f, "AA11").equals("Appunti vari"));
		assertTrue("", stream_t.addFile(f, "AA11").equals("Appunti vari(1)"));
		assertTrue("", stream_t.addFile(f, "AA11").equals("Appunti vari(2)"));
		assertFalse("", stream_t.addFile(f, "BB11").equals("Appunti vari"));
		assertTrue("", liste_t.addFile(f, "AA11").equals("Appunti vari"));
	}

	@Test(expected = PomodoroException.class)
	public void testAddFileStudent() throws PomodoroException{
		File f = new File("Appunti vari","testo");
		Topic stream_t = oop.getTopic("stream");
		stream_t.addFile(f, "FF22");
	}
	@Test(expected = PomodoroException.class)
	public void testGetFile() throws PomodoroException{
		File f = new File("Appunti vari","testo");
		Topic stream_t = oop.getTopic("stream");
		stream_t.addFile(f, "AA11");
		stream_t.getFile("liste");
	}
	@Test
	public void testAddFileInstance() throws PomodoroException{
		File f = new File("Appunti vari","testo");
		Topic stream_t = oop.getTopic("stream");
		Topic liste_t = oop.getTopic("liste");
		stream_t.addFile(f, "AA11");
		liste_t.addFile(f, "AA11");
		File f1 = stream_t.getFile("Appunti vari");
		File f2 = liste_t.getFile("Appunti vari");
		assertEquals(f1.getName(), f1.getName());
		assertFalse(f1==f2);
	}
	@Test
	public void testGetFilesName() throws PomodoroException{
		File f = new File("Appunti vari","testo");
		Topic stream_t = oop.getTopic("stream");
		Topic liste_t = oop.getTopic("liste");
		stream_t.addFile(f, "AA11");
		liste_t.addFile(f, "AA11");
		assertEquals("",1,stream_t.getFilesName().size());
	}
	@Test
	public void testaddQuiz() throws PomodoroException {
		File f = new File("Appunti vari","testo");
		Topic stream_t = oop.getTopic("stream");
		Topic liste_t = oop.getTopic("liste");
		stream_t.addQuiz("il metodo negli stream per modificare gli elementi data una funzione?", "map");
		stream_t.addQuiz("1+1?", "2");
		stream_t.addQuiz("1+1?", "2");
		liste_t.addQuiz("Le liste necessitano una chiave?", "no");
		assertEquals("",3,stream_t.getQuiz().size());
	}
	
	
	
}
