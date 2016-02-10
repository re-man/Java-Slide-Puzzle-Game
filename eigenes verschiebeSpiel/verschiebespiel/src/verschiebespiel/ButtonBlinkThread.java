package verschiebespiel;

public class ButtonBlinkThread implements Runnable{
	
	private Spielfeld sf;
	private static int BLINK_TIME = 300;
	
	public ButtonBlinkThread(Spielfeld _sf){
		sf = _sf;
	}

	@Override
	public void run() {
		for (int i = 0; i < 3; i++){
			sf.bBlink(true);
			try {
				Thread.sleep(ButtonBlinkThread.BLINK_TIME);
			} catch (InterruptedException error) {
				sf.bBlink(false);
				break;
			}
			
			sf.bBlink(false);
			try {
				Thread.sleep(ButtonBlinkThread.BLINK_TIME);
			} catch (InterruptedException error) {
				sf.bBlink(false);
				break;
			}
		}
		sf.setBorder(null);
		
	}

}