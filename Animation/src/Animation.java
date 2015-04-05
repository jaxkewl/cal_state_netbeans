
import java.awt.Graphics;

import javax.swing.JApplet;

public class Animation extends JApplet implements Runnable {

	int x = 0;

	@Override
	public void init() {
		// TODO Auto-generated method stub
		super.init();

                
		x = 0;
		Thread theThread = new Thread(this);
		theThread.start();
                
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (x < this.getWidth()) {

			repaint();
			try {
				Thread.sleep(200);
				x += 5;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	public void paint(Graphics g) {
		
		//super just clears the screen
		super.paint(g);
		g.fillRect(x, 50, 50, 70);
	}

}
