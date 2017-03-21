package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener{

	private boolean[] arrowKeys;
	public boolean up, down, left, right;
	
	public KeyInput(){
		arrowKeys = new boolean[4];
	}
	
	public void moveKey(){
		up = arrowKeys[KeyEvent.VK_UP];
		down = arrowKeys[KeyEvent.VK_DOWN];
		left = arrowKeys[KeyEvent.VK_LEFT];
		right = arrowKeys[KeyEvent.VK_RIGHT];
	}
	@Override
	public void keyPressed(KeyEvent e) {
		arrowKeys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		arrowKeys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

}
