package tile;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import entity.Player;
import room.GameRoom;

public class GoalTile extends Tiles {

	public GoalTile(String tileTexture, int id) {
		super(tileTexture, id);
	}

	@Override
	public void onPlayerWalkedTo(GameRoom room, Player player, int x, int y){
		playSound();
		room.finishLevel();
	}
	
	public void playSound(){
		File sound = new File("res/sounds/drop.wav");
		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(sound);
			try {
				Clip clip = AudioSystem.getClip();
				clip.open(audio);
				clip.start();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
