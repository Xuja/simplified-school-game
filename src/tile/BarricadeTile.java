package tile;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import entity.EnumKey;
import entity.Player;
import room.GameRoom;

public class BarricadeTile extends Tiles {

	private final EnumKey key;
	
	public BarricadeTile(String texture, int id, EnumKey key) {
		super(texture, id);
		this.key = key;
	}

	@Override
	public boolean isSolid(){
		return true;
	}
	
	public void beforePlayerWalkTo(GameRoom room, Player player, int x, int y){
		if(key == player.getCurrentKey()){
			playSound();
			room.replaceTile(TILE_WITHERED_GRASS, x, y);
		}
	}
	
	public void playSound(){
		File sound = new File("res/sounds/unlockChest.wav");
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
