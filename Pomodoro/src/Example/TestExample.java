package Example;

import Pomodoro.Pomodoro;

import java.io.Serial;
import java.util.List;

import Pomodoro.Course;
import Pomodoro.File;
import Pomodoro.PomodoroException;
import Pomodoro.Topic;

public class TestExample {

	public static void main(String[] args) throws PomodoroException, CloneNotSupportedException {
	
		Pomodoro p = new Pomodoro();
		//R1 
		p.addStudent("AA11", "Simone", "Rossi");
		p.addStudent("BB11", "Giorno", "Giovanna");
		Course bsd = p.addCourse("InfOrmatica", "Basi di dati", 2);
		Course oop= p.addCourse("Informatica", "programmazione a Oggetti", 2);
		p.addCourse("Biomedica", "Chimica avanzata", 3);
		
		p.getCourses().forEach(c -> System.out.println(c));
		Course cav = p.getCourse("Biomedica3ChimicaAvanzata");
		// R2
		Topic stream_t = p.addTopic("Informatica2ProgrammazioneAOggetti", "Stream");
		p.addTopic("Informatica2ProgrammazioneAOggetti", "Liste");
		Topic liste_t = oop.getTopic("Liste");
		
		Topic cellule_t = p.addTopic("Biomedica3ChimicaAvanzata", "Cellule");
		Topic sql_t = p.addTopic("Informatica2BasiDiDati", "Sql");
		File file = new File("Appunti vari", "testo");
		
		stream_t.addFile(file,"AA11");
		stream_t.addFile(file,"AA11");
		stream_t.addFile(file,"BB11");
		liste_t.addFile(file,"BB11");
		
		stream_t.getFilesName();
		
		stream_t.addQuiz("il metodo negli stream per modificare gli elementi data una funzione?", "map");
		stream_t.addQuiz("1+1?", "2");
		sql_t.addQuiz("metodo per creare una tabella in sql?", "create");
		liste_t.addQuiz("Le liste necessitano una chiave?", "no");
		cellule_t.addQuiz("Quante cellule ci sono nel corpo?", "tante"); // :)
		// R3
		File fileStream = stream_t.getFile("Appunti vari(1)");
		p.ratingUp(fileStream, "AA11");
		p.ratingDown(fileStream, "AA11");
		p.ratingUp(liste_t.getFile("Appunti vari"), "BB11");
		
		// R4
		List<String> streamQuiz = stream_t.getQuiz();
		List<String> oopQuiz = oop.getQuiz();
		List<String> pomQuiz = p.getQuiz();
		
		int punti = stream_t.answerQuiz("map","2");
		int punti2 = oop.answerQuiz("no","map","2");
		
		//R5
		
		System.out.println(p.topRated());
		System.out.println(p.allTopics());
		System.out.println(p.filesByStudent());
	}

}
