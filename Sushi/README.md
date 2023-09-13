Sushi

Esercitazione guidata per creare un sistema che gestisca gli ordini dei vari tavoli.
Scopo dell’esercitazione lavorare con i threads. Si identificano 3 classi da rendere thread/Runnable: Tavoli, Cucina, Sala.
La classe Principale è AllYouCanEat, che chiede il numero di tavoli.
R1-R4 per creare le classi, dal R5 le faremo funzionare insieme.

R1 Tavoli

Nel locale ci sono N tavoli singoli, N dato durante l’inizializzazione di AllYouCanEat.
Per ogni nuovo gruppo crea un nuovo tavolo tramite newTable() di AllYouCanEat fornendo il numero di coperti.
Ogni due coperti si assegna un tavolo in più. Quindi il metodo assegna tot tavoli al gruppo tra quelli disponibili, e restituisce un'istanza Table.
Il metodo assegna a ogni gruppo un numero identificativo (tra i numeri dei tavoli scelti).
addSushi() riceve un numero e incrementa di uno la quantità di quel numero;
SendOrder() invia alla cucina l’ordine corrente (vedere R2);

R2 Ordini

I piatti sono numerati da 1 a 50
I piatti vengono aggiunti all'ordine, una volta mandato in cucina, i successivi piatti faranno parte di un nuovo ordine. Un ordine può avere un massimo di piatti, pari a 2xNumCoperti.
La classe Ordini deve gestire i seguenti metodi:
addSushi() riceve un numero e incrementa di uno la quantità di quel numero;
getCurrentOrder() restituisce la lista di piatti nell’ordine, come "<Quantità>x<NPiatto>"
toString() deve ritornare una stringa “Tavolo #:<Q>x<N>;<Q>x<N>;......” 

R3 Cucina e sala

Gli ordini arrivano alla cucina, i piatti passeranno da uno status di "in preparazione" a uno di "pronto", una volta consegnato al tavolo passa a "Completato".
(Consiglio: Per distinguerli, non usare gli ENUM ma creare tre distinte liste, tornerà utile dopo)
I piatti vengono preparati secondo l'ordine di arrivo dell'ordinazione.
La classe Kitchen deve gestire gli ordini in arrivo dai tavoli, e mandarli in sala.
La classe Sala deve gestire gli ordini in arrivo dalla cucina e mandarli ai tavoli (status completati).
Cucina → metodo prepOrder() che prende il primo ordine in lista dai tavoli e lo manda alla lista per la sala.
Sala → metodo servingOrder() che prende il primo ordine dalla lista sala e lo manda nei completati.

R4 AllYouCanEat

Per agevolare i metodi precedenti e gestire i casi di possibile errore (come lista vuota) implementare i metodi:
sendOrderToKitchen() usata da Table
getOrderForKitchen() e orderReadyForServing() usati da Cucina in prepOrder()
getOrderForServing() e orderCompleted() usati da Sala in servingOrder().

I metodi:
stringOrderKitchen()
stringOrderServing()
stringOrderCompleted()
Ritornano come stringhe le liste degli ordini:
“Order for kitchen
toString()
toString()
toString()”
con toString formattato come chiesto in precedenza.

Per gli ordini completati mostrare solo gli ultimi 10 aggiunti.

R5 Thread

Rendere le classi (no order) estensioni di thread o implementazioni di Runnable.
Per le classi AllYouCanEat, Tavolo, Sala, Cucina creare il metodo run().
AllYouCanEat run() deve lanciare i thread di sala e cucina.
Ogni tot secondi (Thread.sleep()) deve stampare la situazione degli ordini.
Tavolo run() viene lanciato in newTable(). Ogni tot secondi manda un ordine, per automatizzare anche addSushi() fare in modo che vengano aggiunti piatti Randomicamente (Random) rispettando comunque i limiti imposti sul Numero MAX di piatti per ordine.
Sala e Cucina devono periodicamente lanciare i rispettivi servingOrder() e prepOrder(). 

(Rendere anche i delay randomici per avere un flusso meno statico).

Sincronizzare l’accesso alle liste.

R6 Pulsanti

Per rendere modificabili manualmente i ritardi sulla catena degli ordini creare una classe JPanel che preveda due bottoni + e - e un JLabel per il valore. 
Programmare i bottoni in modo che aggiornino il ritardo a cui fanno riferimento tramite degli addActionListener().
Ogni thread deve avere il suo bottone.
Per rendere più compatti e facili da usare i pannelli aggiungerli a un JFrame comune istanziato nella classe AllYouCanEat. 
