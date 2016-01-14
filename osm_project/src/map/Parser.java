package map;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class Parser {

	static Document document;
	static Element racine;
	public static ArrayList<Node> Nodes;
	public static ArrayList<Way> Ways ;
	ArrayList<String> ListeTag = new ArrayList<String>();

	
	/**
	 * Constructeur de la fonction Parser
	 */
	public Parser(){
		Parser.Nodes=getAllNode();
		Parser.Ways=getAllWay();
	}
	
	
	/**
	 * Retourne la liste de Nodes
	 * @return ArrayList<Node>
	 */
	public ArrayList<Node> getNodes(){
		return Parser.Nodes;
	}
	
	
	/**
	 * Retourne la liste des Ways
	 * @return ArrayList<Way>
	 */
	public ArrayList<Way> getWays(){
		return Parser.Ways;
	}
	
	
	/**
	*Function which return the limits/bounds of map
	**/
	static String[] getBounds(){
		/**
		 * Function which return the coordinates the edges of the map 
		 * [0]=> minlat
		 * [1]=> minlon
		 * [2]=> maxlat
		 * [3]=> maxlon
		 *
		 * @return String[] 
		 *
		 */
		init();
		String[] boundtab= new String[4];

		//Retrieving the data of something at the root, here "Bounds"
		List<Element> listNode = racine.getChildren("bounds");

		//Creating of an iterator which will run through all the data of type "listNode"
		Iterator<Element> i = listNode.iterator();

		while(i.hasNext())
		{
			Element courant = (Element)i.next();
			boundtab[0]=courant.getAttributeValue("minlat");
			boundtab[1]=courant.getAttributeValue("minlon");
			boundtab[2]=courant.getAttributeValue("maxlat");
			boundtab[3]=courant.getAttributeValue("maxlon");
		}
		return boundtab;
	}
	
	
	/**
	 * Return the list of all the Way contenues dans le fichier .XML
	 * @return ArrayList<Way>
	 */
	private static ArrayList<Way> getAllWay(){
		init();
		ArrayList<Way> wayList = new ArrayList<Way>();
		List<Element> listWay = racine.getChildren("way");
		Iterator<Element> i = listWay.iterator();
		while(i.hasNext()){
			Element courant = (Element)i.next();
			Way way = new Way(Long.valueOf((courant.getAttributeValue("id"))));
			List<Element> listTag = courant.getChildren("tag");
			Iterator<Element> tag = listTag.iterator();
			while(tag.hasNext()){
				Element Tagcourant = (Element)tag.next();
				way.addTag(Tagcourant.getAttributeValue("k"));
			}
			List<Element> listRef = courant.getChildren("nd");
			Iterator<Element> ref = listRef.iterator();
			while(ref.hasNext()){
				Element refcourant = (Element)ref.next();
				way.addRef(Long.valueOf(refcourant.getAttributeValue("ref")));
			}

		
			List<Element> listValue = courant.getChildren("tag");
            Iterator<Element> value = listValue.iterator();
            while(value.hasNext()) {
                Element Valuecourant = (Element) value.next();
                way.addValue(Valuecourant.getAttributeValue("v"));
            }
			wayList.add(way);
		}
		return wayList;
	}
	
	
	/**
	*Return the list of all the nodes contenues dans le fichier .XML
	*@return ArrayList<Node>
	**/
	private static ArrayList<Node> getAllNode(){
		init();
		ArrayList<Node> nodeList = new ArrayList<Node>();
		List<Element> listNode = racine.getChildren("node");
		Iterator<Element> i = listNode.iterator();
		while(i.hasNext()){
			Element courant = (Element)i.next();
			nodeList.add(new Node(courant.getAttributeValue("id"),courant.getAttributeValue("lon"),courant.getAttributeValue("lat"),courant.getAttributeValue("visibility")));
		}
		return nodeList;
	}
	
	
	/**Retourne l'element i d'une Liste de node
	 * 
	 * @param i
	 * @return Node
	 */
	public static Node getNode(long i){
		Node n=new Node();
		for(int cpt=0;cpt<Nodes.size();cpt++){
			if(Nodes.get(cpt).getId()==i){
				return Nodes.get(cpt);
			}
		}
		return n;
	}
	
	/**
	*Initialization of Map.osm
	**/
	static void init(){
		//Creating of an instance of SAXBuilder
		SAXBuilder sxb = new SAXBuilder();
		try
		{
			//Creating of a new document JDOM with XML file as an argument
			//From here the parsing is over
			document = sxb.build(new File("osm_file/map.osm"));
		}
		catch(Exception e){}
		//Initializing a new root element with document root element.
		racine = document.getRootElement();
	}
}