package room;

import javax.swing.JLayeredPane;

import game.Game;

public abstract class Room {

	protected Game theGame;

	public Room(Game game){
		this.theGame = game;
	}

	public abstract void init();

	public abstract void update(float deltaTime);

	public abstract JLayeredPane getPanel();

	public abstract void closeRoom();
}
