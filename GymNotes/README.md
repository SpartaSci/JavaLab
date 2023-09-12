GYM NOTES
Una palestra fornisce ai propri clienti un’app per tracciare i propri progressi
La classe principale è GymNotes, le eccezioni sono lanciate tramite GymNotesException().

R1: Utenti
Si registrano al piano nuovi utenti tramite il metodo newClient(), fornendo i dati personali come nome cognome email e il sesso. Oopzionalmente, le informazioni su peso, altezza e sesso, tramite il metodo personalData(), lancia un eccezione se il genere non è M o F e se la email non esiste. 
L’utente che ha fornito i propri dati personali può decidere se accettare l’utilizzo dei propri dati e progressi per tracciare informazioni generali sulla palestra. metodo dataSharing(). eccezione se la email non esiste e se il cliente non ha prima accettato personalData().
(personalData è fine a questo requisito, dataSharing servirà dopo)

R2: Esercizi
Il sistema fornisce già degli esercizi standard tra i quali scegliere, salvati su un file exercise.csv <nome>,<attrezzi>, <pesoC>, <tipo>, dove tipo indica se è un esercizio di pesistica(spinte manubri), o basato sul tempo (tapirulan), e se è basato anche sul peso corporeo 
Il file viene caricato dal costruttore di GymNotes() invocando loadFile()..
Un utente può aggiungere degli esercizi personalizzati tramite il metodo addPersonalExercise() fornendo gli stessi dati. Questi esercizi non vengono considerati in nessun caso dal sistema di tracciamento generale.
getAllExercise() riceve un'email e restituisce tutti gli esercizi a disposizione del cliente, quelli di default più le sue aggiunte.
getBodyWeight() resistuisce una lista di esercizi in cui  utilizza il proprio peso corporeo
lo stesso metodo ma con una email come attributo considera anche gli esercizi personali.
getExercisebyTools() restituisce una lista di esercizi in cui è previsto l’utilizzo di un certo attrezzo, con email considera anche esercizi personali

R3: Schede e allenamenti
Ogni utente può organizzare 6 schede di allenamento, aggiungendo esercizi a piacimento. L’ordine degli esercizi è rilevante. le schede sono gestite come classe Routine.
Le rep degli esercizi sono passati come stringhe, esempio “3x10” o “2x60” (anche per gli esercizi a tempo). 
Il metodo addRoutine() fornendo un utente, crea una scheda (eccezione se nome scheda doppio), che può essere riempita tramite il metodo addExerciseToRoutine(), fornendo email, routine, esercizio e set.
Il metodo getRoutine() restituisce come stringa la routine dato il nome e la email.
Il formato stringa deve essere 
“NomeEsercizio:5x5rep
NomeEsercizio:3x60sec
eccetera”
Parallelamente si tiene traccia degli allenamenti, usando una scheda, e un lista di numeri a indicare il peso utilizzato, o il numero di minuti 
addWorkout apre una effettiva sessione di allenamento (solo una aperta alla volta) fornendo email e la routine su cui basarsi. endWorkout completa l’ultima sessione di workout registrata, fornendo email, e le effettive rep come lista .
Le effReps sono fornite nel dettaglio delle serie con all’ inizio il peso utilizzato, seguito dalla rep, 5x5 usando 30 kg diventa "30 5 5 5 5 5”, oppure “30 5 5 5 4 3”, per indicare che all’ultima serie sono state fatte 3/4 rep. (NB il numero di set deve comunque essere coerente con i set in routine). Gli esercizi a tempo (min) non hanno il peso
endWorkout() lancia eccezioni se non è presente la email, se non ha workout, se l’ultimo workout non è aperto, se i set non sono validi.

R4: progressi
getProgression() dato un utente e un esercizio, fornisce una lista con tutte le occorrenze di un esercizi negli allenamenti di un utete, come kg rep rep rep … (
avgMaxTon() dato un esercizio, calcola la media dei tonnellaggi massimi degli utenti
maxMinutes() fornisce una lista ordinata degli utenti in base alla massima durata di un esercizio, formattato cosi: “Nomeutente max”
















Panca piana, bilanciere, false, rep
Panca 30°, bilanciere, false, rep
Panca piana, manubri, false, rep
Panca 30°, manubri, false, rep
Dip, parallele, true, rep
Pull up, rack, true, rep
Corsa, tapis roulant, false, min
Cyclette, cyclette, false, min
Salto della corda, corda, false, min
