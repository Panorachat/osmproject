package map;

import java.io.*;
import java.util.ArrayList;

import org.jdom2.*;
import org.jdom2.output.*;

public class SaveonFile
{
   //Nous allons commencer notre arborescence en cr�ant la racine XML
   //qui sera ici "personnes".
   static Element racine = new Element("osm");
   public static int id=0;
   //On cr�e un nouveau Document JDOM bas� sur la racine que l'on vient de cr�er
   static org.jdom2.Document document = new Document(racine);

   public void saveonFile(String longitude, String latitude, ArrayList<String> tag){
	   //On cr�e un nouvel Element etudiant et on l'ajoute
	      //en tant qu'Element de racine
	      Element node = new Element("node");
	      racine.addContent(node);

	      //On cr�e un nouvel Attribut classe et on l'ajoute � etudiant
	     //gr�ce � la m�thode setAttribute
	      Attribute lat = new Attribute("lat",latitude);
	      node.setAttribute(lat);
	      Attribute lon = new Attribute("lon",longitude);
	      node.setAttribute(lon);

	      if(!tag.isEmpty()){
		      //On cr�e un nouvel Element nom, on lui assigne du texte
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
         //Remarquez qu'il suffit simplement de cr�er une instance de FileOutputStream
         //avec en argument le nom du fichier pour effectuer la s�rialisation.
         sortie.output(document, new FileOutputStream(fichier));
      }
      catch (java.io.IOException e){}
   }
}
