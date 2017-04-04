package roombuilder;

import java.io.File;
import java.util.ArrayList;
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

public class RoomBuilder {
	
	public static void main(String[] args){
		List<EntityBuilder> entityList = new ArrayList<EntityBuilder>();
		entityList.add(new EntityBuilder("player", 2, 2, "idle_down").addAditionalData("blabla", "yes!"));
		entityList.add(new EntityBuilder("key", 8, 4, "idle").addAditionalData("key", "blue"));
		buildRoom("roomBuilderOut.dat", 10, 10, entityList);
	}
	
	private static void buildRoom(String path, int rows, int columns, List<EntityBuilder> entities){
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
			
			for(EntityBuilder entity : entities){
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
	
	private static Node getEntityNode(Document doc, EntityBuilder entity){
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
}
