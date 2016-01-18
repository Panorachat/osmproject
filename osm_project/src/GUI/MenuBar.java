package GUI;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;

public class MenuBar{
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem  menuItem;

	public MenuBar(){
		menuBar = new JMenuBar();
		menu = new JMenu("Fichier");
		menuItem = new JMenuItem("Ouvrir un fichier");
		menu.add(menuItem);
		menuItem = new JMenuItem("Fermer le fichier actuel");
		menu.add(menuItem);
		menuBar.add(menu);
		menu = new JMenu("Édition");
		menuItem = new JMenuItem("Placer un marqueur");
		menu.add(menuItem);
		menuItem = new JMenuItem("Annuler le marquage");
		menu.add(menuItem);
		menuBar.add(menu);
		menuBar.add(menu);
	}
	
	public JMenuBar getMenuBar(){
		return menuBar;
	}
	
}
