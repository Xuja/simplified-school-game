package roombuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class EntityBuilder{

	public static void main(String[] args){
		List<Entity> entityList = new ArrayList<Entity>();
		entityList.add(new Entity("player", 1, 1, "idle_down"));
		entityList.add(new Entity("key", 3, 8, "idle").addAditionalData("key", "blue"));
		entityList.add(new Entity("key", 6, 6, "idle").addAditionalData("key", "red"));
		entityList.add(new Entity("key", 7, 5, "idle").addAditionalData("key", "green"));
		buildRoom("res/rooms/normal/room.dat", 10, 10, entityList);
	}
	
	private static void buildRoom(String path, int rows, int columns, List<Entity> entities){
		File file = new File(path);

		try{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbf.newDocumentBuilder();
			Document doc = builder.newDocument();
			
			Element mainRootElement = doc.createElement("Room");
			doc.appendChild(mainRootElement);
			
			Element roomSizeElement = doc.createElement("Size");
			roomSizeElement.appendChild(getElement(doc, "Rows", String.valueOf(rows)));
			roomSizeElement.appendChild(getElement(doc, "Columns", String.valueOf(columns)));
			
			mainRootElement.appendChild(roomSizeElement);
			
			for(Entity entity : entities){
				mainRootElement.appendChild(getEntityNode(doc, entity));
			}
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(file);
			transformer.transform(source, result);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static Node getEntityNode(Document doc, Entity entity){
		Element entityElement = doc.createElement("Entity");
		entityElement.setAttribute("id", entity.id);
		entityElement.appendChild(getElement(doc, "x", String.valueOf(entity.x)));
		entityElement.appendChild(getElement(doc, "y", String.valueOf(entity.y)));
		entityElement.appendChild(getElement(doc, "state", entity.state));
		
		if(entity.hasAdditionalData()){
			Set<String> keySet = entity.getDataKeys();
			for(String key : keySet){
				Element dataElement = doc.createElement("data");
				dataElement.setAttribute(key, entity.getDataValue(key));
				entityElement.appendChild(dataElement);
			}
		}
		return entityElement;
	}
	
	private static Node getElement(Document doc, String name, String value){
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}
	
	private static class Entity{

		public final String id;
		public final int x;
		public final int y;
		public final String state;
		private HashMap<String,String> dataMap = new HashMap<String,String>();

		public Entity(String id, int x, int y, String state){
			this.id = id;
			this.x = x;
			this.y = y;
			this.state = state;
		}

		public Entity addAditionalData(String key, String value){
			dataMap.put(key, value);
			return this;
		}

		public boolean hasAdditionalData(){
			return !dataMap.isEmpty();
		}

		public Set<String> getDataKeys(){
			return dataMap.keySet();
		}

		public String getDataValue(String key){
			return dataMap.get(key);
		}
	}
}