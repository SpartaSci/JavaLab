OLIMPICS


implementare un sistema che registri alle olimpiadi gli atleti
La classe principale è Olympics.


R1 Sport                         
abstract class → salto, corsa, nuoto
Occorre registrare gli sport coinvolti per queste olimpiadi, ogni sport ha le proprie caratteristiche e gestisce in modo differente i risultati.
Gli sport sono aggiunti tramite la classe addSport(Sport sport), le classi sport vengono create tramite le estensioni (salto corsa nuoto), le quali a loro volta possono avere nomi diversi (100 metri, 200 metri eccetera). I nomi dati ai vari sport devono contenere i nomi della classe stessa. (new Salto(“Salto in alto”), new nuoto(“Nuoto 100 metri”), altrimenti il costruttore deve lanciare una Exception()
La classe astratta Sport richiede l’implementazione dei metodi checkResult() per controllare se la stringa rispetta il formato di quello sport:
Corsa → “dd,dd”
Nuoto → “dd,dd”
Salto → “d”
addResult per aggiungere i risultati; comparatorResult() che restituisce un Comparator() adatto a comparare i risultati.


R2 atleti (nazionalità)
ogni atleta può essere registrato a un solo sport, un nuovo atleta può essere aggiunto tramite addAthlete, che riceve come argomento nazionalità nome cognome e specialità, restituisce il codice univoco formato come tag nazionalità + numero atleta per nazionalità
Se lo sport non è presente restituisce una stringa vuota.
La nazionalita deve essere un codice di 3 lettere, in maiuscolo. Altrimenti restituisce una stringa vuota.
Il metodo getAthletsByNat restituisce la lista di atleti data la nazionalità.
Il metodo getAthletsBySport restituisce la lista di atleti dato lo sport.
Il metodo getAthlete() restituisce l’atleta dato un codice, ritorna null se non presente.
toString per visualizzare gli atleti “codice:nome”

R3 Gare e risultati
I risultati sono gestiti sotto forma di stringa, con formato diverso in base allo sport.
Corsa → “dd,dd”
Nuoto → “dd,dd”
Salto → “d”
Il metodo addResult() registra il risultato per un atleta, eventualmente sovrascrivendo quello presente, se il risultato non è conforme, o lo sport non appartiene all'atleta lancia un’eccezione Exception().

R4 
bestResult() ricevuto uno sport restituisce la lista di “codiceAtlet:risultato” ordinata in base al risultato. Se lo sport non è presente ritorna null;
resultByNazionality() restituisce la lista di “codicAtleta sport risultato" ordinata alfabeticamente per il codice.
mappa_nazionali() restiisce una map<String, Map<String, List<String>>> raggruppata prima per nazionalità, poi per sport, associando a ogni sport una lista dei nomi degli atleti.


avgNationBySport() dato uno sport restituisce il risultato medio per ogni nazionale.
 arrotondando alla seconda cifra decimale.
