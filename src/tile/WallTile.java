package tile;

public class WallTile extends Tiles {

	public WallTile(String texture, int id) {
		super(texture, id);
	}

	public boolean isSolid(){
		return true;
	}
}
