package room;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import display.TileLabel;
import entity.Entity;
import entity.EntityRegistry;
import entity.Player;
import game.FinishMenu;
import game.Game;
import game.PauseMenu;
import tile.Tiles;

public class GameRoom extends Room {

	public static final int TILE_SIZE = 64;
	public static final String ROOM_PATH = "res/rooms/";

	private final String roomID;

	private Player thePlayer;

	private Tiles[][] tiles;
	private int roomRows;
	private int roomColumns;

	private JLayeredPane layeredPane;
	private JPanel tilePanel;
	private JPanel entityPanel;
	private JPanel playerPanel;

	private FinishMenu finishMenu;
	private PauseMenu pauseMenu;
	
	private boolean isInitialized = false;

	private List<Entity> entityList = new ArrayList<Entity>();

	private boolean paused = false;
	
	public GameRoom(Game game, String id){
		super(game);
		this.roomID = id;
	}

	@Override
	public void init() {

		layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);
		layeredPane.setFocusable(false);

		tilePanel = new JPanel();
		tilePanel.setLayout(null);
		tilePanel.setFocusable(false);

		playerPanel = new JPanel();
		playerPanel.setOpaque(false);
		playerPanel.setLayout(null);
		playerPanel.setVisible(true);
		playerPanel.setFocusable(false);

		entityPanel = new JPanel();
		entityPanel.setOpaque(false);
		entityPanel.setLayout(null);
		entityPanel.setVisible(true);
		entityPanel.setFocusable(false);

		layeredPane.add(tilePanel, new Integer(0), 0);
		layeredPane.add(entityPanel, new Integer(1), 0);
		layeredPane.add(playerPanel, new Integer(2), 0);

		layeredPane.repaint();

		loadRoomData();

		int sizeX = roomRows * TILE_SIZE + 6;
		int sizeY = roomColumns * TILE_SIZE + 29;
		
		pauseMenu = new PauseMenu();
		pauseMenu.init(this, sizeX, sizeY);
		
		finishMenu = new FinishMenu();
		finishMenu.init(this, sizeX, sizeY);
		
		layeredPane.setSize(sizeX, sizeY);
		tilePanel.setSize(sizeX, sizeY);
		playerPanel.setSize(sizeX, sizeY);
		entityPanel.setSize(sizeX, sizeY);

		if(thePlayer == null){
			thePlayer = new Player(this);
		}

