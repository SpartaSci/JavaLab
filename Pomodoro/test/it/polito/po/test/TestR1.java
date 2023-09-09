package it.polito.po.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Pomodoro.Course;
import Pomodoro.Pomodoro;
import Pomodoro.PomodoroException;

public class TestR1 {
	
	Pomodoro p;
	
	@Before
	public void setUp() throws PomodoroException {
		p = new Pomodoro();
		
		p.addStudent("AA11", "Simone", "Rossi");
		p.addStudent("BB11", "Giorno", "Giovanna");
		p.addStudent("CC11", "Davide", "Fo");
		p.addCourse("InfOrmatica", "Basi di dati", 2);
		p.addCourse("Informatica", "programmazione a Oggetti", 2);
		p.addCourse("Biomedica", "Chimica avanzata", 3);
	}
	
	@Test(expected=PomodoroException.class) 
	public void testAddDuplicateStudent() throws PomodoroException {
		p.addStudent("AA11", "Rosa", "Lia");
	}
	@Test(expected = PomodoroException.class)
	public void testAddDuplicatedCourse() throws PomodoroException {
		p.addCourse("Informatica", "Programmazione a oggetti", 2);
	}
	@Test
	public void testGetCourses() {
		List<String> lista = p.getCourses();
		assertEquals("", 3, lista.size());
		assertTrue("",lista.contains("Informatica2BasiDiDati"));
		assertTrue("",lista.contains("Biomedica3ChimicaAvanzata"));
		assertTrue("",lista.contains("Informatica2ProgrammazioneAOggetti"));
		
	}
	
	
	@Test(expected = PomodoroException.class)
	public void testGetCourse() throws PomodoroException{
		p.getCourse("CorsoCheNonPresente");
	}
	
	
}
