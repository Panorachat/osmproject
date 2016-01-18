package GUI;

import java.awt.event.ActionEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

public class MenuBar{
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem  menuItem1, menuItem2, menuItem3, menuItem4;

	public MenuBar(){
		menuBar = new JMenuBar();
		menu = new JMenu("Fichier");
		menuItem1 = new JMenuItem("Ouvrir un fichier");
		menu.add(menuItem1);
		menuItem2 = new JMenuItem("Fermer le fichier actuel");
		menu.add(menuItem2);
		menuBar.add(menu);
		menu = new JMenu("Édition");
		menuItem3 = new JMenuItem("Placer un marqueur");
		menu.add(menuItem3);
		menuItem4 = new JMenuItem("Annuler le marquage");
		menu.add(menuItem4);
		menuBar.add(menu);
		menuBar.add(menu);
	}

	public JMenuBar getMenuBar(){
		return menuBar;
	}

	public void actionPerformed(ActionEvent arg0){

	}
}