		theGame.getInputManager().addActionListener(thePlayer);
		try {
			loadTiles();
		} catch (IOException e) {
			e.printStackTrace();
		}
		isInitialized = true;
	}

	public Tiles getTile(int x, int y){
		return tiles[x][y];
	}

	public boolean isTileWithinRange(int x, int y){
		return x >= 0 && x < tiles.length && y >= 0 && y < tiles[0].length;
	}

	private void loadTiles() throws IOException{
		String path = ROOM_PATH + roomID + "/tiles.dat";
		tiles = new Tiles[roomRows][roomColumns];
		File file = new File(path);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		if(reader != null){

			String line;
			int index = 0;

			while((line = reader.readLine()) != null){
				String[] tileids = line.split(" ");
				if(tileids.length != tiles.length){
					System.err.println("Invalid width while reading the layout");
					return;
				}

				for(int i = 0; i < tileids.length; i++){
					int id = Integer.valueOf(tileids[i]);
					Tiles tile = Tiles.getTile(id);
					int x = index % roomRows;
					int y = index / roomRows;
					setTile(tile, x, y);
					index++;
				}
			}

			reader.close();
		}
		else{
			System.err.println("Failed to intstantiate a buffered reader for file '" + path + "'!");
		}
	}

	private void loadRoomData(){
		try {
			File file = new File(ROOM_PATH + roomID + "/room.dat");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			Document doc = builder.parse(file);

			doc.getDocumentElement().normalize();

			Element sizeNode = (Element) doc.getElementsByTagName("Size").item(0);
			roomRows = Integer.parseInt(sizeNode.getElementsByTagName("Rows").item(0).getTextContent());
			roomColumns = Integer.parseInt(sizeNode.getElementsByTagName("Columns").item(0).getTextContent());

			NodeList entityList = doc.getElementsByTagName("Entity");
			for(int i = 0; i < entityList.getLength(); i++){
				Node node = entityList.item(i);
				if(node.getNodeType() == Node.ELEMENT_NODE){
					Element element = (Element) node;
					String entityID = element.getAttribute("id");
					int posX = Integer.parseInt(element.getElementsByTagName("x").item(0).getTextContent());
					int posY = Integer.parseInt(element.getElementsByTagName("y").item(0).getTextContent());
					String state = element.getElementsByTagName("state").item(0).getTextContent();
					Entity entity = loadEntity(entityID, posX, posY, state);

					if(entity == null){
						System.err.println("Failed to instantiate entity!");
						continue;
					}

					NodeList dataList = element.getElementsByTagName("data");
					if(dataList.getLength() > 0){
						HashMap<String,String> dataMap = new HashMap<String,String>();
						for(int j = 0; j < dataList.getLength(); j++){
							Node dataNode = dataList.item(j);
							if(dataNode.getNodeType() != Node.ELEMENT_NODE)
								continue;

							Element dataElement = (Element) dataNode;
							NamedNodeMap nnm = dataElement.getAttributes();
							Node attributeNode = nnm.item(0);
							dataMap.put(attributeNode.getNodeName(), attributeNode.getTextContent());
						}
						entity.loadEntityData(dataMap);
					}
					addEntityToRoom(entity);
				}
			}
		}
		catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Entity loadEntity(String entityID, int entityPosX, int entityPosY, String entityState){
		Entity entity = EntityRegistry.createNewEntity(entityID, this);
		entity.setPosition(entityPosX, entityPosY);
		entity.setEntityState(entityState);

		if(entity instanceof Player){
			thePlayer = (Player) entity;
		}

		return entity;
	}

	public void addEntityToRoom(Entity entity){
		entity.init();
		entityList.add(entity);
		if(entity instanceof Player){
			playerPanel.add(entity.getEntityLabel());
			playerPanel.repaint();
		}
		else{
			entityPanel.add(entity.getEntityLabel());
			entityPanel.repaint();
		}
	}

	public void removeEntityFromRoom(Entity entity){
		entityList.remove(entity);
		if(entity instanceof Player){
			playerPanel.remove(entity.getEntityLabel());
			playerPanel.repaint();
		}
		else{
			entityPanel.remove(entity.getEntityLabel());
			entityPanel.repaint();
		}
	}

	public Player getPlayer(){
		return thePlayer;
	}

	public void setTile(Tiles tile, int x, int y){
		if(isTileWithinRange(x, y)){
			tiles[x][y] = tile;
			TileLabel tl = new TileLabel(tile);
			tl.setSize(TILE_SIZE, TILE_SIZE);
			tl.loadImage(TILE_SIZE);
			tl.setLocation(x * TILE_SIZE, y * TILE_SIZE);
			tl.setVisible(true);
			int index = x + y * 10;
			tilePanel.add(tl, index);
		}
	}

	public void replaceTile(Tiles tile, int x, int y) {
		tiles[x][y] = tile;
		int index = x + (y * 10);
		TileLabel tileLabel = (TileLabel) tilePanel.getComponent(index);
		tileLabel.setTile(tile);
		tileLabel.loadImage(TILE_SIZE);
	}

	@Override
	public void update(float deltaTime) {
		if(isInitialized){
			for(int i = 0; i < entityList.size(); i++){
				Entity entity = entityList.get(i);
				if(entity != null){
					entity.update(deltaTime);
					entity.render(TILE_SIZE);
				}
			}
		}
	}

	@Override
	public JLayeredPane getPanel() {
		return layeredPane;
	}

	public void finishLevel(){
		layeredPane.add(finishMenu.getFinishPanel(), new Integer(4), 0);
		layeredPane.repaint();
		paused = true;
	}

	public void closeRoom(){
		entityList.clear();
	}

	public Entity getEntityForPosition(int x, int y, Entity exclude){
		for(int i = 0; i < entityList.size(); i++){
			Entity entity = entityList.get(i);
			if(entity != null && entity != exclude){
				if(entity.getX() == x && entity.getY() == y){
					return entity;
				}
			}
		}

		return null;
	}

	public void pauseGame(){
		layeredPane.add(pauseMenu.getPausePanel(), new Integer(3), 0);
		layeredPane.repaint();
		paused = true;
	}

	public void resumeGame(){
		layeredPane.remove(pauseMenu.getPausePanel());
		layeredPane.repaint();
		paused = false;
	}

	public void restartGame(){
		theGame.setRoom(new GameRoom(theGame, roomID));
	}

	public void quitGame(){
		theGame.setRoom(new MenuRoom(theGame));
	}
	
	public boolean isPaused(){
		return paused;
	}
}
