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
		menuBar.add(menu);
		menu = new JMenu("Selection");
		menuBar.add(menu);
	}
	
	public JMenuBar getMenuBar(){
		return menuBar;
	}
	
}
