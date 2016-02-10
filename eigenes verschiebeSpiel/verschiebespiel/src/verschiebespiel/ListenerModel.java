package verschiebespiel;

import javax.swing.*;
import de.fhwgt.verschiebespiel.*;

public class ListenerModel implements VerschiebeListener {

	private Hauptfenster hf;
	private Spielfeld sf;
	private Anzeige anzeige;
	private logik logik;
	private int status;
	private int auswahl;
	private ListenerMaus listenerMaus;
	private String selected;
	private int limit;
	
	public ListenerModel(Spielfeld _sf) {
		sf = _sf;
		hf = (Hauptfenster)_sf.getTopLevelAncestor();
		logik = hf.getLogik();
		listenerMaus = sf.getMausListener();
	}
	
	@Override
	public void GridChanged(VerschiebeEvent event) {
		
		selected = listenerMaus.getSelected();
		
		if (selected == "leicht"){
			limit = 300;
		}
		else if (selected == "normal"){
			limit = 200;
		}
		else if (selected == "schwer"){
			limit = 100;
		}
		else if (selected == "standard"){
			limit = 0;
		}
		
		if (limit != 0) {
			
			if (logik.getZaehler() == limit){
				auswahl = JOptionPane.showConfirmDialog(sf, "Züge-Limit erreicht, leider verloren! Erneut spielen?", "Spielende", JOptionPane.YES_NO_OPTION);
				if(auswahl == JOptionPane.YES_OPTION)
				{
					hf.neuesSpielStarten();
				}
				else if(auswahl == JOptionPane.NO_OPTION)
				{
					System.exit(3);
				}
			}
		}
		
	}

	@Override
	public void StatusChanged(VerschiebeEvent event) {
		status = event.getStatus();
		
		if (status == -1){
			sf.startBlinkThread();
		}
		
		if (status == 1){
			
			listenerMaus = (ListenerMaus) sf.getMausListener();
			
			auswahl = JOptionPane.showConfirmDialog(sf, "Neues Spiel?", "Hurra Gewonnen!", JOptionPane.YES_NO_OPTION);			
			
			if(auswahl == JOptionPane.YES_OPTION)
			{
				hf.dispose();
				anzeige = (Anzeige) hf.getAnzeige();
				anzeige.dispose();
				new Hauptfenster();
			}
			else if(auswahl == JOptionPane.NO_OPTION)
			{
				System.exit(4);
			}
		}
		
	}

}
