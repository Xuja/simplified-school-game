package input;

public interface IActionListener {

	/**
	 * Method that is being called whenever a action is being performed
	 * @param action The input action that is performed
	 * @param actionFlag if the button is pressed or released, 0 = pressed, 1 = released
	 */
	void onAction(InputAction action, int actionFlag);
	
	/**
	 * @return whether the listener is active and will respond to actions
	 */
	boolean isActive();
}
