package map;

import java.io.*;
import java.util.ArrayList;

import org.jdom2.*;
import org.jdom2.output.*;

public class SaveOnFile
{
   //Nous allons commencer notre arborescence en creant la racine XML
   //qui sera ici "personnes".
   static Element racine = new Element("osm");
   public static int id=0;
   //On cree un nouveau Document JDOM base sur la racine que l'on vient de creer
   static org.jdom2.Document document = new Document(racine);

   public void saveonFile(String longitude, String latitude, ArrayList<String> tag){
	   //On cree un nouvel Element etudiant et on l'ajoute
	      //en tant qu'Element de racine
	      Element node = new Element("node");
	      racine.addContent(node);

	      //On cree un nouvel Attribut classe et on l'ajoute e etudiant
	     //grece e la methode setAttribute
	      Attribute lat = new Attribute("lat",latitude);
	      node.setAttribute(lat);
	      Attribute lon = new Attribute("lon",longitude);
	      node.setAttribute(lon);

	      if(!tag.isEmpty()){
		      //On cree un nouvel Element nom, on lui assigne du texte
		      //et on l'ajoute en tant qu'Element de etudiant
	    	  for(int i=0; i<tag.size();i++){
	    		   Element nom = new Element("tag");
	 		      Attribute k = new Attribute("k",tag.get(i));
	 		    // Attribute v = new Attribute("v","P2");
	 		      nom.setAttribute(k);
	 		     // nom.setAttribute(v);
	    	  }
		   
	      }
	      enregistre("osm_file"+"/"+"Exercice1.osm");
   }

   static void affiche()
   {
      try
      {
         //On utilise ici un affichage classique avec getPrettyFormat()
         XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
         sortie.output(document, System.out);
      }
      catch (java.io.IOException e){}
   }

   static void enregistre(String fichier)
   {
      try
      {
         //On utilise ici un affichage classique avec getPrettyFormat()
         XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
         //Remarquez qu'il suffit simplement de creer une instance de FileOutputStream
         //avec en argument le nom du fichier pour effectuer la serialisation.
         sortie.output(document, new FileOutputStream(fichier));
      }
      catch (java.io.IOException e){}
   }
}
