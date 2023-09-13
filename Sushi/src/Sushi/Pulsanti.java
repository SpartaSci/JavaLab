package Sushi;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Pulsanti extends JPanel{

	private static final long serialVersionUID = 1L;
	public JButton piu = new JButton("+");
	public JButton meno = new JButton("-");
	public JLabel valore = new JLabel("",SwingConstants.CENTER);
	private Ritardo ritardo;
	private String name;
	public Pulsanti(Ritardo ritardo, String name) {
	//	this.setTitle(name);
		this.name = name;
		//this.setSize(400,400);
		this.setLayout(new BorderLayout());
		this.add(piu,BorderLayout.NORTH);
		this.add(valore,BorderLayout.CENTER);
		this.add(meno,BorderLayout.SOUTH);
//		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//this.setVisible(true);
		this.ritardo = ritardo;
		this.update();
		piu.addActionListener(e -> this.eseguiIncremento());
		meno.addActionListener(e -> this.eseguiDecremento());
	}
	public void update() {
		int v = ritardo.getValore();
		this.valore.setText(name + ": " + Integer.toString(v));
	}
	public void eseguiIncremento() {
		this.ritardo.increment();
		this.update();
	}	
	public void eseguiDecremento() {
		if(ritardo.getValore()>=2) {
			this.ritardo.decrement();
			this.update();
		}
	}
	
}
