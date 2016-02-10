package verschiebespiel;

import java.awt.*;
import javax.swing.*;
import de.fhwgt.verschiebespiel.*;

@SuppressWarnings("serial")
public class Spielfeld extends JPanel {

	private logik logik;
	private Hauptfenster hf;
	private Anzeige anzeige;
	private Color bgColor = null;
	private Color fgColor = Color.BLACK;
	private Thread blinkThread;
	private ButtonBlinkThread buttonBlink;
	private ListenerButton listenerButton;
	private ListenerMaus listenerMaus;
	private JButton[] bausteine = new JButton[9];
	private JLabel leeresLabel = new JLabel();
	private Dimension buttonSize = new Dimension(70,70);
	private Integer[] nummern;
	private Icon iconLeeresLabel;
	
	public Spielfeld(Hauptfenster _hf) {
		hf = _hf;
		logik = hf.getLogik();
		buttonBlink = new ButtonBlinkThread(this);
		blinkThread = new Thread(buttonBlink);
		listenerMaus = new ListenerMaus(this);
	}
	
	public void zeichneSF() {
		listenerButton = new ListenerButton();
		this.removeAll();
		
		this.setLayout(new GridLayout(3,3));
		nummern = logik.getNummern();
		 
		for (int i = 0; i < 9; i++) {
			if (nummern[i] == null) {
				leeresLabel.setVisible(true);
				leeresLabel.setOpaque(true);
				leeresLabel.addMouseListener(listenerMaus);
				this.add(leeresLabel);
			}
			else {
				bausteine[i] = new JButton("" + nummern[i]);
				bausteine[i].addActionListener(listenerButton);
				bausteine[i].setActionCommand("" + nummern[i]);
				bausteine[i].addMouseListener(listenerMaus);
				
				bausteine[i].setFont(new Font("Arial", Font.BOLD, 16));
				bausteine[i].setBackground(bgColor);
				bausteine[i].setForeground(fgColor);
				bausteine[i].setOpaque(true);
				bausteine[i].setPreferredSize(buttonSize);
				bausteine[i].setVisible(true);
				
				this.add(bausteine[i]);
			}
		}
		revalidate();
		
	}
	
	void startBlinkThread() {
		if (blinkThread.isAlive() == false){
			blinkThread = new Thread(buttonBlink);
			blinkThread.start();
		}
	}
	
	public void bBlink(Boolean blink){
		if (blink){
			this.setBorder(BorderFactory.createLineBorder(Color.RED, 2, true));
		}
		else {
			this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
		}
	}
	
	public logik getLogik(){
		return logik;
	}
	
	public ListenerMaus getMausListener(){
		return listenerMaus;
	}
	
	public void setColors(Color _bgColor, Color _fgColor){
		anzeige = hf.getAnzeige();
		bgColor = _bgColor;
		fgColor = _fgColor;
		anzeige.setButtonColor(_bgColor, _fgColor);
	}
	
	public void setIconLeeresLabel(ImageIcon _icon){
		iconLeeresLabel = _icon;
		leeresLabel.setIcon(iconLeeresLabel);
	}

}
