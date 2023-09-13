package Sushi;


public class Cucina extends Thread{

	private AllYouCanEat sushi;
	private boolean aperto = true;
	//private Random rand = new Random();
	private Ritardo ritardo = new Ritardo();
	private int n = ritardo.getValore();
	private Pulsanti pulsante;

	
	public Cucina(AllYouCanEat sushi) {
		this.sushi = sushi;
		pulsante = new Pulsanti(ritardo, "Cucina");
		sushi.pannello.add(pulsante);
	}
	
	@Override
	public void run() {
		while(aperto) {
			prepOrder();
			try {
				n = ritardo.getValore();
				Thread.sleep(n*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void close() {
		this.aperto = false;
	}
	protected void prepOrder() {
		Ordine o = sushi.getOrderForKitchen();
		if(o==null) return;
		sushi.orderReadyForServing(o);
	}

}
