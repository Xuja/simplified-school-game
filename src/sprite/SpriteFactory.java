package sprite;

import java.util.ArrayList;
import java.util.List;

public class SpriteFactory {

	private List<String> spriteList = new ArrayList<String>();
	
	private final String path;
	
	public SpriteFactory(String path){
		this.path = path;
	}
	
	public SpriteFactory addSprite(String sprite){
		spriteList.add(path + "/" + sprite + ".png");
		return this;
	}
	
	public Sprite buildSprite(){
		String[] sprites = spriteList.toArray(new String[0]);
		spriteList.clear();
		return new Sprite(sprites);
	}
}
