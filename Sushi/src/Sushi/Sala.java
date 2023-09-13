package Sushi;

public class Sala extends Thread{

	private AllYouCanEat sushi;
	//private Random rand = new Random();
	private boolean aperto = true;
	
	private Ritardo ritardo = new Ritardo();
	private int n = ritardo.getValore();
	private Pulsanti pulsante;
	
	public Sala(AllYouCanEat sushi) {
		this.sushi = sushi;
		pulsante = new Pulsanti(ritardo,"Sala");
		sushi.pannello.add(pulsante);
	}
	
	@Override
	public void run() {
		while(aperto) {
			servingOrder();
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
	protected void servingOrder() {
		Ordine o = sushi.getOrderForServing();
		if(o==null)return;
		sushi.orderCompleted(o);
	}
}
