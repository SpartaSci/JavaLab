Pomodoro
Progettare un sistema per la condivisione di appunti per Ingegneria 
tutte le altre classi salvate come mappe in ogni classe 
pomodoro tiene gli studenti e i corsi, corsi tengono gli argomenti e le informazioni come 
anno e classe, gli argomenti tengono le liste di contributi
La classe principale è Pomodoro
R1: studenti e corsi
Il metodo addStudent(matricola,nome,cognome) aggiunge un nuovo studente alla rete, lancia un’eccezione se la matricola è già presente;


A ogni corso ha associato: classe (informatica, aerospaziale, meccanica ecc); anno (1,2,3); nome e codice univoco, le stringhe sono salvate tutte in minuscolo, in modo da non avere ridondanze dovute al case sensitive
        
Il metodo AddCourse() chiede una classe, un anno, il nome, aggiunge il corso se non esiste già. Il codice è formato come segue: “Informatica” 1 “Basi di dati” → “Informatica1BasiDiDati”
Lancia un’eccezione se il codice risultante è già presente.


Il metodo getCourses() restituisce una lista di tutti i codici dei corsi
Il metodo getCourse() restituisce l’istanza della classe Corso corrispondente. Se non presente lancia un eccezione
R2 : argomenti e contributi
Per ogni corso, gli studenti potranno caricare appunti, riassunti e Quiz
Per organizzare correttamente gli argomenti è necessario un nome
I topic dei vari corsi vanno aggiunti rigorosamente tramite la classe Pomodoro, fornendo codici e nomi.


AddTopic() della classe pomodoro aggiunge l'argomento al corso, lancia un'eccezione se il corso non è presente.
Il metodo getTopic() della classe Course restituisce il topic.


Per ogni topic di ogni corso vengono caricati file e quiz. I file sono copiati nel sistema e  i nomi dei file se doppi, devono essere modificati aggiungendo (1), (2).. eccetera.
NB: Solo per i file dello stesso topic!  
NB: uno stesso file può essere aggiunto a più corsi
I metodi per aggiungere elementi al topic sono gestiti direttamente dalla classe topic        
AddFile()  aggiunge i file. 
getFilesName() restituisce la lista dei nomi dei file di un topic


AddQuiz() aggiunge una domanda con risposta breve.
I quiz non hanno codici, e possono ripetersi.
{ Consiglio: usare due classi separate, Classe per i File (titolo testo e voti), Classe per i quiz  (testo e risposta) }


R3: valutazioni
Gli altri studenti possono lasciare feedback sui vari contributi caricati, su e giù (ogni studente può dare solo un voto al contenuto)
I rating vengono aggiunti tramite la classe pomodoro fornendo il file (file effettivamente salvato) e il codice studente. i metodi sono ratingUp() e ratingDown() registrano i voti.


R4: risposte a flash card
I quiz possono essere ottenuti tramite il metodo getQuiz:
di Course, che restituisce tutti i quiz; 
di Topic che restituisce i quiz del topic.
di Pomodoro restituisce una lista di tutte le quiz registrati nel sistema.
a ogni getQuiz (eccetto Pomodoro) corrisponde anche il metodo answerQuiz() data una lista di risposte che restituisce il numero di risposte corrette
Il numero di risposte deve essere uguale al numero di domande, per una corrispondenza in base all’ordinamento;
R5 statistiche
topRated() mostra una lista ordinata in base al Rating dei 10 contenuti piu votati in tutto il sistema. Riporati come stringhe “rating:nomeFile|NomeCorso


allTopics() resistuisce una mappa a cui a ogni classe è associata una mappa dei suoi corsi, a cui a ogni corso è associata una mappa degli argomenti
<Informatica, <APA, List<topic>> 


 filesByStudents() restituisce una mappa in cui associa a tutti i gli studenti che hanno caricato file, una lista dei nomi dei file corrispondenti (anche ridondanti, in quanto l’unicità riguarda solo il topic)
