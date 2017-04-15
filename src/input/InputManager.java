package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class InputManager implements KeyListener{
	
	private Set<IActionListener> actionListeners = new HashSet<IActionListener>();
	
	public InputManager(){}
	
	private void performAction(int keycode, int flag){
		
		if(InputAction.INPUT_MAP.containsKey(keycode)){
			
			InputAction action = InputAction.INPUT_MAP.get(keycode);
			for(IActionListener listener : actionListeners){
				
				if(!listener.isActive())
					continue;
				
				listener.onAction(action, flag);
			}
		}
	}
	
	public void addActionListener(IActionListener listener){
		actionListeners.add(listener);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		performAction(e.getKeyCode(), 0);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		performAction(e.getKeyCode(), 1);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
}
