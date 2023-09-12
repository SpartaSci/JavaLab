Gioco pokemon


L’inserimento di pokemon e tipi può avvenire soltanto all’inizializzazione, importanto i file “pokemon.csv” e “type.csv”, tramite il metodo fromFile(), sfruttando anche loadType() e loadPokemon()


R1 Tipi, forze e debolezze
Il file csv è organizzato con i tipi sulla prima colonna e riga
Leggendo per riga, il Tipo riga è efficace X attaccando il tipo in colonna.
Implementare il metodo privato loadType(), salvando tutti i tipi e le loro efficace;
getType() restituisce la lista di tipi.
Il metodo getProperties() dato un tipo, restituisce una mappa con due chiavi, attacco e difesa, a ogni chiave è associata un’altra mappa che mette in relazione la potenza (x2,x0.5,0) con la lista di tipi che la riguardano. x1 esclusi
Esempio: tipo volo
Defense={x0=[Ground], x0.5=[Grass, Fighting, Bug], x2=[Electric, Ice, Rock]} (subiscex2..)
Offense={x0=[], x0.5=[Rock, Electric, Steel], x2=[Fighting, Grass, Bug]}  (infliggex2...)


 Se viene fornito un tipo non corretto si restituisce una mappa vuota.


R2 Pokemon
caricare il pokedex tramite un file pokemon.csv, implementando il metodo privato loadPokemon(), organizzato come segue
num,nome,type1,type2;total,Hp;atk;def;sp.Atk;sp.Def;speed.
Il toString di un pokemon deve essere “nome” o “nome[mega]”
getListPokemon() restituisce la lista dei pokémon come stringhe
getListPokemon() con un attributo stringa restituisce tutti i pokemon con il tipo dato.
getListPokemonByStat() restituisce una lista ordinata data di una chiave. Le chiavi sono: Total,Hp,Atk,Def,Sp.Atk,Sp.Def,Speed (NB non si considerano le mega). La stringa dovrà essere “nome stat”.


R3 Allenatori
A ogni allenatore può essere assegnata una squadra di 6 pokémon 
newTrainer() aggiunge un nuovo allenatore,  i nomi sono univoci, i doppioni sovrascrivono il trainer corrente.
addPokemon() riceve un nome e un numero, aggiunge i corrispondenti pokemon. se il trainer non esiste o si raggiungono i 6  pokemon si fa ritorno.
addPokemon() può avere anche più numeri.
getPokemon() restituisce la lista dei pokemon nella squadra.
setMega() seleziona quale pokemon mega evolvere, con allenatore, indice in squadra, e versione della mega evoluzione (“”,”X”,”Y”) . Ci può essere solo una mega.
R4 filtri e mappe


getMega() restituisce la lista di tutte le mega evoluzioni
getMapType() restituisce una mappa di type, a cui associa a ogni tipo una lista di pokémon (String) contenente tutti i pokémon che hanno quel tipo, mettendo prima quelli con un solo tipo, poi quelli con due. Ordinati per ordine di pokedex. All’interno della mappa sarà normale trovare ridondanze.
trainerByStat() restituisce in ordine crescente
