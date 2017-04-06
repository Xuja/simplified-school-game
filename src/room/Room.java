package room;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
	
	public void playSound(String sfx){
		File sound = new File("res/sounds/" + sfx + ".wav");
		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(sound);
			Clip clip = AudioSystem.getClip();
			clip.open(audio);
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
}
