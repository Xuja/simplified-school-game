package sprite;

public class Sprite {

	private final String[] sprites;
	
	public Sprite(String[] sprites){
		this.sprites = sprites;
	}
	
	public String getSprite(float progress){
		int index = (int) (progress * getAnimationLength());
		return sprites[index % getAnimationLength()];
	}
	
	public int getAnimationLength(){
		return sprites.length;
	}
}
