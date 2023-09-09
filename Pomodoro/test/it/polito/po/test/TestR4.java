package it.polito.po.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Pomodoro.Course;
import Pomodoro.File;
import Pomodoro.Pomodoro;
import Pomodoro.PomodoroException;
import Pomodoro.Topic;

public class TestR4 {
	
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
		
		stream_t.addQuiz("il metodo negli stream per modificare gli elementi data una funzione?", "map");
		stream_t.addQuiz("1+1?", "2");
		liste_t.addQuiz("Le liste necessitano una chiave?", "no");
		cellule_t.addQuiz("Quante cellule ci sono nel corpo?", "tante");
		
	}
	
	
	@Test 
	public void testCourseGetQuiz() {
		List<String> oopQuiz = oop.getQuiz();
		assertEquals("",3, oopQuiz.size());
		assertTrue("Stream quiz 1", oopQuiz.contains("il metodo negli stream per modificare gli elementi data una funzione?"));
		assertTrue("Stream quiz 2",oopQuiz.contains("1+1?"));
		assertTrue("Liste quiz",oopQuiz.contains("Le liste necessitano una chiave?"));
	}
	@Test 
	public void testPomoGetQuiz() {
		List<String> pomQuiz = p.getQuiz();
		assertEquals("",4, pomQuiz.size());
		assertTrue("",pomQuiz.contains("Quante cellule ci sono nel corpo?"));
	}
	@Test 
	public void testTopicGetQuiz() {
		List<String> streamQuiz = stream_t.getQuiz();
		assertEquals("",2, streamQuiz.size());
	}
	
	@Test (expected = PomodoroException.class)
	public void testTopicAnswerQuizException() throws PomodoroException {
		stream_t.answerQuiz("map");
	}
	@Test (expected = PomodoroException.class)
	public void testCourseAnswerQuizException() throws PomodoroException {
		oop.answerQuiz("map");
	}
	
	
	@Test 
	public void testTopicAnswerQuiz() throws PomodoroException{
		int punti = stream_t.answerQuiz("map","2");
		assertEquals("Right answer", 2 ,punti);
		assertEquals("Wrong answer",1, stream_t.answerQuiz("map","1"));
	}
	
	@Test 
	public void testCourseAnswerQuiz() throws PomodoroException{
		List<String> quiz = oop.getQuiz();
		int punti = oop.answerQuiz("no","map","2");
		assertEquals("Right answer", 3 ,punti);
		assertEquals("Wrong answer",1, oop.answerQuiz("bho","map","1"));
	}
	
}
