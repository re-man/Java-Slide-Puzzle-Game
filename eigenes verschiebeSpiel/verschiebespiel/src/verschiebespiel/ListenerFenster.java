package verschiebespiel;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class ListenerFenster implements ComponentListener {
	
	private Hauptfenster hf;
	private Anzeige anzeige;

	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentMoved(ComponentEvent event) {
		hf = (Hauptfenster)event.getSource();
		anzeige = hf.getAnzeige();
		anzeige.updateLocation(hf);

	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

}
