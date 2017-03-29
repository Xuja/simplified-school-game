package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class InputManager implements KeyListener{

	private final int[] keys;
	
	private Set<IActionListener> actionListeners = new HashSet<IActionListener>();
	
	public InputManager(){
		keys = new int[InputAction.size()];
	}
	
	/**
	 * Initializes the keys based on the enum InputAction
	 */
	public void initKeys(){
		for(InputAction action : InputAction.values()){
			keys[action.ordinal()] = action.keyIndex;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		performAction(e.getKeyCode(), 0);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		performAction(e.getKeyCode(), 1);
	}
	
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

	@Override
	public void keyTyped(KeyEvent e) {}
	
	public void addActionListener(IActionListener listener){
		actionListeners.add(listener);
	}
}
