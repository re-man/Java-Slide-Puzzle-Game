package verschiebespiel;

import java.awt.*;
import javax.swing.*;
import de.fhwgt.verschiebespiel.*;

@SuppressWarnings("serial")
public class Hauptfenster extends JFrame {

	// Objekt für Spiele-Logik erstellen
	private logik logik = new logik();
	// Spielfeld erstellen
	private Spielfeld sf = new Spielfeld(this);
	// ListenerModel erstellen
	private ListenerModel listenerModel;
	// Navigation erstellen
	private Anzeige anzeige;
	// MenuBar erstellen
	private MenuBar menuBar;
	
	
	
	public Hauptfenster () {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Verschiebespiel");
		this.setLocationRelativeTo(null);
		
		this.getContentPane();
		
		sf.zeichneSF();
		
		menuBar = new MenuBar(this);
		
		this.setJMenuBar(menuBar);
		this.add(sf, BorderLayout.CENTER);
		
		listenerModel = new ListenerModel(sf);
		logik.addVerschiebeListener(listenerModel);
		
		pack();
		
		this.setLayout(new BorderLayout());
		this.setVisible(true);
		this.setResizable(false);	
		
		anzeige = new Anzeige(this);
		
		this.addComponentListener(new ListenerFenster());
	}

	
	public logik getLogik(){
		return logik;
	}
	
	public Spielfeld getSF(){
		return sf;
	}
	
	public Anzeige getAnzeige(){
		return anzeige;
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException error) {
			// TODO Auto-generated catch block
			error.printStackTrace();
		}
		new Hauptfenster();
	}
	
	public void neuesSpielStarten() {
		this.dispose();
		this.anzeige.dispose();	
		new Hauptfenster();	
	}
}