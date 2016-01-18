package map;

import java.io.*;
import java.util.ArrayList;

import org.jdom2.*;
import org.jdom2.output.*;

public class SaveonFile
{
   //Nous allons commencer notre arborescence en créant la racine XML
   //qui sera ici "personnes".
   static Element racine = new Element("osm");
   public static int id=0;
   //On crée un nouveau Document JDOM basé sur la racine que l'on vient de créer
   static org.jdom2.Document document = new Document(racine);

   public void saveonFile(String longitude, String latitude, ArrayList<String> tag){
	   //On crée un nouvel Element etudiant et on l'ajoute
	      //en tant qu'Element de racine
	      Element node = new Element("node");
	      racine.addContent(node);

	      //On crée un nouvel Attribut classe et on l'ajoute à etudiant
	     //grâce à la méthode setAttribute
	      Attribute lat = new Attribute("lat",latitude);
	      node.setAttribute(lat);
	      Attribute lon = new Attribute("lon",longitude);
	      node.setAttribute(lon);

	      if(!tag.isEmpty()){
		      //On crée un nouvel Element nom, on lui assigne du texte
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
         //Remarquez qu'il suffit simplement de créer une instance de FileOutputStream
         //avec en argument le nom du fichier pour effectuer la sérialisation.
         sortie.output(document, new FileOutputStream(fichier));
      }
      catch (java.io.IOException e){}
   }
}
