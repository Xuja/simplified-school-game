package input;

import java.util.HashMap;

import com.sun.glass.events.KeyEvent;

@SuppressWarnings("restriction")
public enum InputAction {

	MOVE_UP(KeyEvent.VK_UP),
	MOVE_DOWN(KeyEvent.VK_DOWN),
	MOVE_LEFT(KeyEvent.VK_LEFT),
	MOVE_RIGHT(KeyEvent.VK_RIGHT),
	INTERACT(KeyEvent.VK_SPACE),
	PAUSE(KeyEvent.VK_P);

	public static final HashMap<Integer, InputAction> INPUT_MAP = new HashMap<Integer, InputAction>();

	public final int keyIndex;

	private InputAction(int keyIndex){
		this.keyIndex = keyIndex;
	}

	public static int size(){
		return values().length;
	}

	public static InputAction get(int index){
		return values()[index % size()];
	}

	static{
		for(InputAction action : values()){
			INPUT_MAP.put(action.keyIndex, action);
		}
	}
}
