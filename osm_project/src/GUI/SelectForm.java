package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;


public class SelectForm extends JPanel{

	private static final long serialVersionUID = 1L;
	private Dessin ancestor;
	private JCheckBox[] cb;
	private final int maxCB = 5;

	public SelectForm(Dessin frame){
		this.ancestor = frame;
		setBackground(Color.white);
		
		cb = new JCheckBox[maxCB];		
		cb[0] = new JCheckBox("Toilet");
		cb[1] = new JCheckBox("Restaurant");
		cb[2] = new JCheckBox("Café");
		cb[3] = new JCheckBox("École");
		cb[4] = new JCheckBox("Université");

		for(int i=0;i<maxCB;i++){
			cb[i].addActionListener(new StateListener());
			add(cb[i]);
		}
		this.setBounds(20, 350, this.getWidth(), this.getHeight());
		this.setOpaque(true);
	}

	public class StateListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("source : " + ((JCheckBox)e.getSource()).getText() + " - état : " + ((JCheckBox)e.getSource()).isSelected());
			
		}
	}
}